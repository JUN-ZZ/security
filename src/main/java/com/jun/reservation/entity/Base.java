package com.jun.reservation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import java.util.Date;

@Data
public class Base {

    @Column
    private String creator;

    @Column
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date createdTime;

    @LastModifiedDate
    @Column(nullable = false)
    @JsonFormat(pattern="yyyy-MM-dd hh:mm:ss")
    private Date updatedTime;


}
