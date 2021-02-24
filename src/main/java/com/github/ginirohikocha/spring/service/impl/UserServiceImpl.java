package com.github.ginirohikocha.spring.service.impl;

import com.github.ginirohikocha.spring.entity.User;
import com.github.ginirohikocha.spring.mapper.UserMapper;
import com.github.ginirohikocha.spring.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GinirohikoCha
 * @since 2021-02-24
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
