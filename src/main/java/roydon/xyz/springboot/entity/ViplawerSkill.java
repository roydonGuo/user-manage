package roydon.xyz.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.util.List;

/**
 * 律师专长(ViplawerSkill)实体类
 *
 * @author roydon
 * @since 2023-10-19 18:38:09
 */
@Data
@TableName("viplawer_skill")
public class ViplawerSkill implements Serializable {
    private static final long serialVersionUID = -15729811980582218L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 专长名称
     */
    private String name;

    private Integer pid;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    @TableField(exist = false)
    private List<ViplawerSkill> children;


}

