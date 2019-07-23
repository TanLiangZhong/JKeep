package com.ml.jkeep.jpa.system.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 链接权限 - vo
 *
 * @author 谭良忠
 * @date 2019/7/23 17:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class HrefPermissionVo {

    /**
     * 唯一标记
     */
    private String code;

    /**
     * 链接
     */
    private String href;
}
