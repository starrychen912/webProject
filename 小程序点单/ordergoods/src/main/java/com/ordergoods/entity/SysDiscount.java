package com.ordergoods.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 打折活动表
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_sys_discount")
@ApiModel(value="SysDiscount对象", description="打折活动表")
public class SysDiscount implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "编码")
    private String code;

    @ApiModelProperty(value = "打折活动名称")
    private String name;

    @ApiModelProperty(value = "餐品ID")
    private Long goodsId;

    @ApiModelProperty(value = "餐品名称")
    private String goodsName;

    @ApiModelProperty(value = "折扣说明，如1表示1折；2表示2折；10表示不打折")
    private Integer discount;

    @ApiModelProperty(value = "打折活动开始时间")
    private LocalDateTime beginDate;

    @ApiModelProperty(value = "打折活动结束时间")
    private LocalDateTime endDate;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "冗余字段")
    private String field0;

    @ApiModelProperty(value = "冗余字段1")
    private String field1;

    @ApiModelProperty(value = "冗余字段2")
    private String field2;

    @ApiModelProperty(value = "冗余字段3")
    private String field3;

    @ApiModelProperty(value = "冗余字段4")
    private String field4;

    @ApiModelProperty(value = "冗余字段5")
    private String field5;

    @ApiModelProperty(value = "冗余字段")
    private String field6;

    @ApiModelProperty(value = "冗余字段")
    private String field7;

    @ApiModelProperty(value = "冗余字段")
    private String field8;

    @ApiModelProperty(value = "冗余字段")
    private String field9;

    @ApiModelProperty(value = "创建时间")
    private LocalDateTime createTime;

    @ApiModelProperty(value = "更新时间")
    private LocalDateTime updateTime;


}
