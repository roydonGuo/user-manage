package roydon.xyz.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;
import java.io.Serializable;

/**
 * 金牌律师(Viplawer)实体类
 *
 * @author roydon
 * @since 2023-10-19 18:34:36
 */
@Data
@TableName("viplawer")
public class Viplawer implements Serializable {
    private static final long serialVersionUID = -20146039231717764L;

    @TableId(value = "id",type = IdType.AUTO)
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    private String avatar;
    /**
     * 省代码
     */
    private String provinceCode;
    /**
     * 城市代码
     */
    private String cityCode;

    private String regionCode;
    /**
     * 联系电话
     */
    private String telephone;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}

