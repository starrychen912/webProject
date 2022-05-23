package com.ordergoods.dto;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by jianggc at 2020/11/10.
 */
@Data
@ApiModel
public class SearchGoods {
    String beginTime;
    String endTime;
    Long categoryId;
    Long userId;
    String name;//物品名称

}
