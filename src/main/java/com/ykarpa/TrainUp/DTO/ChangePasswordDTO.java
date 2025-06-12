package com.ykarpa.TrainUp.DTO;

import lombok.Data;


@Data
public class ChangePasswordDTO {
    private Long userId;
    private String oldPassword;
    private String newPassword;
}
