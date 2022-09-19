package roydon.xyz.springboot.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.date.Quarter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import roydon.xyz.springboot.common.Result;
import roydon.xyz.springboot.entity.User;
import roydon.xyz.springboot.service.IUserService;

import java.util.Date;
import java.util.List;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/18
 * Time: 19:33
 **/
@RestController
@RequestMapping("/echarts")
public class EchartController {

    @Autowired
    private IUserService userService;

    @GetMapping("/members")
    public Result members() {
        List<User> users = userService.list();
        int q1 = 0, q2 = 0, q3 = 0, q4 = 0;
        for (User user : users) {
            Date createTime = user.getCreateTime();
            Quarter quarter = DateUtil.quarterEnum(createTime);
            switch (quarter) {
                case Q1:
                    q1 += 1;
                    break;
                case Q2:
                    q2 += 1;
                    break;
                case Q3:
                    q3 += 1;
                    break;
                case Q4:
                    q4 += 1;
                    break;
                default:
                    break;
            }
        }
        ;
        return Result.success(CollUtil.newArrayList(q1, q2, q3, q4));

    }

}
