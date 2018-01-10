package cyan.svc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

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
 * <p>
 * @GeneratorValue(strategy=GenerationType.xxx) ：
 * GenerationType.AUTO：让Hibernate根据数据库方言自动选择主键生成策略。
 * GenerationType.IDENTITY: 适宜MySQL、SqlServer有自增长列的数据库。
 * GenerationType.SEQUENCE：适宜Oracle这种没有自增长有sequence的数据库。
 * GenerationType.TABLE: 适宜所有的数据库,因为它会单独生成一张表来维护主键生成。
 * <p>
 * @Column : 持久化类中属性转化成数据库表中列的相关信息。
 * name：指定列名。
 * length: 该列支持的长度。
 * precision：有效的总位数。(BigDecimal)。
 * scale：小数点的位数。(BigDecimal)。
 * unique： 唯一约束。
 * nullable：非空约束。
 * insertable：是否允许插入true：允许 false: 不允许。
 * updatable：是否允许修改true：允许 false: 不允许。
 * columnDefinition ：指定列的定义。
 * <p>
 * //@DynamicInsert(true) //动态插入，根据持久化对象的属性是否有值，生成相应的Sql语句
 * //@DynamicUpdate(true) //动态修改，会判断持久化对象中的属性，那些属性改变，才会生成相应的update语句（持久化状态下做修改）
 * @SelectBeforeUpdate(true) //修改之前先进行查询，查询得到持久化对象与脱管状态下的对象进行比较，那些属性改变生成sql语句。
 */
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class BaseEntity implements Serializable {
    /*========== Properties ==========*/
    /**
     * 逻辑主键
     */
    @Id
    @Generated(value = GenerationTime.INSERT)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 64, unique = true, insertable = false, updatable = false)
    @JsonProperty
    protected Long id;

    /**
     * 记录创建时间戳 DEFAULT CURRENT_TIMESTAMP
     */
    @JsonIgnore
    @Column(name = "ts_create", nullable = false, insertable = false, updatable = false)
    protected Timestamp tsCreate;

    /**
     * 记录更新时间戳 DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
     */
    @JsonIgnore
    @Column(name = "ts_update", nullable = false, insertable = false, updatable = false)
    protected Timestamp tsUpdate;

    /**
     * 记录状态，伪删除等使用，1-为有效， 0-为无效（已删除）
     *
     * @return
     */
    @JsonIgnore
    @Column(name = "item_stat", nullable = false, insertable = false, updatable = true, columnDefinition = "INT default 1")
    protected Integer itemStat;

    /*========== Getter & Setter ==========*/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Integer getItemStat() {
        return itemStat;
    }

    public void setItemStat(Integer itemStat) {
        this.itemStat = itemStat;
    }

    /*========= Assistant Function ==========*/
    public void delete() {
        this.itemStat = 0;
    }
}

