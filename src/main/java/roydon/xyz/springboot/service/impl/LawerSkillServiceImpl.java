package roydon.xyz.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import roydon.xyz.springboot.entity.LawerSkill;
import roydon.xyz.springboot.entity.ViplawerSkill;
import roydon.xyz.springboot.mapper.LawerSkillMapper;
import roydon.xyz.springboot.service.ILawerSkillService;
import org.springframework.stereotype.Service;
import roydon.xyz.springboot.service.IViplawerSkillService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

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

    @Resource
    private IViplawerSkillService viplawerSkillService;

    @Override
    public List<ViplawerSkill> getLawerSkill(Integer id) {
        LambdaQueryWrapper<LawerSkill> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LawerSkill::getLawerId, id);
        List<LawerSkill> list = list(queryWrapper);
        if (list == null || list.isEmpty()) return null;
        List<Integer> skillids = list.stream().map(LawerSkill::getSkillId).collect(Collectors.toList());
        LambdaQueryWrapper<ViplawerSkill> queryWrapper2 = new LambdaQueryWrapper<>();
        queryWrapper2.in(ViplawerSkill::getId, skillids);
        List<ViplawerSkill> viplawerSkills = viplawerSkillService.list(queryWrapper2);
        return viplawerSkills;
    }
}
