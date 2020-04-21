package com.jiaming.test;

import com.jiaming.utils.JpaUtils;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import java.util.List;

/**
 * @author jmstart
 * @create 2020-04-14 11:16
 *
 * 测试 Jpql查询
 * jpql: Java Persistence Query Language
 */
public class JpqlTest {

    /**
     * 测试查询全部客户
     */
    @Test
    public void testFindAll() {
        //获取实体管理对象
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //查询所有客户
        //先写 jpql语句,与普通 sql很相似
        String jpql = "from Customer";
        //在执行 query查询对象
        Query query = em.createQuery(jpql);
        //发送查询,并封装结果集
        List list = query.getResultList();
        //遍历
        for (Object obj : list){
            System.out.println(obj);
        }

        //事务提交
        tx.commit();
        //释放资源
        em.close();
    }

    /**
     * 测试排序查询:
     *      倒序查询全部客户(根据 id倒序)
     */
    @Test
    public void testOrders() {
        //获取实体管理对象
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //倒序查询所有客户
        //先写 jpql语句,与普通 sql很相似   custId是属性
        String jpql = "from Customer order by custId desc";
        //在执行 query查询对象
        Query query = em.createQuery(jpql);
        //发送查询,并封装结果集
        List list = query.getResultList();
        //遍历
        for (Object obj : list){
            System.out.println(obj);
        }

        //事务提交
        tx.commit();
        //释放资源
        em.close();
    }

    /**
     * 测试统计查询
     */
    @Test
    public void testCount() {
        //获取实体管理对象
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //统计查询所有客户
        //先写 jpql语句,与普通 sql很相似  custId是属性,下面二个jpql都可以
        //String jpql = "select count(custId) from Customer";
        String jpql = "select count(*) from Customer";
        //在执行 query查询对象
        Query query = em.createQuery(jpql);
        //得到结果
        Object result = query.getSingleResult();
        //输出结果
        System.out.println(result);

        //事务提交
        tx.commit();
        //释放资源
        em.close();
    }

    /**
     *测试分页查询
     */
    @Test
    public void testPaged() {
        //获取实体管理对象
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //分页查询所有客户
        //先写 jpql语句,与普通 sql很相似
        String jpql = "from Customer";
        //在执行 query查询对象
        Query query = em.createQuery(jpql);
        //对分页参数赋值
        //1.起始索引
        query.setFirstResult(1);
        //2.每页的条数
        query.setMaxResults(2);
        //得到结果
        List list = query.getResultList();
        //遍历
        for (Object obj : list) {
            System.out.println(obj);
        }

        //事务提交
        tx.commit();
        //释放资源
        em.close();
    }

    /**
     * 测试条件查询
     */
    @Test
    public void testCondition() {
        //获取实体管理对象
        EntityManager em = JpaUtils.getEntityManager();
        //开启事务
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        //条件查询所有客户
        //先写 jpql语句,与普通 sql很相似   custName属性
        String jpql = "from Customer where custName like ?";
        //在执行 query查询对象
        Query query = em.createQuery(jpql);
        //为占位符赋值
        query.setParameter(1, "欣%");
        //得到结果
        List list = query.getResultList();
        //遍历
        for (Object obj : list) {
            System.out.println(obj);
        }

        //事务提交
        tx.commit();
        //释放资源
        em.close();
    }
}
