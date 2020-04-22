package com.jiaming.test;

import com.jiaming.dao.CustomerDao;
import com.jiaming.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jmstart
 * @create 2020-04-19 8:36
 *
 * SpringData JPA 查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class CustomerDaoTest02 {

    //注入客户对象
    @Autowired
    private CustomerDao customerDao;

    /**
     * 测试查询客户数量
     */
    @Test
    public void testCount() {
        long count = customerDao.count();
        System.out.println("客户数量: " + count);
    }

    /**
     * 测试 id为3的是否存在
     */
    @Test
    public void testExists() {
        boolean b = customerDao.exists(3l);
        System.out.println("id为3的客户是否存在: " + b);
    }

    /**
     * 测试根据 id查询客户
     * 使用 GetOne时,要加上事务注解支持
     * 延迟加载
     */
    @Test
    @Transactional
    public void testGetOne() {
        Customer customer = customerDao.getOne(3l);
        System.out.println(customer);
    }

}
