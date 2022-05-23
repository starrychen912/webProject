package com.ordergoods.service;

import com.ordergoods.entity.SysOrder;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务类
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
public interface SysOrderService extends IService<SysOrder> {

    List<HashMap<String, Integer>> countOrderNum(Long userId);


    List<HashMap<String,String>> countGoodsNumber(Date beginDate, Date endDate);

    List<Map<String, Object>> moneyCount(Date beginDate, Date endDate, String groupType);
}
