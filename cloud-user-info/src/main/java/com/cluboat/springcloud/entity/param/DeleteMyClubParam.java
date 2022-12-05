package com.cluboat.springcloud.entity.param;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteMyClubParam {
    private Integer userId;
    private Integer clubId;
}
