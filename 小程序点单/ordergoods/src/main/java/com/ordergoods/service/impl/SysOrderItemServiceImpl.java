package com.ordergoods.service.impl;

import com.ordergoods.entity.SysOrderItem;
import com.ordergoods.mapper.SysOrderItemMapper;
import com.ordergoods.service.SysOrderItemService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * <p>
 * 订单明细表 服务实现类
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
@Service
public class SysOrderItemServiceImpl extends ServiceImpl<SysOrderItemMapper, SysOrderItem> implements SysOrderItemService {


    @Autowired
    SysOrderItemMapper orderItemMapper;
    @Override
    public List<HashMap> groupUserGoods() {
        return orderItemMapper.groupUserGoods();
    }
}
