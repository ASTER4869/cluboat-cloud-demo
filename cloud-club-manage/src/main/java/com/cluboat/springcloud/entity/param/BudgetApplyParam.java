package com.cluboat.springcloud.entity.param;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class BudgetApplyParam {
    public int userId;
    public int clubId;
    public String budgetApplyReason;
    public Timestamp budgetApplyTime;
}
