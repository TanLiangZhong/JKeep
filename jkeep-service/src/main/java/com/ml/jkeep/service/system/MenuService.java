package com.ml.jkeep.service.system;

import com.ml.jkeep.jpa.system.entity.Menu;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2019/8/15-15:10
 * @author meng
 * Description:
 */
public interface MenuService {

    List<Menu> getAllMenu();

}
