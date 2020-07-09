package com.hu.eduservice.entity;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 条件查询实体
 */

@Data
public class QueryPeople {
    @ApiModelProperty(value = "查询姓名")
    private String name;
    @ApiModelProperty(value = "查询开始时间", example = "2020-07-09 10:10:10")
    private String start_time;
    @ApiModelProperty(value = "查询结束时间", example = "2020-07-09 10:10:10")
    private String end_time;

}
