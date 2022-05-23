package com.ordergoods.service.impl;

import com.ordergoods.entity.SysOrder;
import com.ordergoods.mapper.SysOrderMapper;
import com.ordergoods.service.SysOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
@Service
public class SysOrderServiceImpl extends ServiceImpl<SysOrderMapper, SysOrder> implements SysOrderService {

    @Autowired
    SysOrderMapper orderMapper;

    @Override
    public List<HashMap<String, Integer>> countOrderNum(Long userId) {
        return orderMapper.countOrderNum( userId);
    }



    @Override
    public List<HashMap<String, String>> countGoodsNumber(Date beginDate, Date endDate) {
        return orderMapper.countGoodsNumber(beginDate,endDate);
    }

    @Override
    public List<Map<String, Object>> moneyCount(Date beginDate, Date endDate, String groupType) {
        return orderMapper.moneyCount(beginDate,endDate,groupType);
    }


}
