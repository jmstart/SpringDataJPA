package com.jiaming.test;

import com.jiaming.dao.CustomerDao;
import com.jiaming.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author jmstart
 * @create 2020-04-19 9:37
 *
 * 使用 jpql语言操作
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class JpqlTest {

    //注入
    @Autowired
    private CustomerDao customerDao;

    /**
     * 测试按名称查找客户
     */
    @Test
    public void testFindJpql() {
        Customer customer = customerDao.findJpql("张三");
        System.out.println(customer);
    }

    /**
     * 测试按名称和id查找客户
     */
    @Test
    public void testFindCustNameAndCustId() {
        Customer customer = customerDao.findCustNameAndCustId("赵六", 6l);
        System.out.println(customer);
    }

    /**
     * 测试更新客户姓名
     *
     * 修改/删除:
     * 都需要加事务注解 @Transactional支持,
     * 但是数据会默认回滚,所以数据库没有变化,
     * 我们需要把自动回滚的功能关掉,所以还需要一个注解 @Rollback
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testUpdateCustomer() {
        customerDao.updateCustomer(7l, "刘七");
    }

    /**
     * 测试使用sql语句查询所有客户
     */
    @Test
    public void testFindSql() {
        List<Object[]> list = customerDao.findSql();
        for (Object[] customer : list) {
            System.out.println(Arrays.toString(customer));
        }
    }

    /**
     * 测试sql语句条件查询
     */
    @Test
    public void testFindCustomerName() {
        Customer customer = customerDao.findCustomerName("张%");
        System.out.println(customer);
    }

    /**
     * 测试命名规则查询
     */
    @Test
    public void testFindByCustName() {
        Customer customer = customerDao.findByCustName("张三");
        System.out.println(customer);
    }

    /**
     * 测试命名规则模糊查询
     */
    @Test
    public void testFindByCustNameLike() {
        Customer customer = customerDao.findByCustNameLike("张%");
        System.out.println(customer);
    }

    /**
     * 测试多条件查询
     */
    @Test
    public void testFindByCustNameLikeAndCustIndustry() {
        Customer customer = customerDao.findByCustNameLikeAndCustIndustry("张%", "Java");
        System.out.println(customer);
    }
}
