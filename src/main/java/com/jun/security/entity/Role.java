package com.jun.security.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "tb_role")
public class Role {

    @Id
//    auto-increment
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String roleNickName;

    @Column
    private String roleCodeName;

    @Column
    private Date createTime;

    @Column
    private String creator;

    @Transient
    private List<User> users;

    @Transient
    private List<Authority> authorities;



}
