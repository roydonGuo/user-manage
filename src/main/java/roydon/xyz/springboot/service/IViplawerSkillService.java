package roydon.xyz.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import roydon.xyz.springboot.entity.ViplawerSkill;

import java.util.List;

/**
 * 律师专长(ViplawerSkill)表服务接口
 *
 * @author roydon
 * @since 2023-10-19 18:38:10
 */
public interface IViplawerSkillService extends IService<ViplawerSkill> {

    List<ViplawerSkill> findPageTree(String name);
}
