package com.jun.reservation.entity;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.Date;
import java.util.List;
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

    @Transient
    private List<Role> roles;



}
