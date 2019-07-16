package com.ml.jkeep.service.system;

import com.ml.jkeep.jpa.system.entity.Menu;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 菜单 - service
 *
 * @author 谭良忠
 * @date 2019/6/20 13:35
 */
@Service
public class MenuService {

    @Cacheable
    public List<Menu> getAllMenu() {
        // TODO 所有 url 权限
        return new ArrayList<>();
    }

}
