package com.github.ginirohikocha.spring.service.impl;

import com.github.ginirohikocha.spring.entity.SysUser;
import com.github.ginirohikocha.spring.mapper.SysUserMapper;
import com.github.ginirohikocha.spring.service.ISysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GinirohikoCha
 * @since 2021-05-20
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

}
