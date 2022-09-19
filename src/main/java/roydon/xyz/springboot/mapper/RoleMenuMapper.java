package roydon.xyz.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import roydon.xyz.springboot.entity.RoleMenu;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/19
 * Time: 20:01
 **/
public interface RoleMenuMapper extends BaseMapper<RoleMenu> {

    @Delete("delete from sys_role_menu where role_id = #{roleId}")
    int delByRoleId(@Param("roleId") Integer roleId);

    @Select("select menu_id from sys_role_menu where role_id = #{roleId}")
    List<Integer> selectByRoleId(@Param("roleId") Integer roleId);
}
