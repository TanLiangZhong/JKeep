package com.ml.jkeep.service.system;

import com.ml.jkeep.jpa.system.entity.sys.Menu;
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

    public List<Menu> getAllMenu() {
        return new ArrayList<>();
    }

}
