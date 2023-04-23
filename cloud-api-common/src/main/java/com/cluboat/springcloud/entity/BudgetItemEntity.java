package com.cluboat.springcloud.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@TableName("budget_item")
@Table(name = "budget_item", schema = "cluboat", catalog = "")
public class BudgetItemEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @TableId("item_id")
    @Column(name = "item_id")
    private int itemId;
    @Basic
    @Column(name = "budget_id")
    private int budgetId;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "money")
    private BigDecimal money;
    @Basic
    @Column(name = "description")
    private String description;

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(int budgetId) {
        this.budgetId = budgetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getMoney() {
        return money;
    }

    public void setMoney(BigDecimal money) {
        this.money = money;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetItemEntity that = (BudgetItemEntity) o;
        return itemId == that.itemId && budgetId == that.budgetId && Objects.equals(name, that.name) && Objects.equals(money, that.money) && Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(itemId, budgetId, name, money, description);
    }
}
