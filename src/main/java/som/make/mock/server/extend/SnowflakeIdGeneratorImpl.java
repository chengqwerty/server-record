package som.make.mock.server.extend;

import org.hibernate.HibernateException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;

import java.io.Serializable;

public class SnowflakeIdGeneratorImpl implements IdentifierGenerator, Serializable {

    private static final SnowflakeIdGenerator snowflakeIdGenerator = new SnowflakeIdGenerator(1L, 1L);

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) throws HibernateException {
        long nextedId = snowflakeIdGenerator.nextId();
        // 获取主键字段的类型
        Class<?> idType = session.getEntityPersister(null, object).getIdentifierType().getReturnedClass();

        if (idType == String.class) {
            // 生成 String 类型的主键
            return nextedId + "";
        } else if (idType == Long.class) {
            return nextedId;
        }
        throw new HibernateException("不支持的主键类型: " + idType);
    }

}
