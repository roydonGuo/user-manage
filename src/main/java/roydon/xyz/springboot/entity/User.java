package roydon.xyz.springboot.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/13
 * Time: 18:39
 **/
@Data
@TableName(value = "sys_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String username;
    @JsonIgnore
    private String password; //JsonIgnore 忽略此字段不展示给前端
    private String nickname;
    private String email;
    private String phone;
    private String address;
    @TableField(value = "avatar_url")  // 指定数据库的字段名称
    private String avatar;

}
