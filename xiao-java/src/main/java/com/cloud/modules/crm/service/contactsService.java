package com.cloud.modules.crm.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.crm.entity.contacts;
import com.cloud.modules.crm.entity.contactsExample;
import com.cloud.modules.customer.dao.customerMapper;
import com.cloud.modules.customer.entity.customer;
import com.cloud.utils.MyPage;
import com.cloud.utils.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class contactsService {
    @Autowired
    com.cloud.modules.crm.dao.contactsMapper contactsMapper;
    @Autowired
    com.cloud.modules.customer.dao.customerMapper customerMapper;

    public RestResponse contactsIndex(long owner_user_id, Integer pageIndex, Integer pageSize) {
        MyPage page = new MyPage();
        try {
            contactsExample example = new contactsExample();
            example.createCriteria().andOwnerUserIdEqualTo(owner_user_id);
            //================分页================
            PageHelper.startPage(pageIndex, pageSize);//1.设置分页
            List<contacts> list = contactsMapper.selectByExample(example);//2.进行查询
            PageInfo<contacts> pageInfo = new PageInfo<>(list);//3.进行分页
            //====================================
            page = MyPage.newInstance(pageInfo);
            return RestResponse.success().put("data", page);
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    public RestResponse contactsSave(contacts contacts) {
        RestResponse RestResponse = new RestResponse();
        try {
            String name = contacts.getName();
            if (name == null || name.equals("")) {
                return RestResponse.error("姓名不能为空!");
            }
            contacts.setUpdateTime(new Date());
            contacts.setCreateTime(new Date());
            contactsMapper.insertSelective(contacts);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    public RestResponse contactsDelete(int id) {
        try {
            contactsMapper.deleteByPrimaryKey(id);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    public RestResponse contactsUpdate(contacts contacts) {
        try {
            contacts.setUpdateTime(new Date());
            contactsMapper.updateByPrimaryKeySelective(contacts);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    public RestResponse contactsDetail(int id) {
        try {
            return RestResponse.success().put("data", contactsMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    public RestResponse contactsTransfer(long owner_user_id, int contacts_id) {
        try {
            contacts contacts = new contacts();
            contacts.setContactsId(contacts_id);
            contacts.setOwnerUserId(owner_user_id);
            contacts.setUpdateTime(new Date());
            contactsMapper.updateByPrimaryKeySelective(contacts);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }
}
