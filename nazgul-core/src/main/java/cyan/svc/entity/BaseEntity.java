package cyan.svc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;

/**
 * 标准类型实体
 * 支持Jackson注解
 * 支持JPA注解
 * 数据库字段使用 @Column(name = "id", nullable = false, length = 64, unique = true, updatable = false)
 * 瞬时字段使用 @Transient
 * Created by DreamInSun on 2017/12/26.
 * <p>
 * <p>
 * 【常用注解】
 * 使用JPA 指定映射关系时，有两种方式，一种是使用xml 方式，另一种是注解方式，笔者推荐使用注解方式。在JPA 映射简单实体时，常用的注解如下：
 *
 * @Entity：修饰实体类对象，表示该实体类对象进行数据库映射
 * @Table(name="***")：指定实体类对象映射的表名称
 * @Id： 指定主键
 * @GeneratedValue(strategy=GenerationType.AUTO)：指定主键生成方式，默认为Auto。 IDENTITY：采用数据库 ID自增长的方式来自增主键字段，Oracle 不支持这种方式；
 * AUTO： JPA自动选择合适的策略，是默认选项；
 * SEQUENCE：通过序列产生主键，通过 @SequenceGenerator 注解指定序列名，MySql 不支持这种方式
 * TABLE：通过表产生主键，框架借由表模拟序列产生主键，使用该策略可以使应用更易于数据库移植。
 * @Column(name="s_name",length=20,unique=true,nullable=false, insertable=true, updatable=true)：修饰属性， 指定列名称和相关限制
 * @Enumerated(EnumType.STRING)：修饰枚举类属性， EnumType.STRING： 指定数据库中存储的是字符串类型,
 * EnumTypee.ORDINAL:指定数据库 存储的类型为枚举的索引(0,1,2,3...)
 * @Temporal(TemporalType.TIME)：修饰日期类型： TemporalType.DATE: 指定映射数据库中的DATE 类型，只存储日期
 * TemporalType.TIME: 指定映射数据库 中的TIME类型， 只存储时间
 * TemporalType.TIMESTAMP:指定映射数据库中的TIMESTAMP类型
 * @Transient：指定不映射的属性
 * @Lob：修饰 byte[] 数组，二进制文件
 * @Basic(fetch=FetchType.LAZY) ： 默认注解，如果字段不添加任何注解，则默认添加了此注解。可以通过fetch 属性指定大数据字段延时加载，目前在Hibernate中并未能实现，或许是Hibernate的一个bug。
 * @NamedQueries({
 * @NamedQuery(name="***",query="*** hql ***"),
 * @NamedQuery(name="***",query="*** hql ***")
 * })： 命名查询注解，指定命名查询语句，query字段只能写JPQL 查询语句，不能写普通的sql 语句。
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {
    /*========== Properties ==========*/
    /**
     * 逻辑主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 64, unique = true, updatable = false)
    @JsonProperty
    protected Integer id;

    /**
     * 记录创建时间戳 DEFAULT CURRENT_TIMESTAMP
     */
    @JsonIgnore
    @Column(name = "ts_create", nullable = false, updatable = false)
    protected Timestamp tsCreate;

    /**
     * 记录更新时间戳 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
     */
    @JsonIgnore
    @Column(name = "ts_update", nullable = false)
    protected Timestamp tsUpdate;

    /*========== Getter & Setter ==========*/
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Timestamp getTsCreate() {
        return tsCreate;
    }

    public void setTsCreate(Timestamp tsCreate) {
        this.tsCreate = tsCreate;
    }

    public Timestamp getTsUpdate() {
        return tsUpdate;
    }

    public void setTsUpdate(Timestamp tsUpdate) {
        this.tsUpdate = tsUpdate;
    }
}

