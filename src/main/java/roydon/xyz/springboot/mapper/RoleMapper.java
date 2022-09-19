package roydon.xyz.springboot.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import roydon.xyz.springboot.entity.Role;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author roydon
 * @since 2022-09-18
 */
public interface RoleMapper extends BaseMapper<Role> {


    @Select("select id from sys_role where flag = #{flag}")
    Integer selectByFlag(@Param("flag") String flag);
}
