package com.ordergoods.entity;

import java.math.BigDecimal;

import com.baomidou.mybatisplus.annotation.TableField;
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
 * 购物车表
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_sys_cart")
@ApiModel(value="SysCart对象", description="购物车表")
public class SysCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private SysGoods goods;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "用户ID")
    private Long userId;

    @ApiModelProperty(value = "用户名称")
    private String userName;

    @ApiModelProperty(value = "商品ID")
    private Long goodsId;

    @ApiModelProperty(value = "商品数量")
    private Integer goodsNum;

    @ApiModelProperty(value = "商品名称")
    private String goodsName;

    @ApiModelProperty(value = "下单时商品价格")
    private BigDecimal price;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "保存商家ID")
    private String field0;

    @ApiModelProperty(value = "保存商家名称")
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
