package com.ordergoods.entity;

import java.math.BigDecimal;
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
 * 餐品表
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("tb_sys_goods")
@ApiModel(value="SysGoods对象", description="餐品表")
public class SysGoods implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @ApiModelProperty(value = "餐品名称")
    private String name;

    @ApiModelProperty(value = "餐品编号")
    private String code;

    @ApiModelProperty(value = "分类ID ")
    private Long categoryId;

    @ApiModelProperty(value = "分类名称")
    private String categoryName;

    @ApiModelProperty(value = "价格")
    private BigDecimal price;

    @ApiModelProperty(value = "库存")
    private Integer stock;

    @ApiModelProperty(value = "状态：0 下架；1 上架")
    private Integer state;

    @ApiModelProperty(value = "主图")
    private String mainPic;

    @ApiModelProperty(value = "副图1")
    private String subPic1;

    @ApiModelProperty(value = "副图2")
    private String subPic2;

    @ApiModelProperty(value = "副图3")
    private String subPic3;

    @ApiModelProperty(value = "副图4")
    private String subPic4;

    @ApiModelProperty(value = "副图5")
    private String subPic5;

    @ApiModelProperty(value = "备注")
    private String remark;

    @ApiModelProperty(value = "详情介绍")
    private String content;

    @ApiModelProperty(value = "保存返回给小程序时候的下单购买数量")
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
