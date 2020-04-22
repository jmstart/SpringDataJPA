package com.jiaming.test;

import com.jiaming.dao.CustomerDao;
import com.jiaming.dao.LinkManDao;
import com.jiaming.domain.Customer;
import com.jiaming.domain.LinkMan;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author jmstart
 * @create 2020-04-21 17:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class OneToManyTest {

    //注入Dao
    @Autowired
    private CustomerDao customerDao;
    @Autowired
    private LinkManDao linkManDao;

    /**
     * 保存一个客户,保存一个联系人
     */
    @Test
    @Transactional //配置事务
    @Rollback(value = false) //关闭回滚
    public void testSave() {
        //创建一个客户
        Customer customer = new Customer();
        customer.setCustName("阿里");
        customer.setCustIndustry("Java");
        customer.setCustLevel("高级架构师");
        customer.setCustAddress("杭州");
        //创建一个联系人
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("王小明");

        //关联客户和联系人,维护外键,下面二种关联方式都可以
        customer.getLinkmans().add(linkMan); //会多产生一条语句 update维护外键
        linkMan.setCustomer(customer); //在保存的时候就维护了外键

        //保存客户,联系人
        customerDao.save(customer);
        linkManDao.save(linkMan);
    }

    /**
     * 级联添加:
     *      保存一个客户的同时,保存所有联系人
     * 级联的使用:
     *      需要在操作的主体类上,配置cascate属性
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascateSave() {
        //创建一个客户
        Customer customer = new Customer();
        customer.setCustName("阿里");
        //创建一个联系人
        LinkMan linkMan = new LinkMan();
        linkMan.setLkmName("王小明");

        //关联
        customer.getLinkmans().add(linkMan);
        linkMan.setCustomer(customer);

        //只保存客户,测试级联保存
        customerDao.save(customer);
    }

    /**
     * 级联删除
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascateDelete() {
        //1.先查询
        Customer customer = customerDao.findOne(1l);
        //2.在删除,只删除客户,测试级联删除
        customerDao.delete(customer);
    }

}
