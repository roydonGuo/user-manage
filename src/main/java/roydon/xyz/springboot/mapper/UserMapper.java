package roydon.xyz.springboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import roydon.xyz.springboot.entity.User;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/13
 * Time: 18:46
 **/
public interface UserMapper extends BaseMapper<User> {

//    @Select("SELECT * from sys_user")
//    List<User> findAll();
//
//    @Insert("INSERT into sys_user(username, password,nickname,email,phone,address) VALUES (#{username}, #{password}," +
//            " #{nickname}, #{email},#{phone}, #{address})")
//    int insert(User user);
//
//    int update(User user);
//
//    @Delete("delete from sys_user where id = #{id}")
//    Integer deleteById(@Param("id") Integer id);
//
//    @Select("select * from sys_user where username like #{username} and email like #{email} and address like #{address} limit #{pageNum}, #{pageSize}")
//    List<User> selectPage(Integer pageNum, Integer pageSize, String username,String email,String address);
//
//    @Select("select count(*) from sys_user where username like concat('%', #{username}, '%') and email like concat('%', #{email}, '%') and address like concat('%', #{address}, '%')" )
//    Integer selectTotal(String username,String email,String address);

}
