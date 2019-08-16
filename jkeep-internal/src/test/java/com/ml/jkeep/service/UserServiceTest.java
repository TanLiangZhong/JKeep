package com.ml.jkeep.service;

import java.util.HashMap;
import java.util.Map;
import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.PageVo;
import com.ml.jkeep.jpa.system.bo.UserSearchBo;
import com.ml.jkeep.jpa.system.entity.User;
import com.ml.jkeep.service.system.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Date: 2019/8/16-12:50
 * @author meng
 * Description: 用户Service测试
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTest {

	@Autowired
	UserService userService;

	@Test
	public void findPageTest(){

		UserSearchBo userSearch = new UserSearchBo();
		userSearch.setBranchId("1");
		userSearch.setEmail("liangzhong.tan@outlook.com");
		userSearch.setName("十一月的萧邦");
		userSearch.setNickname("JKeep");
		userSearch.setPhone("15111213963");
		userSearch.setStatus("0");
		userSearch.setUsername("JKeep");

		PageBo<UserSearchBo> pageBo = new PageBo<>();
		pageBo.setPage(1);
		pageBo.setParam(userSearch);
		Map<String,String> orderByList = new HashMap<>();
		pageBo.setOrderby( orderByList );
		PageVo<User> pageVo = userService.findPage( pageBo );

		for (User user : pageVo.getData()){
			System.out.println( String.format("获取的用户信息 : {%s} " , user.toString()) );
		}

	}

}
