package com.ml.jkeep.service.system.impl;

import com.ml.jkeep.common.annotation.CriteriaBuilderQuery;
import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.enums.QueryPolicy;
import com.ml.jkeep.common.utils.JpaUtils;
import com.ml.jkeep.common.vo.PageVo;
import com.ml.jkeep.jpa.BaseRepository;
import com.ml.jkeep.service.system.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * Date: 2019/8/16-10:39
 *
 * @author meng
 * Description: 基础 - Service
 */
@Slf4j
public abstract class BaseServiceImpl<D extends BaseRepository<T, ID>, T, ID> implements BaseService<T, ID> {

    @Autowired(required = false)
    protected D dao;

    @Override
    public PageVo<T> findPage(PageBo<?> pageBo) {
        List<Sort.Order> orders = new ArrayList<>();
        for (String key : pageBo.getOrderby().keySet()) {
            orders.add(new Sort.Order(Sort.Direction.DESC.toString().toLowerCase().equals(pageBo.getOrderby().get(key)) ? Sort.Direction.DESC : Sort.Direction.ASC, key));
        }
        PageRequest pageRequest = PageRequest.of(pageBo.getPage() - 1, pageBo.getSize(), Sort.by(orders));
        Specification<T> specification = (Specification<T>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();
            Class<?> clazz = pageBo.getParam().getClass();
            try {
                for (Field field : clazz.getDeclaredFields()) {
                    String name = field.getName();
                    field.setAccessible(true);
                    Object value = field.get(pageBo.getParam());
                    CriteriaBuilderQuery criteriaBuilderQuery = field.getAnnotation(CriteriaBuilderQuery.class);
                    if (criteriaBuilderQuery != null) {
                        if (criteriaBuilderQuery.queryPolicy().equals(QueryPolicy.EQUAL)) {
                            if (StringUtils.isNotBlank(value.toString())) {
                                predicates.add(cb.equal(root.get(name), value));
                            }
                        } else if (criteriaBuilderQuery.queryPolicy().equals(QueryPolicy.LIKE)) {
                            if (StringUtils.isNotBlank(value.toString())) {
                                predicates.add(cb.like(root.get(name), JpaUtils.addWildcard(value.toString())));
                            }
                        } else if (criteriaBuilderQuery.queryPolicy().equals(QueryPolicy.GREATERTHANOREQUALTO)) {
                            if (StringUtils.isNotBlank(value.toString())) {
                                predicates.add(cb.greaterThanOrEqualTo(root.get(name), value.toString()));
                            }
                        } else if (criteriaBuilderQuery.queryPolicy().equals(QueryPolicy.LESSTHANOREQUALTO)) {
                            if (StringUtils.isNotBlank(value.toString())) {
                                predicates.add(cb.lessThanOrEqualTo(root.get(name), value.toString()));
                            }
                        }
                        /**
                         * 待添加 ...
                         */
                    } else {
                        if (StringUtils.isNotBlank(value.toString())) {
                            predicates.add(cb.equal(root.get(name), value));
                        }
                    }
                    log.debug(String.format("Name : {%s} , Value : {%s} ", name, value));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            Predicate[] pre = new Predicate[predicates.size()];
            return query.where(predicates.toArray(pre)).getRestriction();
        };
        Page<T> page = dao.findAll(specification, pageRequest);
        return new PageVo<>(pageBo.getPage(), pageBo.getSize(), page.getTotalElements(), page.getTotalPages(), page.getContent());
    }

    @Override
    public Optional<T> findById(ID id) {
        return dao.findById(id);
    }
}
