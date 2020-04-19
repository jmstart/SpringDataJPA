# SpringData JPA简介
一. ORM介绍

  1.1 ORM概述
 
    ORM(Object-Relational Mapping)表示对象关系映射。在面向对象的软件开发中，通过ORM，就可以把对象映射到关系型数据库中。只要有一套程序能够做到建立对象与数据库的关联，操作对象就可以直接操作数据库数据，就可以说这套程序实现了ORM对象关系映射
    简单的说：ORM就是建立实体类和数据库表之间的关系，从而达到操作实体类就相当于操作数据库表的目的。
  
  1.2 为什么使用ORM 
   
    当实现一个应用程序时（不使用O/R Mapping），我们可能会写特别多数据访问层的代码，从数据库保存数据、修改数据、删除数据，而这些代码都是重复的。而使用ORM则会大大减少重复性代码。对象关系映射（Object Relational Mapping，简称ORM），主要实现程序对象到关系数据库数据的映射。
   
  1.3 常见ORM框架
  
    Mybatis（ibatis）、Hibernate、Jpa

二. Hibernate与JPA的概述
    
   2.1 Hibernate概述

    Hibernate是一个开放源代码的对象关系映射框架，它对JDBC进行了非常轻量级的对象封装，它将POJO与数据库表建立映射关系，是一个全自动的orm框架，hibernate可以自动生成SQL语句，自动执行，使得Java程序员可以随心所欲的使用对象编程思维来操纵数据库。
    
   2.2 JPA概述 
   
    JPA的全称是Java Persistence API， 即Java 持久化API，是SUN公司推出的一套基于ORM的规范，内部是由一系列的接口和抽象类构成。
    JPA通过JDK 5.0注解描述对象－关系表的映射关系，并将运行期的实体对象持久化到数据库中。
    
   2.3 JPA的优势
   
   1. 标准化
   
    JPA 是 JCP 组织发布的 Java EE 标准之一，因此任何声称符合 JPA 标准的框架都遵循同样的架构，提供相同的访问API，这保证了基于JPA开发的企业应用能够经过少量的修改就能够在不同的JPA框架下运行。

  2. 容器级特性的支持
  
    JPA框架中支持大数据集、事务、并发等容器级事务，这使得 JPA 超越了简单持久化框架的局限，在企业应用发挥更大的作用。

  3. 简单方便
  
    JPA的主要目标之一就是提供更加简单的编程模型：在JPA框架下创建实体和创建Java 类一样简单，没有任何的约束和限制，只需要使用 javax.persistence.Entity进行注释，JPA的框架和接口也都非常简单，没有太多特别的规则和设计模式的要求，开发者可以很容易的掌握。JPA基于非侵入式原则设计，因此可以很容易的和其它框架或者容器集成

  4. 查询能力

    JPA的查询语言是面向对象而非面向数据库的，它以面向对象的自然语法构造查询语句，可以看成是Hibernate HQL的等价物。JPA定义了独特的JPQL（Java Persistence Query Language），JPQL是EJB QL的一种扩展，它是针对实体的一种查询语言，操作对象是实体，而不是关系数据库的表，而且能够支持批量更新和修改、JOIN、GROUP BY、HAVING 等通常只有 SQL 才能够提供的高级查询特性，甚至还能够支持子查询。

  5. 高级特性
  
    JPA 中能够支持面向对象的高级特性，如类之间的继承、多态和类之间的复杂关系，这样的支持能够让开发者最大限度的使用面向对象的模型设计企业应用，而不需要自行处理这些特性在关系数据库的持久化。
    
  2.4 JPA与hibernate的关系
   
    JPA规范本质上就是一种ORM规范，注意不是ORM框架——因为JPA并未提供ORM实现，它只是制订了一些规范，提供了一些编程的API接口，但具体实现则由服务厂商来提供实现。     
    JPA和Hibernate的关系就像JDBC和JDBC驱动的关系，JPA是规范，Hibernate除了作为ORM框架之外，它也是一种JPA实现。JPA怎么取代Hibernate呢？JDBC规范可以驱动底层数据库吗？答案是否定的，也就是说，如果使用JPA规范进行数据库操作，底层需要hibernate作为其实现类完成数据持久化工作。

三. JPA的入门案例
   
   3.1 搭建开发环境
   
    maven工程导入坐标:
    <properties>
	  	<project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  		<project.hibernate.version>5.0.7.Final</project.hibernate.version>
	  </properties>
    
	  <dependencies>
		  <!-- junit -->
		  <dependency>
			  <groupId>junit</groupId>
			  <artifactId>junit</artifactId>
			  <version>4.12</version>
			  <scope>test</scope>
		  </dependency>

      <!-- hibernate对jpa的支持包 -->
      <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-entitymanager</artifactId>
        <version>${project.hibernate.version}</version>
      </dependency>

      <!-- c3p0 -->
      <dependency>
        <groupId>org.hibernate</groupId>
        <artifactId>hibernate-c3p0</artifactId>
        <version>${project.hibernate.version}</version>
      </dependency>

      <!-- log日志 -->
      <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
      </dependency>

      <!-- Mysql and MariaDB -->
      <dependency>
        <groupId>mysql</groupId>
        <artifactId>mysql-connector-java</artifactId>
        <version>5.1.6</version>
      </dependency>
  	</dependencies>
    
   3.2 创建客户的数据库表和客户的实体类
     
     创建客户的数据库表
     创建客户的实体类
     
   3.3 编写实体类和数据库表的映射配置
   
     使用的注解:
      @Entity
        	作用：指定当前类是实体类。
      @Table
        	作用：指定实体类和表之间的对应关系。
        	属性：
        		name：指定数据库表的名称
      @Id
        作用：指定当前字段是主键。
      @GeneratedValue
        作用：指定主键的生成方式。。
        属性：
          strategy ：指定主键生成策略。
      例:@GeneratedValue(strategy=GenerationType.IDENTITY)
      @Column
        	作用：指定实体类属性和数据库表之间的对应关系
        	属性：
        		name：指定数据库表的列名称。
        		unique：是否唯一  
        		nullable：是否可以为空  
        		inserttable：是否可以插入  
        		updateable：是否可以更新  
        		columnDefinition: 定义建表时创建此列的DDL  
        		secondaryTable: 从表名。如果此列不建在主表上（默认建在主表），该属性定义该列所在从表的名字搭建开发环境
            
   3.4 配置JPA的核心配置文件
   
    在java工程的src路径下创建一个名为META-INF的文件夹，在此文件夹下创建一个名为persistence.xml的配置文件
      <?xml version="1.0" encoding="UTF-8"?>
      <persistence xmlns="http://java.sun.com/xml/ns/persistence"
        xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
        xsi:schemaLocation="http://java.sun.com/xml/ns/persistence  
          http://java.sun.com/xml/ns/persistence/persistence_2_0.xsd"
          version="2.0">
          <!--配置持久化单元 
            name：持久化单元名称 
            transaction-type：事务类型
              RESOURCE_LOCAL：本地事务管理 
              JTA：分布式事务管理 -->
          <persistence-unit name="myJpa" transaction-type="RESOURCE_LOCAL">
            <!--配置JPA规范的服务提供商 -->
            <provider>org.hibernate.jpa.HibernatePersistenceProvider</provider>
            <properties>
              <!-- 数据库驱动 -->
              <property name="javax.persistence.jdbc.driver" value="com.mysql.jdbc.Driver" />
              <!-- 数据库地址 -->
              <property name="javax.persistence.jdbc.url" value="jdbc:mysql://localhost:3306/ssh" />
              <!-- 数据库用户名 -->
              <property name="javax.persistence.jdbc.user" value="root" />
              <!-- 数据库密码 -->
              <property name="javax.persistence.jdbc.password" value="111111" />

              <!--jpa提供者的可选配置：我们的JPA规范的提供者为hibernate，所以jpa的核心配置中兼容hibernate的配 -->
              <property name="hibernate.show_sql" value="true" />
              <property name="hibernate.format_sql" value="true" />
              <property name="hibernate.hbm2ddl.auto" value="create" />
            </properties>
          </persistence-unit>
       </persistence>
       
   3.4 实现保存操作(最原始的例子)
   
    @Test
	  public void test() {
		/**
		 * 创建实体管理类工厂，借助Persistence的静态方法获取
		 * 其中传递的参数为持久化单元名称，需要jpa配置文件中指定
		 */
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("myJpa");
		//创建实体管理类
		EntityManager em = factory.createEntityManager();
		//获取事务对象
		EntityTransaction tx = em.getTransaction();
		//开启事务
		tx.begin();
		Customer c = new Customer();
		c.setCustName("张三");
		//保存操作
		em.persist(c);
		//提交事务
		tx.commit();
		//释放资源
		em.close();
		factory.close();
  	}

四. JPA中的复杂查询
  
  4.1 JPQL简介
    
    JPQL全称Java Persistence Query Language
    基于首次在EJB2.0中引入的EJB查询语言(EJB QL),Java持久化查询语言(JPQL)是一种可移植的查询语言，旨在以面向对象表达式语言的表达式，将SQL语法和简单查询语义绑定在一起·使用这种语言编写的查询是可移植的，可以被编译成所有主流数据库服务器上的SQL。
    其特征与原生SQL语句类似，并且完全面向对象，通过"类名和属性"访问，而不是表名和表的属性。







   
   
   
   
    
    
