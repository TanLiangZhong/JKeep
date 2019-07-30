package com.ml.jkeep.common.service.impl;

import com.ml.jkeep.common.bo.PageBo;
import com.ml.jkeep.common.repository.BaseRepository;
import com.ml.jkeep.common.service.BaseService;
import com.ml.jkeep.common.vo.PageVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Optional;

/**
 * 基础 - Service
 *
 * @author liangzhong
 * @date 2019/1/17 10:44
 */
public abstract class BaseServiceImpl<R extends BaseRepository<T, ID>, T, ID> implements BaseService<T, ID> {

    @Autowired(required = false)
    protected R r;

    /**
     * Saves a given entity. Use the returned instance for further operations as the save operation might have changed the
     * entity instance completely.
     *
     * @param entity must not be {@literal null}.
     * @return the saved entity will never be {@literal null}.
     */
    @Override
    public <S extends T> S save(S entity) {
        return r.save(entity);
    }

    /**
     * Saves all given entities.
     *
     * @param entities must not be {@literal null}.
     * @return the saved entities will never be {@literal null}.
     * @throws IllegalArgumentException in case the given entity is {@literal null}.
     */
    @Override
    public <S extends T> Iterable<S> saveAll(Iterable<S> entities) {
        return r.saveAll(entities);
    }

    /**
     * Retrieves an entity by its id.
     *
     * @param id must not be {@literal null}.
     * @return the entity with the given id or {@literal Optional#empty()} if none found
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @Override
    public Optional<T> findById(ID id) {
        return r.findById(id);
    }

    /**
     * Returns whether an entity with the given id exists.
     *
     * @param id must not be {@literal null}.
     * @return {@literal true} if an entity with the given id exists, {@literal false} otherwise.
     * @throws IllegalArgumentException if {@code id} is {@literal null}.
     */
    @Override
    public boolean existsById(ID id) {
        return r.existsById(id);
    }

    /**
     * Returns all instances of the type.
     *
     * @return all entities
     */
    @Override
    public Iterable<T> findAll() {
        return r.findAll();
    }

    /**
     * Returns all instances of the type with the given IDs.
     *
     * @param ids
     * @return entities
     */
    @Override
    public Iterable<T> findAllById(Iterable<ID> ids) {
        return r.findAllById(ids);
    }

    /**
     * Deletes the entity with the given id.
     *
     * @param id must not be {@literal null}.
     * @throws IllegalArgumentException in case the given {@code id} is {@literal null}
     */
    @Override
    public void deleteById(ID id) {
        r.deleteById(id);
    }

    /**
     * Deletes the given entities.
     *
     * @param entities
     * @throws IllegalArgumentException in case the given {@link Iterable} is {@literal null}.
     */
    @Override
    public void deleteAll(Iterable<? extends T> entities) {
        r.deleteAll(entities);
    }

    /**
     * Deletes all entities managed by the repository.
     */
    @Override
    public void deleteAll() {
        r.deleteAll();
    }

    /**
     * Returns all entities sorted by the given options.
     *
     * @param sort
     * @return all entities sorted by the given options
     */
    @Override
    public Iterable<T> findAll(Sort sort) {
        return r.findAll(sort);
    }

    /**
     * 简单分页查询
     * <p>简单的过滤条件</p>
     *
     * @param pageBo 分页对象
     * @return a {@link Page} of entities matching the given
     */
    @Override
    public PageVo<T> findSimplePage(PageBo<T> pageBo) {
        Page<T> page;
        // page 从 0 开始
        int pageNo = pageBo.getPage() - 1;
        if (pageBo.getParam() == null) {
            page = r.findAll(PageRequest.of(pageNo, pageBo.getSize(), Sort.Direction.DESC, pageBo.getSortableField()));
        } else {
            page = r.findAll(Example.of(pageBo.getParam()), PageRequest.of(pageNo, pageBo.getSize(), Sort.Direction.DESC, pageBo.getSortableField()));
        }
        PageVo<T> pageVo = new PageVo<>();
        pageVo.setPage(pageBo.getPage());
        pageVo.setSize(pageBo.getSize());
        pageVo.setTotal(page.getTotalElements());
        pageVo.setTotalPages(page.getTotalPages());
        pageVo.setData(page.getContent());
        return pageVo;
    }
}
