package com.jiaming.dao;

import com.jiaming.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author jmstart
 * @create 2020-04-21 19:35
 */
public interface UserDao extends JpaRepository<User, Long>, JpaSpecificationExecutor<User> {
}
