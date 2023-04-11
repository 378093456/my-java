package com.test.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("User_2")
public class User2 {

    private String name;

    private Integer age;
}
