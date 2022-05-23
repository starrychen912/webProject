package com.ordergoods.service;

import com.ordergoods.entity.SysOrderItem;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 订单明细表 服务类
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
public interface SysOrderItemService extends IService<SysOrderItem> {

    List<HashMap> groupUserGoods();

}
