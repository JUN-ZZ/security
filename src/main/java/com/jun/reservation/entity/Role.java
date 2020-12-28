package com.jun.reservation.entity;


import lombok.Data;

import javax.persistence.*;
import java.util.Date;
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

    @ManyToMany(mappedBy = "roles")
    private Set<User> users;

    @ManyToMany(fetch = FetchType.LAZY)
    private Set<Authority> authorities;



}
