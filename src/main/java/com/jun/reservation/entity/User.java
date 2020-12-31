package com.jun.reservation.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.GenerationType;


@Entity
@Table(name = "tb_user")
@Data
public class User extends Base{

    @Id
//    @GenericGenerator(name = "IDGenerator", strategy = "IDENTITY")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username",nullable = false,unique = true,length = 64)
    private String username;

    @Column(name = "password",nullable = false)
    private String password;

    @Column
    private Long shopId;

    @Column
    private String registerAddress;

    @Column
    private String registerIp;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "tb_user_role",
            joinColumns =
            @JoinColumn(name = "user_id",referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id",referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", shopId=" + shopId +
                ", registerAddress='" + registerAddress + '\'' +
                ", registerIp='" + registerIp + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        if (!super.equals(o)) return false;

        User user = (User) o;

        if (getId() != null ? !getId().equals(user.getId()) : user.getId() != null) return false;
        if (getUsername() != null ? !getUsername().equals(user.getUsername()) : user.getUsername() != null)
            return false;
        if (getPassword() != null ? !getPassword().equals(user.getPassword()) : user.getPassword() != null)
            return false;
        if (getShopId() != null ? !getShopId().equals(user.getShopId()) : user.getShopId() != null) return false;
        if (getRegisterAddress() != null ? !getRegisterAddress().equals(user.getRegisterAddress()) : user.getRegisterAddress() != null)
            return false;
        return getRegisterIp() != null ? getRegisterIp().equals(user.getRegisterIp()) : user.getRegisterIp() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getId() != null ? getId().hashCode() : 0);
        result = 31 * result + (getUsername() != null ? getUsername().hashCode() : 0);
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        result = 31 * result + (getShopId() != null ? getShopId().hashCode() : 0);
        result = 31 * result + (getRegisterAddress() != null ? getRegisterAddress().hashCode() : 0);
        result = 31 * result + (getRegisterIp() != null ? getRegisterIp().hashCode() : 0);
        return result;
    }
}
