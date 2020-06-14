package com.cloud.modules.message.service;

import com.cloud.modules.message.entity.message;
import com.cloud.modules.message.entity.messageExample;
import com.cloud.utils.MyPage;
import com.cloud.utils.RestResponse;
import com.cloud.utils.WebAPIResult;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class messageService {
    @Autowired
    com.cloud.modules.message.dao.messageMapper messageMapper;
    @Autowired
    com.cloud.modules.customer.dao.customerMapper customerMapper;
    public RestResponse messageIndex(long recipient_user, Integer pageIndex, Integer pageSize) {
        try {
            messageExample example = new messageExample();
            MyPage page = new MyPage();
            example.createCriteria().andRecipientUserEqualTo(recipient_user);
            //================分页================
            PageHelper.startPage(pageIndex, pageSize);//1.设置分页
            List<message> list = messageMapper.selectByExample(example);//2.进行查询
            PageInfo<message> pageInfo = new PageInfo<>(list);//3.进行分页
            //====================================
            page = MyPage.newInstance(pageInfo);
            return RestResponse.success().put("data", page);
        } catch (Exception e) {
            return RestResponse.error();
        }
    }
}

