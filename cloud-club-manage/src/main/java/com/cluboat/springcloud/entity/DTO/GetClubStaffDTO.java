package com.cluboat.springcloud.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetClubStaffDTO {
    public int userId;
    public String userName;
    public int state;
    public int permission;
}
