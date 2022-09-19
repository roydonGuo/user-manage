package roydon.xyz.springboot.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/19
 * Time: 19:24
 **/
@Data
@TableName("sys_dict")
public class Dict {
    private String name;
    private String value;
    private String type;
}
