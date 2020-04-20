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
        	作用：指定当前类是实体类
		
      @Table
        	作用：指定实体类和表之间的对应关系
        	属性：
        		name：指定数据库表的名称
			
      @Id
        作用：指定当前字段是主键
	
      @GeneratedValue
        作用：指定主键的生成方式
        属性：
          strategy ：指定主键生成策略
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

五. SpringData JPA的概述
	
   5.1 SpringData JPA的简介
   
    SpringData JPA 是 Spring 基于ORM框架、JPA 规范的基础上封装的一套JPA应用框架，可使开发者用极简的代码即可实现对数据库的访问和操作。它提供了包括增删改查等在内的常用功能，且易于扩展！学习并使用 Spring Data JPA 可以极大提高开发效率！
    SpringData JPA 让我们解脱了DAO层的操作，基本上所有CRUD都可以依赖于它来实现,在实际的工作工程中，推荐使用SpringData JPA + ORM（如：hibernate）完成操作，这样在切换不同的ORM框架时提供了极大的方便，同时也使数据库层操作更加简单，方便解耦。
    
   5.2 SpringData JPA的特性
   
    SpringData Jpa 极大简化了数据库访问层代码。 如何简化的呢？ 使用了SpringDataJpa，我们的dao层中只需要写接口，就自动具有了增删改查、分页查询等方法。
   
   5.3 SpringData JPA与JPA和hibernate之间的关系
    
    JPA是一套规范，内部是有接口和抽象类组成的。hibernate是一套成熟的ORM框架，而且Hibernate实现了JPA规范，所以也可以称hibernate为JPA的一种实现方式，我们使用JPA的API编程，意味着站在更高的角度上看待问题（面向接口编程）
    SpringData JPA是Spring提供的一套对JPA操作更加高级的封装，是在JPA规范下的专门用来进行数据持久化的解决方案。

六. SpringData JPA的快速入门

   6.1 需求说明
    
    SpringData JPA完成客户的基本CRUD操作
    
   6.2 搭建SpringData JPA的开发环境
    
    使用Spring Data JPA，需要整合Spring与Spring Data JPA，并且需要提供JPA的服务提供者hibernate，所以需要导入spring相关坐标，hibernate坐标，数据库驱动坐标等
    
   6.3 maven坐标
   
    <properties>
        <spring.version>4.2.4.RELEASE</spring.version>
        <hibernate.version>5.0.7.Final</hibernate.version>
        <slf4j.version>1.6.6</slf4j.version>
        <log4j.version>1.2.12</log4j.version>
        <c3p0.version>0.9.1.2</c3p0.version>
        <mysql.version>5.1.6</mysql.version>
    </properties>

    <dependencies>
        <!-- junit单元测试 -->
        <dependency>
            <groupId>junit</groupId>
            <artifactId>junit</artifactId>
            <version>4.9</version>
            <scope>test</scope>
        </dependency>
        
        <!-- spring beg -->
        <dependency>
            <groupId>org.aspectj</groupId>
            <artifactId>aspectjweaver</artifactId>
            <version>1.6.8</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-aop</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-context-support</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-orm</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-beans</artifactId>
            <version>${spring.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-core</artifactId>
            <version>${spring.version}</version>
        </dependency>
        
        <!-- spring end -->

        <!-- hibernate beg -->
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-core</artifactId>
            <version>${hibernate.version}</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-entitymanager</artifactId>
            <version>${hibernate.version}</version>
        </dependency>
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>5.2.1.Final</version>
        </dependency>
        <!-- hibernate end -->

        <!-- c3p0 beg -->
        <dependency>
            <groupId>c3p0</groupId>
            <artifactId>c3p0</artifactId>
            <version>${c3p0.version}</version>
        </dependency>
        <!-- c3p0 end -->

        <!-- log end -->
        <dependency>
            <groupId>log4j</groupId>
            <artifactId>log4j</artifactId>
            <version>${log4j.version}</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-api</artifactId>
            <version>${slf4j.version}</version>
        </dependency>

        <dependency>
            <groupId>org.slf4j</groupId>
            <artifactId>slf4j-log4j12</artifactId>
            <version>${slf4j.version}</version>
        </dependency>
        <!-- log end -->

        
        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>${mysql.version}</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.data</groupId>
            <artifactId>spring-data-jpa</artifactId>
            <version>1.9.0.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>4.2.4.RELEASE</version>
        </dependency>
        
        <!-- el beg 使用spring data jpa 必须引入 -->
        <dependency>  
            <groupId>javax.el</groupId>  
            <artifactId>javax.el-api</artifactId>  
            <version>2.2.4</version>  
        </dependency>  
          
        <dependency>  
            <groupId>org.glassfish.web</groupId>  
            <artifactId>javax.el</artifactId>  
            <version>2.2.4</version>  
        </dependency> 
        <!-- el end -->
    </dependencies>
    
   6.4 整合SpringData JPA与Spring的配置文件
    
    	<?xml version="1.0" encoding="UTF-8"?>
	<beans xmlns="http://www.springframework.org/schema/beans"
		xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance" xmlns:aop="http://www.springframework.org/schema/aop"
		xmlns:context="http://www.springframework.org/schema/context"
		xmlns:jdbc="http://www.springframework.org/schema/jdbc" xmlns:tx="http://www.springframework.org/schema/tx"
		xmlns:jpa="http://www.springframework.org/schema/data/jpa" xmlns:task="http://www.springframework.org/schema/task"
		xsi:schemaLocation="
			http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
			http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd
			http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
			http://www.springframework.org/schema/jdbc http://www.springframework.org/schema/jdbc/spring-jdbc.xsd
			http://www.springframework.org/schema/tx http://www.springframework.org/schema/tx/spring-tx.xsd
			http://www.springframework.org/schema/data/jpa 
			http://www.springframework.org/schema/data/jpa/spring-jpa.xsd">

		<!-- 1.dataSource 配置数据库连接池-->
		<bean id="dataSource" class="com.mchange.v2.c3p0.ComboPooledDataSource">
			<property name="driverClass" value="com.mysql.jdbc.Driver" />
			<property name="jdbcUrl" value="jdbc:mysql://localhost:3306/jpa" />
			<property name="user" value="root" />
			<property name="password" value="root" />
		</bean>

		<!-- 2.配置entityManagerFactory -->
		<bean id="entityManagerFactory" class="org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean">
			<property name="dataSource" ref="dataSource" />
			<property name="packagesToScan" value="com.jiaming.entity" />
			<property name="persistenceProvider">
				<bean class="org.hibernate.jpa.HibernatePersistenceProvider" />
			</property>
			<!--JPA的供应商适配器-->
			<property name="jpaVendorAdapter">
				<bean class="org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter">
					<property name="generateDdl" value="false" />
					<property name="database" value="MYSQL" />
					<property name="databasePlatform" value="org.hibernate.dialect.MySQLDialect" />
					<property name="showSql" value="true" />
				</bean>
			</property>
			<property name="jpaDialect">
				<bean class="org.springframework.orm.jpa.vendor.HibernateJpaDialect" />
			</property>
		</bean>

		<!-- 3.事务管理器-->
		<!-- JPA事务管理器  -->
		<bean id="transactionManager" class="org.springframework.orm.jpa.JpaTransactionManager">
			<property name="entityManagerFactory" ref="entityManagerFactory" />
		</bean>

		<!-- 整合spring data jpa-->
		<jpa:repositories base-package="com.jiaming.dao"
			transaction-manager-ref="transactionManager"
			entity-manager-factory-ref="entityManagerFactory"></jpa:repositories>

		<!-- 4.txAdvice-->
		<tx:advice id="txAdvice" transaction-manager="transactionManager">
			<tx:attributes>
				<tx:method name="save*" propagation="REQUIRED"/>
				<tx:method name="insert*" propagation="REQUIRED"/>
				<tx:method name="update*" propagation="REQUIRED"/>
				<tx:method name="delete*" propagation="REQUIRED"/>
				<tx:method name="get*" read-only="true"/>
				<tx:method name="find*" read-only="true"/>
				<tx:method name="*" propagation="REQUIRED"/>
			</tx:attributes>
		</tx:advice>

		<!-- 5.aop-->
		<aop:config>
			<aop:pointcut id="pointcut" expression="execution(* com.jiaming.service.*.*(..))" />
			<aop:advisor advice-ref="txAdvice" pointcut-ref="pointcut" />
		</aop:config>

		<context:component-scan base-package="com.jiaming"></context:component-scan>

		<!--组装其它 配置文件-->
	</beans>

   6.4 编写符合SpringData JPA规范的Dao层接口
    
    在Spring Data JPA中，对于定义符合规范的Dao层接口，我们只需要遵循以下几点就可以了：
    
	1.创建一个Dao层接口，并实现JpaRepository和JpaSpecificationExecutor
 	2.提供相应的泛型
       JpaRepository<实体类类型，主键类型>：用来完成基本CRUD操作
       JpaSpecificationExecutor<实体类类型>：用于复杂查询（分页等查询操作）
   
   6.5 方法命名规则查询
    
    顾名思义，方法命名规则查询就是根据方法的名字，就能创建查询。只需要按照SpringData JPA提供的方法命名规则定义方法的名称，就可以完成查询工作。SpringData JPA在程序执行的时候会根据方法名称进行解析，并自动生成查询语句进行查询

    按照SpringData JPA 定义的规则，查询方法以findBy开头，涉及条件查询时，条件的属性用条件关键字连接，要注意的是：条件属性首字母需大写。框架在进行方法名解析时，会先把方法名多余的前缀截取掉，然后对剩下部分进行解析。
    
    
