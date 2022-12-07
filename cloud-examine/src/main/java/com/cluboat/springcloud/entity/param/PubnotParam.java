package com.cluboat.springcloud.entity.param;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;
@Data
public class PubnotParam {
    public int adminId;
    public String pubnotTitle;
    public Timestamp pubnotTime;
    public String pubnotContent;
}
