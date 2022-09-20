package roydon.xyz.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import roydon.xyz.springboot.entity.Menu;
import roydon.xyz.springboot.entity.Role;
import roydon.xyz.springboot.entity.RoleMenu;
import roydon.xyz.springboot.mapper.RoleMapper;
import roydon.xyz.springboot.mapper.RoleMenuMapper;
import roydon.xyz.springboot.service.IMenuService;
import roydon.xyz.springboot.service.IRoleService;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author roydon
 * @since 2022-09-18
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

    @Resource
    private RoleMenuMapper roleMenuMapper;

    @Resource
    private IMenuService menuService;

    @Transactional
    @Override
    public void setRoleMenu(Integer roleId, List<Integer> menuIds) {
        roleMenuMapper.delByRoleId(roleId);
        for (Integer menuId : menuIds) {
            Menu menu = menuService.getById(menuId);
            List<Integer> menuCopy = CollUtil.newArrayList(menuIds);
            if (menu.getPid() != null && !menuCopy.contains(menu.getPid())) { // 二级菜单
                RoleMenu roleMenu = new RoleMenu();
                roleMenu.setRoleId(roleId);
                roleMenu.setMenuId(menu.getPid());
                roleMenuMapper.insert(roleMenu);
                menuCopy.add(menu.getPid());
            }
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            roleMenuMapper.insert(roleMenu);
        }
    }

    @Override
    public List<Integer> getRoleMenu(Integer roleId) {
        return roleMenuMapper.selectByRoleId(roleId);
    }
}
