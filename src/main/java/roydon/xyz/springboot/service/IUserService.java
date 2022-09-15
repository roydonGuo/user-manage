package roydon.xyz.springboot.service;

import roydon.xyz.springboot.controller.dto.UserDTO;
import roydon.xyz.springboot.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author roydon
 * @since 2022-09-15
 */
public interface IUserService extends IService<User> {

    UserDTO login(UserDTO userDTO);

    User register(UserDTO userDTO);
}
