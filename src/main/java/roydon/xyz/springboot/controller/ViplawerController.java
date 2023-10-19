package roydon.xyz.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.logging.log4j.util.Strings;
import roydon.xyz.springboot.common.Result;
import roydon.xyz.springboot.entity.Viplawer;
import roydon.xyz.springboot.service.IViplawerService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;

/**
 * 金牌律师(Viplawer)表控制层
 *
 * @author roydon
 * @since 2023-10-19 18:34:34
 */
@RestController
@RequestMapping("/viplawer")
public class ViplawerController {

    @Resource
    private IViplawerService viplawerService;

    /**
     * 分页查询
     */
    @GetMapping("/page")
    public Result queryPage(@RequestParam Integer pageNum,
                            @RequestParam Integer pageSize,
                            @RequestParam(defaultValue = "") String name,
                            @RequestParam(defaultValue = "") String provinceCode,
                            @RequestParam(defaultValue = "") String cityCode,
                            @RequestParam(defaultValue = "") String regionCode,
                            @RequestParam(defaultValue = "") String skillId) {
        LambdaQueryWrapper<Viplawer> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(Strings.isNotEmpty(name), Viplawer::getName, name);
        queryWrapper.eq(Strings.isNotEmpty(provinceCode), Viplawer::getProvinceCode, provinceCode);
        queryWrapper.eq(Strings.isNotEmpty(cityCode), Viplawer::getCityCode, cityCode);
        queryWrapper.eq(Strings.isNotEmpty(regionCode), Viplawer::getRegionCode, regionCode);
        return Result.success(viplawerService.page(new Page<>(pageNum, pageSize), queryWrapper));
    }

    @PostMapping()
    public Result save(@RequestBody Viplawer viplawer){
        viplawer.setCreateTime(LocalDateTime.now());
        return Result.success(viplawerService.saveOrUpdate(viplawer));
    }

    /**
     * 技能树
     */
    @GetMapping("/skillIdTree/{id}")
    public Result getSkillIdTree(@PathVariable Integer id) {
        return Result.success(viplawerService.getSkillIdTree(id));
    }

    /**
     * 绑定关系
     */
    @PostMapping("/skill/{id}")
    public Result saveIcon(@PathVariable Integer id,
                           @RequestBody List<Integer> skillIds) {
        viplawerService.saveLawerSkills(id,skillIds);
        return Result.success();
    }

}

