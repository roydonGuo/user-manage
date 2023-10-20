package roydon.xyz.springboot.entity.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import roydon.xyz.springboot.entity.Viplawer;

/**
 * 金牌律师(Viplawer)实体类
 *
 * @author roydon
 * @since 2023-10-19 18:34:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class ViplawerVO extends Viplawer {

    private String skills;

}

