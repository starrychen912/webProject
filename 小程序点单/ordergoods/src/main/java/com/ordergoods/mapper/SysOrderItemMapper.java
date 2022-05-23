package com.ordergoods.mapper;

import com.ordergoods.entity.SysOrderItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 订单明细表 Mapper 接口
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
public interface SysOrderItemMapper extends BaseMapper<SysOrderItem> {

    List<HashMap> groupUserGoods();
}
