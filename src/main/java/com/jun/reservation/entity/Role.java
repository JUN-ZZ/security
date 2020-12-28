package com.jun.reservation.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


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

    @Transient
    private List<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Authority> authorities;



}
