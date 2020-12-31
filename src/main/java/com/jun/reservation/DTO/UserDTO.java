package com.jun.reservation.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

/**
 * @author jun
 * @date 2020/12/31
 */
@Data
public class UserDTO {

    private Long id;

    private String username;

    private String password;

    private Long shopId;

    private String registerAddress;

    private String registerIp;

    private String creator;

}
