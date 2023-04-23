package com.cluboat.springcloud.entity.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetBudgetItemDTO {
    private String name;
    private BigDecimal money;
    private String description;
}
