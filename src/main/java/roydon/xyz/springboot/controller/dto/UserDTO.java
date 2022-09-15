package roydon.xyz.springboot.controller.dto;

import lombok.Data;

/**
 * Created by Intellij IDEA
 * Author: yi cheng
 * Date: 2022/9/15
 * Time: 20:19
 **/
@Data
public class UserDTO {
    private String username;
    private String password;
    private String nickname;
    private String avatarUrl;
}
