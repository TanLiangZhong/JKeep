package com.ml.jkeep.service.system;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.PageVo;


/**
 * Date: 2019/8/15-15:11
 * @author meng
 * Description: 基础 - Service 接口
 */
public interface BaseService<T> {

	PageVo<T> findPage(PageBo<?> pageBo);

}
