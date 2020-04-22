package com.jiaming.domain;

import javax.persistence.*;

/**
 * @author jmstart
 * @create 2020-04-21 16:23
 */
@Entity
@Table(name = "cst_linkman")
public class LinkMan {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="lkm_id")
    private Long lkmId;
    @Column(name="lkm_name")
    private String lkmName;
    @Column(name="lkm_gender")
    private String lkmGender;
    @Column(name="lkm_phone")
    private String lkmPhone;
    @Column(name="lkm_mobile")
    private String lkmMobile;
    @Column(name="lkm_email")
    private String lkmEmail;
    @Column(name="lkm_position")
    private String lkmPosition;
    @Column(name="lkm_memo")
    private String lkmMemo;

    //多对一关系映射：多个联系人对应客户
    @ManyToOne(targetEntity=Customer.class)
    @JoinColumn(name="lkm_cust_id",referencedColumnName="cust_id")
    private Customer customer;//用它的主键，对应联系人表中的外键

    public Long getLkmId() {
        return lkmId;
    }
    public void setLkmId(Long lkmId) {
        this.lkmId = lkmId;
    }
    public String getLkmName() {
        return lkmName;
    }
    public void setLkmName(String lkmName) {
        this.lkmName = lkmName;
    }
    public String getLkmGender() {
        return lkmGender;
    }
    public void setLkmGender(String lkmGender) {
        this.lkmGender = lkmGender;
    }
    public String getLkmPhone() {
        return lkmPhone;
    }
    public void setLkmPhone(String lkmPhone) {
        this.lkmPhone = lkmPhone;
    }
    public String getLkmMobile() {
        return lkmMobile;
    }
    public void setLkmMobile(String lkmMobile) {
        this.lkmMobile = lkmMobile;
    }
    public String getLkmEmail() {
        return lkmEmail;
    }
    public void setLkmEmail(String lkmEmail) {
        this.lkmEmail = lkmEmail;
    }
    public String getLkmPosition() {
        return lkmPosition;
    }
    public void setLkmPosition(String lkmPosition) {
        this.lkmPosition = lkmPosition;
    }
    public String getLkmMemo() {
        return lkmMemo;
    }
    public void setLkmMemo(String lkmMemo) {
        this.lkmMemo = lkmMemo;
    }
    public Customer getCustomer() {
        return customer;
    }
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
    @Override
    public String toString() {
        return "LinkMan [lkmId=" + lkmId + ", lkmName=" + lkmName + ", lkmGender=" + lkmGender + ", lkmPhone="
                + lkmPhone + ", lkmMobile=" + lkmMobile + ", lkmEmail=" + lkmEmail + ", lkmPosition=" + lkmPosition
                + ", lkmMemo=" + lkmMemo + "]";
    }
}
