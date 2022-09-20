package roydon.xyz.springboot.service;

import roydon.xyz.springboot.entity.Menu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author roydon
 * @since 2022-09-19
 */
public interface IMenuService extends IService<Menu> {

    List<Menu> findMenus(String name);

}
