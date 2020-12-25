package com.jun.reservation.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_shop")
@Data
public class Shop extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String address;

    @Column
    private String description;

    @Column
    private String shopName;

    @Column
    private Long sortNum;

    @Column
    private String imgUrl;


}
