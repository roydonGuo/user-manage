package roydon.xyz.springboot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.logging.log4j.util.Strings;
import roydon.xyz.springboot.common.Result;
import roydon.xyz.springboot.entity.Viplawer;
import roydon.xyz.springboot.entity.ViplawerSkill;
import roydon.xyz.springboot.service.IViplawerSkillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.time.LocalDateTime;

/**
 * 律师专长(ViplawerSkill)表控制层
 *
 * @author roydon
 * @since 2023-10-19 18:38:09
 */
@RestController
@RequestMapping("/viplawerSkill")
public class ViplawerSkillController {

    @Resource
    private IViplawerSkillService viplawerSkillService;

    /**
     * 集合树
     */
    @GetMapping("/list")
    public Result queryList(@RequestParam(defaultValue = "") String name) {
        return Result.success(viplawerSkillService.findPageTree(name));
    }

    @PostMapping()
    public Result save(@RequestBody ViplawerSkill viplawerSkill) {
        viplawerSkill.setCreateTime(LocalDateTime.now());
        return Result.success(viplawerSkillService.saveOrUpdate(viplawerSkill));
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Long id) {
        return Result.success(viplawerSkillService.removeById(id));
    }

}

