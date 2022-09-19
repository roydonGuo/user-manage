package roydon.xyz.springboot.service.impl;

import roydon.xyz.springboot.entity.Menu;
import roydon.xyz.springboot.mapper.MenuMapper;
import roydon.xyz.springboot.service.IMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author roydon
 * @since 2022-09-19
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {

}
