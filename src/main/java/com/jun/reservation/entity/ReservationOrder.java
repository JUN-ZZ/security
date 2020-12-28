package com.jun.reservation.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name = "tb_reservation_order")
@Data
public class ReservationOrder extends Base {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long shopId;

    @Column
    private String shopName;

    @Column
    private Long userId;

    @Column
    private String username;

    @Column
    private Long goodsId;

    @Column
    private String goodsName;

    @Column
    private Long reservationCount;

    @Column
    private Date reservationTime;

    @Column
    private String remark;

    @Column
    private boolean isExpire;


}
