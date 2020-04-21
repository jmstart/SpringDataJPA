package com.jiaming.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * @author jmstart
 * @create 2020-04-14 9:00
 *
 * Jpa工具包
 *      解决实体管理工厂浪费资源问题和耗时问题
 *
 * 解决思路使用静态代码块,把它变成一个公共的资源,创建一次即可
 */
public class JpaUtils {

    //变成成员变量
    private static EntityManagerFactory factory;

    static {
        //创建实体管理工厂对象
        factory = Persistence.createEntityManagerFactory("myJpa");
    }

    /**
     * 获取实体管理对象
     * @return
     */
    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

}
