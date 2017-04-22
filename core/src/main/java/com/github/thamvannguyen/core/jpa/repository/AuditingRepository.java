package com.github.thamvannguyen.core.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huynhduychuong on 10/9/2016.
 */
@NoRepositoryBean
public interface AuditingRepository<T, ID extends Serializable> extends JpaRepository<T, ID> {
    T findByIdAndDeletedIsFalse(ID id);

    @Query("SELECT e FROM #{#entityName} e WHERE e.deleted = false")
    List<T> findAllNotDeleted();
}
