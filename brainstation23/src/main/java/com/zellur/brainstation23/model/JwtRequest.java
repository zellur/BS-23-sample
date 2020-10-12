package com.zellur.brainstation23.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class JwtRequest implements Serializable {
    private static final long serialVersionUID = 5926468583005150707L;
    private String username;
    private String password;

    //Device data
    private boolean isMobile;
    private Long userId;
    private boolean isDeviceLocked;
    private Integer heightPixels;
    private Integer widthPixels;
    private String simSerialNumber;
    private String wifiMacAddress;
    private String IMEI;
    private Date createdDate;
    private Date updatedDate;
}
