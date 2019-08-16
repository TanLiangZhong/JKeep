package com.ml.jkeep.service.system.impl;

import java.util.ArrayList;
import java.util.List;
import com.ml.jkeep.jpa.system.entity.Menu;
import com.ml.jkeep.service.system.MenuService;
import org.springframework.stereotype.Service;

/**
 * Date: 2019/8/15-15:10
 * @author meng
 * Description:
 */
@Service
public class MenuServiceImpl implements MenuService {

	@Override
	public List<Menu> getAllMenu() {
		return new ArrayList<>();
	}

}
