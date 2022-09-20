package roydon.xyz.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;
import roydon.xyz.springboot.entity.Menu;
import roydon.xyz.springboot.mapper.MenuMapper;
import roydon.xyz.springboot.service.IMenuService;

import java.util.List;
import java.util.stream.Collectors;

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


    @Override
    public List<Menu> findMenus(String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(name), "name", name);
        List<Menu> menuList = list(queryWrapper);
        // 找到pid为null的一级菜单
        List<Menu> parentMenus = menuList.stream()
                .filter(menu -> menu.getPid() == null)
                .collect(Collectors.toList());
        for (Menu menu : parentMenus) {
            List<Menu> children = menuList.stream()
                    .filter(m -> menu.getId().equals(m.getPid()))
                    .collect(Collectors.toList());
            menu.setChildren(children);
        }
        return parentMenus;
    }
}
