package com.jiaming.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author jmstart
 * @create 2020-04-21 19:26
 */
@Entity
@Table(name = "sys_role")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id")
    private Long roleId;

    @Column(name = "role_name")
    private String roleName;

    //配置多对多关系
    //放弃主键维护权
    @ManyToMany(mappedBy = "roles")
//    @JoinTable(name = "sys_user_role",
//            //当前对象在中间表中的外键
//            joinColumns = {@JoinColumn(name = "sys_role_id", referencedColumnName = "role_id")},
//            //对方对象在中间表中的外键
//            inverseJoinColumns = {@JoinColumn(name = "sys_user_id", referencedColumnName = "user_id")}
//    )
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }
}
