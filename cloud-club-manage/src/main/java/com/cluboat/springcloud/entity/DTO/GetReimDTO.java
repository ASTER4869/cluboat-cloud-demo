package com.cluboat.springcloud.entity.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetReimDTO {
    private Integer reimId;
    private Integer clubId;
    private Integer userId;
    private String title;
    private BigDecimal amount;
    private String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Timestamp createTime;
    private String status;
    private String feedback;

    private List<GetReimAttachDTO> attachments;

    private String userName;
    private String clubName;


}
