package roydon.xyz.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/19
 * Time: 20:00
 **/
@Data
@TableName("sys_role_menu")
public class RoleMenu {

    private Integer roleId;
    private Integer menuId;

}
