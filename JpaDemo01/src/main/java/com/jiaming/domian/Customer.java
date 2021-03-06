package com.jiaming.domian;

import javax.persistence.*;

/**
 * @author jmstart
 * @create 2020-04-12 9:51
 *
 * 使用Jpa注解配置映射关系:
 *
 *  1.实体类和数据库表
 *      @Entity: 声明实体类
 *      @Table: 配置与表的映射关系
 *           name:数据库表名
 *  2.实体类属性和表中字段
 *      @Id: 声明主键
 *      @GeneratedValue: 主键生成策略
 *          strategy:
 *              GenerationType.IDENTITY: 自增 mysql
 *              GenerationType.SEQUENCE: 序列 oracle
 *              GenerationType.TABLE: Jpa提供的, 通过表来实现主键自增
 *              GenerationType.AUTO: 由程序自动选择主键生成策略
 *      @Column: 属性域字段产生联系
 *              name: 数据库字段名称
 */
@Entity
@Table(name = "cst_customer")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cust_id")
    private Long custId; //客户主键

    @Column(name = "cust_name")
    private String custName; //客户名称

    @Column(name = "cust_source")
    private String custSource; //客户来源

    @Column(name = "cust_level")
    private String custLevel; //客户级别

    @Column(name = "cust_industry")
    private String custIndustry; //客户所在行业

    @Column(name = "cust_phone")
    private String custPhone; //客户电话

    @Column(name = "cust_address")
    private String custAddress; //客户地址

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getCustSource() {
        return custSource;
    }

    public void setCustSource(String custSource) {
        this.custSource = custSource;
    }

    public String getCustLevel() {
        return custLevel;
    }

    public void setCustLevel(String custLevel) {
        this.custLevel = custLevel;
    }

    public String getCustIndustry() {
        return custIndustry;
    }

    public void setCustIndustry(String custIndustry) {
        this.custIndustry = custIndustry;
    }

    public String getCustPhone() {
        return custPhone;
    }

    public void setCustPhone(String custPhone) {
        this.custPhone = custPhone;
    }

    public String getCustAddress() {
        return custAddress;
    }

    public void setCustAddress(String custAddress) {
        this.custAddress = custAddress;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "custId=" + custId +
                ", custName='" + custName + '\'' +
                ", custSource='" + custSource + '\'' +
                ", custLevel='" + custLevel + '\'' +
                ", custIndustry='" + custIndustry + '\'' +
                ", custPhone='" + custPhone + '\'' +
                ", custAddress='" + custAddress + '\'' +
                '}';
    }
}
