package com.jiaming.test;

import com.jiaming.dao.CustomerDao;
import com.jiaming.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author jmstart
 * @create 2020-04-16 9:30
 *
 * SpringData JPA 简单操作
 */
@RunWith(SpringJUnit4ClassRunner.class) //声明使用Spring提供的单元测试环境
@ContextConfiguration(locations = "classpath:applicationContext.xml") //指定Spring容器配置信息
public class CustomerDaoTest {
    //获取客户dao对象
    @Autowired
    private CustomerDao customerDao;

    /**
     * 测试查询单个客户(根据id查询)
     */
    @Test
    public void testFindOne() {
        Customer customer = customerDao.findOne(7l);
        System.out.println(customer);
    }

    /**
     * 测试查询所有客户
     */
    @Test
    public void testFindAll() {
        List<Customer> list = customerDao.findAll();
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 测试保存客户
     *      save:保存或更新
     *          没主键,保存
     *          有主键,更新
     */
    @Test
    public void testSave() {
        //没主键,保存
        Customer customer = new Customer();
        customer.setCustName("猪八");
        customer.setCustIndustry("产品经理");
        customerDao.save(customer);
    }

    @Test
    public void testUpdate() {
        //有主键,更新
        Customer customer = new Customer();
        customer.setCustId(3l);
        customer.setCustName("唐三");
        customer.setCustIndustry("销售");
        customerDao.save(customer);
    }

    /**
     * 测试删除
      */
    @Test
    public void testDelete() {
        customerDao.delete(5l);
    }

}
