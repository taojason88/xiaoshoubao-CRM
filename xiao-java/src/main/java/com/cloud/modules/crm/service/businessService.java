package com.cloud.modules.business.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.business.entity.business;
import com.cloud.modules.business.entity.businessExample;
import com.cloud.modules.customer.dao.customerMapper;
import com.cloud.modules.customer.entity.customer;
import com.cloud.utils.MyPage;
import com.cloud.utils.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class businessService {
    @Autowired
    com.cloud.modules.business.dao.businessMapper businessMapper;
    @Autowired
    com.cloud.modules.customer.dao.customerMapper customerMapper;

    public RestResponse businessIndex(long owner_user_id, Integer pageIndex, Integer pageSize) {
        MyPage page = new MyPage();
        try {
            businessExample example = new businessExample();
            example.createCriteria().andOwnerUserIdEqualTo(owner_user_id);
            //================分页================
            PageHelper.startPage(pageIndex, pageSize);//1.设置分页
            List<business> list = businessMapper.selectByExample(example);//2.进行查询
            PageInfo<business> pageInfo = new PageInfo<>(list);//3.进行分页
            //====================================
            page = MyPage.newInstance(pageInfo);
            return RestResponse.success().put("data", page);
 
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse businessSave(business business) {
        try {
            String name = business.getBusinessName();
            if (name == null || name.equals("")) {
                return RestResponse.error("商机名称不能为空!");
            }
            business.setCreateTime(new Date());
            business.setUpdateTime(new Date());
            businessMapper.insertSelective(business);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse businessDelete(int id) {
        try {
            businessMapper.deleteByPrimaryKey(id);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse businessUpdate(business business) {
        try {
            business.setUpdateTime(new Date());
            businessMapper.updateByPrimaryKeySelective(business);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse businessDetail(int id) {
        try {
            return RestResponse.success().put("data", businessMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse businessTransfer(long owner_user_id, int business_id) {
        try {
            business business = new business();
            business.setBusinessId(business_id);
            business.setOwnerUserId(owner_user_id);
            businessMapper.updateByPrimaryKeySelective(business);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }
}
