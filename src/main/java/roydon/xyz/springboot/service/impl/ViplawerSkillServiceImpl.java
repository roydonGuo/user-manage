package roydon.xyz.springboot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import roydon.xyz.springboot.entity.ViplawerSkill;
import roydon.xyz.springboot.mapper.ViplawerSkillMapper;
import roydon.xyz.springboot.service.IViplawerSkillService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 律师专长(ViplawerSkill)表服务实现类
 *
 * @author roydon
 * @since 2023-10-19 18:38:10
 */
@Service("viplawerSkillService")
public class ViplawerSkillServiceImpl extends ServiceImpl<ViplawerSkillMapper, ViplawerSkill> implements IViplawerSkillService {
    @Resource
    private ViplawerSkillMapper viplawerSkillMapper;

    @Override
    public List<ViplawerSkill> findPageTree(String name) {
        LambdaQueryWrapper<ViplawerSkill> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name), ViplawerSkill::getName, name);
        List<ViplawerSkill> list = list(queryWrapper);
        // 构造树结构
        List<ViplawerSkill> returnList = new ArrayList<>();
        List<Integer> tempList = new ArrayList<>();
        for (ViplawerSkill dept : list) {
            tempList.add(dept.getId());
        }
        for (Iterator<ViplawerSkill> iterator = list.iterator(); iterator.hasNext(); ) {
            ViplawerSkill menu = (ViplawerSkill) iterator.next();
            // 如果是顶级节点, 遍历该父节点的所有子节点
            if (!tempList.contains(menu.getPid())) {
                recursionFn(list, menu);
                returnList.add(menu);
            }
        }
        if (returnList.isEmpty()) {
            returnList = list;
        }
        return returnList;

    }


    /**
     * 递归列表
     *
     */
    private void recursionFn(List<ViplawerSkill> list, ViplawerSkill one) {
        // 得到子节点列表
        List<ViplawerSkill> childList = getChildList(list, one);
        one.setChildren(childList);
        for (ViplawerSkill tChild : childList) {
            if (hasChild(list, tChild)) {
                recursionFn(list, tChild);
            }
        }
    }

    /**
     * 得到子节点列表
     */
    private List<ViplawerSkill> getChildList(List<ViplawerSkill> list, ViplawerSkill t) {
        List<ViplawerSkill> tlist = new ArrayList<ViplawerSkill>();
        Iterator<ViplawerSkill> it = list.iterator();
        while (it.hasNext()) {
            ViplawerSkill n = (ViplawerSkill) it.next();
            if (n.getPid().longValue() == t.getId().longValue()) {
                tlist.add(n);
            }
        }
        return tlist;
    }

    /**
     * 判断是否有子节点
     */
    private boolean hasChild(List<ViplawerSkill> list, ViplawerSkill t) {
        return !getChildList(list, t).isEmpty();
    }

}
