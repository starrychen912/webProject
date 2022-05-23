package com.ordergoods.mapper;

import com.ordergoods.entity.SysOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 Mapper 接口
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
public interface SysOrderMapper extends BaseMapper<SysOrder> {

    List<HashMap<String, Integer>> countOrderNum(Long userId);


    List<HashMap<String,String>> countGoodsNumber(Date beginDate, Date endDate);

    List<Map<String, Object>> moneyCount(Date beginDate, Date endDate, String groupType);
}
