package com.ml.jkeep;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.ml.jkeep.common.annotation.CriteriaBuilderQuery;
import com.ml.jkeep.jpa.system.bo.UserSearchBo;

/**
 * Date: 2019/8/16-9:38
 * @author meng
 * Description: 通过反射获取 成员变量的名字、成员变量对应的值 , 成员变量指定某个注解
 */
public class ParseAnnotationTest {

	public static void main(String[] args) {

		UserSearchBo userSearch = new UserSearchBo();
		userSearch.setBranchId("888");
		userSearch.setEmail("dream891117@gmail.com");
		userSearch.setName("Meng");
		userSearch.setNickname("Meng ~ NickName");
		userSearch.setPhone("13585549864");
		userSearch.setStatus("1");
		userSearch.setUsername("dream891117");

		Class<UserSearchBo> clazz = (Class<UserSearchBo>)userSearch.getClass();
		try {
			for (Field field : clazz.getDeclaredFields()){
				String name = field.getName();
				field.setAccessible(true);
				Object value = field.get( userSearch );
				CriteriaBuilderQuery criteriaBuilderQuery = field.getAnnotation(CriteriaBuilderQuery.class);
				System.out.println( String.format("Name : {%s} , Value : {%s} , CriteriaBuilderQuery : {%s}",name,value,criteriaBuilderQuery) );
			}
		} catch (Exception e){
			e.printStackTrace();
		}


	}

}
