package som.make.mock.server.web.system.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import org.hibernate.annotations.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

@Entity
@Table(name = "sys_dict_detail")
@SQLDelete(table = "sys_dict_detail", sql = "update sys_dict_detail set delete_flag = 1 where delete_flag = ? ")
@Where(clause = "deleteFlag = 0")
public class SysDictDetail {

    @Id
    @UuidGenerator(style = UuidGenerator.Style.TIME)
    @Comment("主键Id")
    private String dictDetailId;

    @Column(length = 64)
    @Comment("字典编码")
    @Length(min = 4)
    private String dictCode;

    @Column(length = 64)
    @Comment("code")
    private String code;

    @Column(length = 64)
    @Comment("name")
    private String name;

    @Column(name = "delete_flag", nullable = false)
    @Comment("删除标记")
    @ColumnDefault("0")
    private Integer deleteFlag;

    @Column()
    @Comment("创建时间")
    private LocalDateTime createTime;

    @Column(length = 64)
    @Comment("创建人")
    private LocalDateTime creteUser;

    @Column()
    @Comment("修改时间")
    private String updateTime;

    @Column(length = 64)
    @Comment("修改人")
    private String updateUser;

}
