package roydon.xyz.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * 律师专长多对多表(LawerSkill)实体类
 *
 * @author roydon
 * @since 2023-10-19 18:40:36
 */
@Data
@TableName("lawer_skill")
public class LawerSkill implements Serializable {
    private static final long serialVersionUID = -94989472366481527L;
    /**
     * 律师id
     */
    private Integer lawerId;
    /**
     * 专长id
     */
    private Integer skillId;


}

