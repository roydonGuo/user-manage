package roydon.xyz.springboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import roydon.xyz.springboot.entity.Viplawer;

import java.util.List;

/**
 * 金牌律师(Viplawer)表服务接口
 *
 * @author roydon
 * @since 2023-10-19 18:34:36
 */
public interface IViplawerService extends IService<Viplawer> {

    List<Integer> getSkillIdTree(Integer id);

    void saveLawerSkills(Integer id, List<Integer> skillIds);
}
