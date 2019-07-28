package com.ml.jkeep.service.system;

import com.ml.jkeep.jpa.system.vo.HrefPermissionVo;

import java.util.Set;

/**
 * href 权限服务
 *
 * @author 谭良忠
 * @date 2019/7/24 17:50
 */
public interface HrefPermissionService {

    /**
     * 获取用户可访问的 href
     *
     * @param userId 用户Id
     * @return 用户所有权限
     */
    Set<HrefPermissionVo> hrefPermission(Long userId);

    /**
     * 获取所有href权限配置
     *
     * @return 所有权限
     */
    Set<HrefPermissionVo> hrefPermission();


}
