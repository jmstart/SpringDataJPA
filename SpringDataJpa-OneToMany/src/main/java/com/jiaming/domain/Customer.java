package com.jiaming.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jmstart
 * @create 2020-04-21 16:19
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

    //配置客户和联系人的一对多关系
    //cascade = CascadeType.ALL 代表可以级联删除,级联更新,级联添加
    @OneToMany(targetEntity=LinkMan.class, cascade = CascadeType.ALL)
    @JoinColumn(name="lkm_cust_id",referencedColumnName="cust_id")
    /**
     * 放弃维护外键,下面注解即可
     *   但是要删除主表数据时要是放弃了外键维护权,就需要级联删除了
     *   如果没有放弃维护权,会先把外键置为空,在删除
     */
    //@OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private Set<LinkMan> linkmans = new HashSet<LinkMan>(0);

    public Set<LinkMan> getLinkmans() {
        return linkmans;
    }

    public void setLinkmans(Set<LinkMan> linkmans) {
        this.linkmans = linkmans;
    }

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
