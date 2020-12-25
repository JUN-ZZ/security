package com.jun.reservation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "tb_authority")
@Data
public class Authority extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(
            columnDefinition = "int default 0"
    )
    private Long parent_id;

    @Column
    private String authName;

    @Column
    private String url;

    @Column
    private String requestMethod;

    @Column
    private String code;

    @Transient
    private List<Role> roles;



}
