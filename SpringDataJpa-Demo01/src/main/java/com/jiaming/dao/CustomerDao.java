package com.jiaming.dao;

import com.jiaming.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author jmstart
 * @create 2020-04-16 9:16
 *
 * 写个接口继承二个接口,不用写实现类就可以使用
 *
 * 符合 Spring-data-jpa的dao接口代码规范
 *      继承二个接口:
 *         JpaRepository<操作的实体类类型,主键的类型>
 *             :封装了基本的 CRUD操作
 *         JpaSpecificationExecutor<操作的实体类类型>
 *             :封装了复杂查询操作(分页)
 */
public interface CustomerDao extends JpaRepository<Customer, Long>, JpaSpecificationExecutor<Customer> {

    /**
     * 案例: 根据客户名称查询客户
     *      使用jpql方式查询,jpql针对的是类和属性,例如下面的Customer把C写成c就会报错
     *      记住是类名和属性名不能写错
     *
     *   jpql: from Customer where custName = ?
     *
     *   配置jpql语句,使用 @Query注解
     */
    @Query(value = "from Customer where custName = ?")
    public Customer findJpql(String custName);

    /**
     * 案例: 根据客户名称和客户 id查询客户
     *
     *  jpql: from Customer where custName = ? and custId = ?
     */
    @Query(value = "from Customer where custName = ? and custId = ?")
    public Customer findCustNameAndCustId(String custName, Long custId);

    /**
     * 案例: 根据 id更新客户姓名
     *
     *  sql: update cust_customer set cust_name = ? where cust_id = ?
     *
     *  jpql: update Customer set custName = ? where custId = ?
     *
     *  如果参数和查询语句位置不匹配,可以使用加索引的方式
     *              在 ?后面加索引
     *
     *  更新的话,还需要声明一个注解: @Modifying
     */
    @Query(value = "update Customer set custName = ?2 where custId = ?1")
    @Modifying
    public void updateCustomer(Long custId, String custName);

    /**
     * 使用sql语句的形式来查询
     * 案例: 查询全部客户
     *
     * sql: select * from cst_customer
     */
    @Query(value = "select * from cst_customer", nativeQuery = true)
    public List<Object[]> findSql();

    /**
     * 案例: sql条件查询
     *
     * sql: select * from cst_customer where cust_name like ?
     */
    @Query(value = "select * from cst_customer where cust_name like ?", nativeQuery = true)
    public Customer findCustomerName(String custName);

    /**
     * 以命名规则来查询: 其实就是SpringDataJpa对jpql封装好了的方法,调用即可
     *      命名约定:
     *          findBy : 查询
     *          findBy + 属性名称
     *          例如: findByCustName : 根据客户名称查询数据库
     */
    public Customer findByCustName(String custName);

    /**
     * 已命名规则来查询:
     *     模糊查询
     *  findBy + 属性名称 + 查询方式
     */
    public Customer findByCustNameLike(String custName);

    /**
     * 多条件查询方式
     *      findBy + 属性名称 + 查询方式 + 连接符(and | or) + 属性名称 + 查询方式
     * 案例: 使用客户名称模糊匹配和客户职业的精准匹配
     */
    public Customer findByCustNameLikeAndCustIndustry(String custName, String custIndustry);


}
