package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.vo.PageVo;
import com.ml.jkeep.jpa.system.bo.SysLogSearchBo;
import com.ml.jkeep.jpa.system.entity.SysLog;
import com.ml.jkeep.jpa.system.repository.SysLogRepository;
import com.ml.jkeep.service.system.SysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统日志
 *
 * @author 谭良忠
 * @date 2019/7/23 11:02
 */
@Service
public class SysLogServiceImpl implements SysLogService {

    @Autowired
    private SysLogRepository sysLogRepository;

    @Override
    public PageVo<SysLog> findPage(PageBo<SysLogSearchBo> pageBo) {
        Specification<SysLog> specification = this.convertSearchParam(pageBo.getParam());
        Page<SysLog> page = sysLogRepository.findAll(specification,
                PageRequest.of(pageBo.getPage() - 1, pageBo.getSize(), Sort.Direction.DESC, pageBo.getSortableField()));
        return new PageVo<>(pageBo.getPage(), pageBo.getSize(), page.getTotalElements(), page.getTotalPages(), page.getContent());
    }

    private Specification<SysLog> convertSearchParam(SysLogSearchBo param) {
        if (param == null) {
            return null;
        }
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (!StringUtils.isEmpty(param.getUsername())) {
                predicates.add(criteriaBuilder.like(root.get("username"), param.getUsername()));
            }
            if (!StringUtils.isEmpty(param.getBeginTime())) {
                predicates.add(criteriaBuilder.greaterThanOrEqualTo(root.get("gmtCreated"), param.getBeginTime()));
            }
            if (!StringUtils.isEmpty(param.getEndTime())) {
                predicates.add(criteriaBuilder.lessThanOrEqualTo(root.get("gmtCreated"), param.getBeginTime()));
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

    @Async
    @Override
    public void insertLog(SysLog log) {
        sysLogRepository.save(log);
    }
}
