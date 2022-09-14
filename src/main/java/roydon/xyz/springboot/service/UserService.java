package roydon.xyz.springboot.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import roydon.xyz.springboot.entity.User;
import roydon.xyz.springboot.mapper.UserMapper;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/13
 * Time: 19:00
 **/
@Service
public class UserService extends ServiceImpl<UserMapper, User> {
    public boolean saveUser(User user) {
//        if (user.getId() == null) {
//            return save(user);
//        } else {
//            return updateById(user);
//        }
        return saveOrUpdate(user);
    }

//    @Autowired
//    private UserMapper userMapper;

//    public int save(User user){
//        if (user.getId() == null) {  // user没有id，则表示是新增
//            return userMapper.insert(user);
//        } else { // 否则为更新
//            return userMapper.update(user);
//        }
//    }

}
