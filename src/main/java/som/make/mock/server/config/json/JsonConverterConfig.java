package som.make.mock.server.config.json;

import com.fasterxml.jackson.datatype.hibernate6.Hibernate6Module;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

@Configuration
public class JsonConverterConfig implements InitializingBean {

    private final MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;

    public JsonConverterConfig(MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter) {
        this.mappingJackson2HttpMessageConverter = mappingJackson2HttpMessageConverter;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("ObjectMapper Add Hibernate6Module!");
        mappingJackson2HttpMessageConverter.getObjectMapper().registerModule(new Hibernate6Module());
    }

}
