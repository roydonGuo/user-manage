package roydon.xyz.springboot.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.logging.log4j.util.Strings;
import org.springframework.web.bind.annotation.*;
import roydon.xyz.springboot.common.Constants;
import roydon.xyz.springboot.common.Result;
import roydon.xyz.springboot.entity.Dict;
import roydon.xyz.springboot.entity.Menu;
import roydon.xyz.springboot.mapper.DictMapper;
import roydon.xyz.springboot.service.IMenuService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author roydon
 * @since 2022-09-19
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Resource
    private IMenuService menuService;
    @Resource
    private DictMapper dictMapper;

    // 新增或者更新
    @PostMapping
    public Result save(@RequestBody Menu menu) {
        return Result.success(menuService.saveOrUpdate(menu));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        return Result.success(menuService.removeById(id));
    }

    @DeleteMapping("/del/batch")
    public Result deleteBatch(@RequestBody List<Integer> ids) {
        return Result.success(menuService.removeByIds(ids));
    }

    @GetMapping
    public Result findAll(@RequestParam(defaultValue = "") String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(name), "name", name);
        List<Menu> menuList = menuService.list();
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
        return Result.success(parentMenus);
    }

    @GetMapping("/{id}")
    public Result findOne(@PathVariable Integer id) {
        return Result.success(menuService.getById(id));
    }

    @GetMapping("/page")
    public Result findPage(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String name) {
        QueryWrapper<Menu> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(name), "name", name);
        return Result.success(menuService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }


    @GetMapping("/icons")
    public Result getIcons() {
        QueryWrapper<Dict> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type", Constants.DICT_TYPE_ICON);
        return Result.success(dictMapper.selectList(null));
    }
}

