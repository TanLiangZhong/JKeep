package com.ml.jkeep.service.system;

import com.ml.jkeep.jpa.system.vo.MenuVo;

import java.util.List;

/**
 * Date: 2019/8/15-15:10
 *
 * @author meng
 * Description:
 */
public interface MenuService {


    /**
     * 获得用户可用菜单树
     *
     * @param userId 用户Id
     * @return 菜单树
     * @author liangzhong
     * @date 2019-9-9 09:52:44
     */
    List<MenuVo> getTree(Long userId);

}
