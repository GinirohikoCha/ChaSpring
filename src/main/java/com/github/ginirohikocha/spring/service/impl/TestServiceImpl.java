package com.github.ginirohikocha.spring.service.impl;

import com.github.ginirohikocha.spring.entity.Test;
import com.github.ginirohikocha.spring.mapper.TestMapper;
import com.github.ginirohikocha.spring.service.ITestService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author GinirohikoCha
 * @since 2021-02-21
 */
@Service
public class TestServiceImpl extends ServiceImpl<TestMapper, Test> implements ITestService {

}
