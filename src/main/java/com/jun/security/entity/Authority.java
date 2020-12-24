package com.jun.security.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_authority")
@Data
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String url;

    @Column
    private String requestMethod;

    @Column
    private String code;

    @Column
    private Date createTime;

    @Column
    private String creator;

    @Transient
    private List<Role> roles;



}
