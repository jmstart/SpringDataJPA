package com.jiaming.test;

import com.jiaming.dao.CustomerDao;
import com.jiaming.dao.LinkManDao;
import com.jiaming.domain.Customer;
import com.jiaming.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * @author jmstart
 * @create 2020-04-21 20:45
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ObjectQueryTest {

    //注入Dao
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 测试对象导航查询(从一的一方向多的一方查询)
     * 延迟加载 getOne()
     *
     * 报错:
     * could not initialize proxy - no Session
     * 加事务注解
     */
    @Test
    @Transactional //解决 no Session问题
    public void testQuery01() {
        //查询
        Customer customer = customerDao.getOne(2l);

        //直接调用就可以得到此客户下的所有联系人
        Set<LinkMan> linkmans = customer.getLinkmans();

        //遍历
        for (LinkMan linkman : linkmans) {
            System.out.println(linkman);
        }
    }

    /**
     * 立即加载
     */
    @Test
    @Transactional //解决 no Session问题
    public void testQuery02() {
        //查询
        Customer customer = customerDao.findOne(2l);

        //直接调用就可以得到此客户下的所有联系人
        Set<LinkMan> linkmans = customer.getLinkmans();

        //遍历
        for (LinkMan linkman : linkmans) {
            System.out.println(linkman);
        }
    }

    /**
     * 从联系人一方来查找客户一方(从多的一方开始查询)
     */
    @Test
    @Transactional //解决 no Session问题
    public void testQuery03() {
        //查询
        LinkMan linkMan = linkManDao.findOne(2l);

        //直接调用就可以得到此客户下的所有联系人
        Customer customer = linkMan.getCustomer();

        //输出客户
        System.out.println(customer);

    }


}
