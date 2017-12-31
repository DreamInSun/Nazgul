package cyan.svc.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * 日志类型记录，使用序列号作为主键，不可修改。
 * Created by DreamInSun on 2017/12/26.
 */
public class LogEntity implements Serializable {
    /*========== Properties ==========*/
    /**
     * 逻辑主键
     */
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)//通过Sequence来实现表主键自增，这种方式依赖于数据库是否有SEQUENCE，如果没有就不能用
    @SequenceGenerator(name = "seq")
    @Column(name = "user_id", nullable = false)
    @JsonProperty
    protected BigInteger seq;

    /**
     * 记录创建时间戳 DEFAULT CURRENT_TIMESTAMP
     */
    @JsonIgnore
    protected Timestamp tsCreate;

    /*========== Getter & Setter ==========*/

    public BigInteger getSeq() {
        return seq;
    }

    public void setSeq(BigInteger seq) {
        this.seq = seq;
    }

    public Timestamp getTsCreate() {
        return tsCreate;
    }

    public void setTsCreate(Timestamp tsCreate) {
        this.tsCreate = tsCreate;
    }
}

