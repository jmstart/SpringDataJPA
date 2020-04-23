package com.jiaming.test;

import com.jiaming.dao.RoleDao;
import com.jiaming.dao.UserDao;
import com.jiaming.domain.Role;
import com.jiaming.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.util.Set;

/**
 * @author jmstart
 * @create 2020-04-21 19:58
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class ManyToManyTest {

    //注入
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    /**
     * 保存用户和角色
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testSave() {

        //创建用户
        User user = new User();
        user.setUserName("王小明");

        //创建角色
        Role role = new Role();
        role.setRoleName("Java程序员");

        /**
         * 关联多对多
         * 如果二个表都配置关系会报主键冲突的错误
         * 解决冲突的方法是: 放弃一方的主键维护权
         * 放弃原则: 谁被选择就放弃谁,被动一方的放弃
         */
        user.getRoles().add(role); //可以对主键维护
        role.getUsers().add(user); //可以对主键维护

        userDao.save(user);
        roleDao.save(role);

    }

    /**
     * 测试级联添加
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascateSave() {

        //创建用户
        User user = new User();
        user.setUserName("王小明");

        //创建角色
        Role role = new Role();
        role.setRoleName("Java程序员");

        user.getRoles().add(role); //可以对主键维护
        role.getUsers().add(user);

        //只添加用户,测试级联
        userDao.save(user);
    }

    /**
     * 测试级联删除
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascateDelete() {
        //1.查询
        User user = userDao.findOne(1l);
        //2.只删除用户,测试级联
        userDao.delete(user);
    }

    /**
     * 测试级联查询
     */
    @Test
    @Transactional
    @Rollback(value = false)
    public void testCascateSelect() {
        //级联查询
        User user = userDao.findOne(2l);
        //获得角色
        Set<Role> roles = user.getRoles();

        for (Role role : roles) {
            System.out.println(role);
        }

    }


}
