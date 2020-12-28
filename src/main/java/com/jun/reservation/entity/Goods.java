package com.jun.reservation.entity;

import lombok.Data;

import javax.persistence.*;

@Table(name = "tb_goods")
@Entity
@Data
public class Goods extends Base{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Double price;

    @Column
    private String description;

    @Column
    private String imgUrl;

    @ManyToOne(cascade = {CascadeType.DETACH},fetch = FetchType.LAZY)
    @JoinColumn(name = "shopId",referencedColumnName = "id")
    private Shop shop;



}
