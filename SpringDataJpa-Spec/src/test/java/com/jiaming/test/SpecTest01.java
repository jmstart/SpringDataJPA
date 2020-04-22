package com.jiaming.test;

import com.jiaming.dao.CustomerDao;
import com.jiaming.domain.Customer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.criteria.*;
import java.util.List;

/**
 * @author jmstart
 * @create 2020-04-21 8:25
 *
 * SpringData Jpa 动态查询
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SpecTest01 {

    @Autowired
    private CustomerDao customerDao;

    /**
     * 根据条件,查询单个对象
     */
    @Test
    public void testSpec01() {
        /**
         * 匿名内部类
         * 自定义查询条件:
         *      1.实现 Specification接口,需要给泛型(查询的对象类型)
         *      2.实现 toPredicate方法(构造查询条件)
         *      3.借助下面方法中二个参数(root, criteriaBuilder)
         *              root: 获取查询的对象属性
         *              criteriaBuilder: 构造查询条件,内部封装了很多查询条件(模糊匹配,精准匹配)
         *
         * 案例: 根据客户名称查询
         */
        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //1.获取比较的属性
                Path<Object> custName = root.get("custName");
                //2.构造查询条件
                Predicate predicate = criteriaBuilder.equal(custName, "赵六"); //精准匹配
                //返回结果
                return predicate;
            }
        };

        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 多个条件查询客户
     *
     * 案例: 根据客户名称和客户行业查询
     */
    @Test
    public void testSpec02() {

        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取属性
                Path<Object> custName = root.get("custName");
                Path<Object> custIndustry = root.get("custIndustry");

                //构造查询条件
                Predicate p1 = criteriaBuilder.equal(custName, "刘七");
                Predicate p2 = criteriaBuilder.equal(custIndustry, "C++");

                //将多个查询条件组合起来(组合起来有二种关系,一种是与(and),一种是或(or))
                Predicate predicate = criteriaBuilder.and(p1, p2);

                //返回结果
                return predicate;
            }
        };

        Customer customer = customerDao.findOne(spec);
        System.out.println(customer);
    }

    /**
     * 案例: 根据客户名称模糊匹配
     */
    @Test
    public void testSpec03() {

        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取属性
                Path<Object> custName = root.get("custName");

                //查询条件
                //使用 like要得到它的参数类型所以用 as方法获得其参数类型
                Predicate predicate = criteriaBuilder.like(custName.as(String.class), "猪%");

                return predicate;
            }
        };

        //单客户
        //Customer customer = customerDao.findOne(spec);
        //System.out.println(customer);

        //多客户
        List<Customer> list = customerDao.findAll(spec);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }

    /**
     * 排序
     */
    @Test
    public void testspec04() {

        Specification<Customer> spec = new Specification<Customer>() {
            @Override
            public Predicate toPredicate(Root<Customer> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //获取属性
                Path<Object> custName = root.get("custName");

                //查询条件
                Predicate predicate = criteriaBuilder.like(custName.as(String.class), "猪%");

                return predicate;
            }
        };

        //添加排序
        //创建排序对象,需要调用构造方法实例化sort参数
        //第一个参数,倒叙或正序
        //第二个参数,排序的属性名(根据什么条件排序)
        Sort sort = new Sort(Sort.Direction.DESC, "custId");

        List<Customer> list = customerDao.findAll(spec, sort);
        for (Customer customer : list) {
            System.out.println(customer);
        }
    }


    /**
     * 分页查询
     */
    @Test
    public void testspec05() {

        //代表没有条件,直接查询所有
        Specification spec = null;

        //pageable是一个接口,所以使用它的实现类
        /**
         * 调用 PageRequest构造函数,添加参数
         *      第一个参数: 从第几页数据开始(从第几个数据开始)
         *      第二个参数: 查询几条
         */
        Pageable pageable = new PageRequest(0, 2);

        Page<Customer> page = customerDao.findAll(spec, pageable);

        System.out.println(page.getTotalElements()); //数据的总条数
        System.out.println(page.getTotalPages()); //总页数
        System.out.println(page.getContent()); //得到的数据集合
    }

}
