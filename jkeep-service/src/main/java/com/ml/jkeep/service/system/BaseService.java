package com.ml.jkeep.service.system;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.PageVo;

import java.util.Optional;


/**
 * Date: 2019/8/15-15:11
 *
 * @author meng
 * Description: 基础 - Service 接口
 */
public interface BaseService<T, ID> {

    PageVo<T> findPage(PageBo<?> pageBo);

	/**
	 * Retrieves an entity by its id.
	 *
	 * @param id must not be {@literal null}.
	 * @return the entity with the given id or {@literal Optional#empty()} if none found
	 * @throws IllegalArgumentException if {@code id} is {@literal null}.
	 */
    Optional<T> findById(ID id);
}
