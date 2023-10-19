package roydon.xyz.springboot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import roydon.xyz.springboot.entity.LawerSkill;
import roydon.xyz.springboot.mapper.LawerSkillMapper;
import roydon.xyz.springboot.service.ILawerSkillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 律师专长多对多表(LawerSkill)表服务实现类
 *
 * @author roydon
 * @since 2023-10-19 18:40:37
 */
@Service("lawerSkillService")
public class LawerSkillServiceImpl extends ServiceImpl<LawerSkillMapper, LawerSkill> implements ILawerSkillService {
    @Resource
    private LawerSkillMapper lawerSkillMapper;

}
