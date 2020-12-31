package com.jun.reservation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;

@MappedSuperclass
@Data
public class Base {

    @Column
    private String creator;

    @Column
    @CreatedDate
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @LastModifiedDate
    @Column
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;


}
