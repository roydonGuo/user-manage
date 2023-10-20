package roydon.xyz.springboot.service.impl;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.transaction.annotation.Transactional;
import roydon.xyz.springboot.entity.*;
import roydon.xyz.springboot.mapper.ViplawerMapper;
import roydon.xyz.springboot.service.ILawerSkillService;
import roydon.xyz.springboot.service.IViplawerService;
import org.springframework.stereotype.Service;
import roydon.xyz.springboot.service.IViplawerSkillService;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 金牌律师(Viplawer)表服务实现类
 *
 * @author roydon
 * @since 2023-10-19 18:34:36
 */
@Service("viplawerService")
public class ViplawerServiceImpl extends ServiceImpl<ViplawerMapper, Viplawer> implements IViplawerService {

    @Resource
    private ViplawerMapper viplawerMapper;

    @Resource
    private ILawerSkillService lawerSkillService;

    @Override
    public List<Integer> getSkillIdTree(Integer id) {
        LambdaQueryWrapper<LawerSkill> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.select(LawerSkill::getSkillId).eq(LawerSkill::getLawerId, id);
        List<LawerSkill> list = lawerSkillService.list(queryWrapper);
        return list.stream().map(LawerSkill::getSkillId).collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void saveLawerSkills(Integer id, List<Integer> skillIds) {
        // 先根据id删除所有记录
        LambdaQueryWrapper<LawerSkill> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(LawerSkill::getLawerId, id);
        lawerSkillService.remove(queryWrapper);
        // 批量插入
        for (Integer skill : skillIds) {
            LawerSkill lawerSkill = new LawerSkill();
            lawerSkill.setLawerId(id);
            lawerSkill.setSkillId(skill);
            lawerSkillService.save(lawerSkill);
        }
    }
}
