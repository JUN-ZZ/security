package com.jun.reservation.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Data
@Entity
@Table(name = "tb_role")
public class Role extends Base {

    @Id
//    auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long shopId;

    @Column(unique = true)
    private String roleNickName;

    @Column(unique = true)
    private String roleCodeName;

    @ManyToMany(mappedBy = "roles",fetch = FetchType.LAZY)
    private Set<User> users = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Authority> authorities = new HashSet<>();

    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", shopId=" + shopId +
                ", roleNickName='" + roleNickName + '\'' +
                ", roleCodeName='" + roleCodeName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Role)) return false;
        if (!super.equals(o)) return false;

        Role role = (Role) o;

        if (getId() != null ? !getId().equals(role.getId()) : role.getId() != null) return false;
        if (getShopId() != null ? !getShopId().equals(role.getShopId()) : role.getShopId() != null) return false;
        if (getRoleNickName() != null ? !getRoleNickName().equals(role.getRoleNickName()) : role.getRoleNickName() != null)
            return false;
        return getRoleCodeName() != null ? getRoleCodeName().equals(role.getRoleCodeName()) : role.getRoleCodeName() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getShopId() != null ? getShopId().hashCode() : 0);
        result = 31 * result + (getRoleNickName() != null ? getRoleNickName().hashCode() : 0);
        result = 31 * result + (getRoleCodeName() != null ? getRoleCodeName().hashCode() : 0);
        return result;
    }



}
