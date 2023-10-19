package roydon.xyz.springboot.controller;

import roydon.xyz.springboot.service.ILawerSkillService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 律师专长多对多表(LawerSkill)表控制层
 *
 * @author roydon
 * @since 2023-10-19 18:40:35
 */
@RestController
@RequestMapping("/lawerSkill")
public class LawerSkillController {

    @Resource
    private ILawerSkillService lawerSkillService;

}

