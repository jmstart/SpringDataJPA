package com.jiaming.test;

import com.jiaming.domian.Customer;
import com.jiaming.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 * @author jmstart
 * @create 2020-04-12 10:20
 */
public class JpaTest {
    /**
     * 使用 Jpa步骤:
     *      1.加载配置文件创建工厂(实体管理器工厂)对象
     *      2.通过工厂获取实体管理器
     *      3.获得事务对象,开启事务
     *      4.执行增删改查
     *      5.提交事务(回滚事务)
     *      6.释放资源
     */

    /**
     * 测试 Jpa保存
     * 保存一个客户到数据库
     */
    @Test
    public void testSave() {
        //1.加载配置文件创建工厂(实体管理器工厂)对象
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
        //2.通过工厂获取实体管理器
        EntityManager em = factory.createEntityManager();
        //3.获得事务对象,开启事务
        EntityTransaction tx = em.getTransaction(); //获取事务对象
        tx.begin(); //开启事务
        //4.执行增删改查
        Customer customer = new Customer();
        customer.setCustName("孙七");
        customer.setCustIndustry("C++");
        customer.setCustLevel("高级程序员");
        //保存
        em.persist(customer);
        //5.提交事务
        tx.commit();
        //6.释放资源
        em.close();
        factory.close();
    }

    /**
     * 测试 JpaUtils工具类
     */
    @Test
    public void testJpaUtils() {
        //获取实体管理对象
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //添加客户
        Customer customer = new Customer();
        customer.setCustName("赵六");
        customer.setCustIndustry("Android");
        customer.setCustLevel("中级程序员");
        //保存
        em.persist(customer);
        //提交
        tx.commit();
        //释放资源,实体管理工厂对象不用关闭了,因为它现在是公共的
        em.close();
    }

    /**
     * 测试查询(根据 id查询客户)
     * 立即加载:
     *      得到对象本身
     *      调用find方法就执行sql查询数据库
     */
    @Test
    public void testFind() {
        //获取实体管理对象
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //查询
        Customer customer = em.find(Customer.class, 1l);
        System.out.println(customer);
        //事务提交
        tx.commit();
        //释放资源
        em.close();
    }

    /**
     * 测试查询(根据 id查询客户)
     * 延迟加载(懒加载):
     *      得到一个动态代理对象
     *      什么时候用,什么时候发送sql语句去查询数据库
     * 推荐使用延迟加载
     */
    @Test
    public void testReference() {
        //获取实体管理对象
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //查询
        Customer customer = em.getReference(Customer.class, 1l);
        System.out.println(customer);
        //事务提交
        tx.commit();
        //释放资源
        em.close();
    }

    /**
     * 测试删除(先查在删)
     */
    @Test
    public void testRemove() {
        //获取实体管理对象
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //删除客户,先查在删
        //1.要先根据 id查到客户
        Customer customer = em.find(Customer.class, 4l);
        //2.在删除客户 remove方法接收的是对象
        em.remove(customer);
        //事务提交
        tx.commit();
        //释放资源
        em.close();
    }

    /**
     * 测试更新(先查在改)
     */
    @Test
    public void testMerge() {
        //获取实体管理对象
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();
        //更新客户,先查在改
        //1.要先根据 id查到客户
        Customer customer = em.find(Customer.class, 2l);
        //2.在更新客户 merge方法也是接收的对象
        customer.setCustAddress("北京");
        em.merge(customer);
        //事务提交
        tx.commit();
        //释放资源
        em.close();
    }


}
