package roydon.xyz.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import roydon.xyz.springboot.common.Result;
import roydon.xyz.springboot.entity.User;
import roydon.xyz.springboot.service.UserService;

import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/13
 * Time: 18:48
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> index() {
        List<User> userList = userService.list();
        return userList;
    }

    /**
     * 分页查询
     * /user/page?pageNum=1&pageSize=10，limit第一个参数 = (pageNum - 1) * pageSize
     *
     * @param pageNum
     * @param pageSize
     * @param username
     * @param email
     * @param address
     * @return
     */
//    @GetMapping("/page")
//    public Map<String, Object> findPage(@RequestParam Integer pageNum,
//                                        @RequestParam Integer pageSize,
//                                        @RequestParam String username,
//                                        @RequestParam String email,
//                                        @RequestParam String address) {
//        pageNum = (pageNum - 1) * pageSize;
//        username = "%" + username + "%";
//        email = "%" + email + "%";
//        address = "%" + address + "%";
//        List<User> data = userMapper.selectPage(pageNum, pageSize, username,email,address);
//        Integer total = userMapper.selectTotal(username,email,address);
//        Map<String, Object> res = new HashMap<>();
//        res.put("data", data);
//        res.put("total", total);
//        return res;
//    }
    @GetMapping("/page")
    public IPage<User> findPage(@RequestParam Integer pageNum,
                                @RequestParam Integer pageSize,
                                @RequestParam(defaultValue = "") String username,
                                @RequestParam(defaultValue = "") String email,
                                @RequestParam(defaultValue = "") String address) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(username), "username", username);// Strings.isNotEmpty(username) 数据库此字段判断是否为空
        queryWrapper.like(Strings.isNotEmpty(email), "email", email);// 相当于拼接了and条件
        queryWrapper.like(Strings.isNotEmpty(address), "address", address);
        return userService.page(new Page<>(pageNum, pageSize), queryWrapper);
    }

    @PostMapping
    public Result save(@RequestBody User user) {

        return Result.success(userService.saveOrUpdate(user));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(userService.removeById(id));
    }

    @DeleteMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(userService.removeByIds(ids));
    }
}
