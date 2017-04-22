package com.github.thamvannguyen.logging.dao.repository;


import com.github.thamvannguyen.logging.dao.entity.LogDetails;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by huynhduychuong on 10/9/2016.
 */
public interface LogDetailsRepository extends JpaRepository<LogDetails, String> {
}
