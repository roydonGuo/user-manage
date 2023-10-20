package roydon.xyz.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import roydon.xyz.springboot.entity.LawerSkill;
import roydon.xyz.springboot.entity.ViplawerSkill;

import java.util.List;

/**
 * 律师专长多对多表(LawerSkill)表服务接口
 *
 * @author roydon
 * @since 2023-10-19 18:40:37
 */
public interface ILawerSkillService extends IService<LawerSkill> {

    /**
     * 根据id获取律师的所有技能
     * @param id
     * @return
     */
    List<ViplawerSkill> getLawerSkill(Integer id);

}
