package com.ordergoods.service.impl;

import com.ordergoods.entity.UserScore;
import com.ordergoods.mapper.UserScoreMapper;
import com.ordergoods.service.UserScoreService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 会员积分明细表 服务实现类
 * </p>
 *
 * @author Janus
 * @since 2021-01-23
 */
@Service
public class UserScoreServiceImpl extends ServiceImpl<UserScoreMapper, UserScore> implements UserScoreService {

}
