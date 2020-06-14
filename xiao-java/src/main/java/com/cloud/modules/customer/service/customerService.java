package com.cloud.modules.customer.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.cloud.modules.customer.entity.customer;
import com.cloud.modules.customer.entity.customerExample;
import com.cloud.modules.user.service.userService;
import com.cloud.utils.MyPage;
import com.cloud.utils.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
public class customerService {
    @Autowired
    com.cloud.modules.customer.dao.customerMapper customerMapper;
    @Autowired
    com.cloud.modules.user.service.userService userService;

    public RestResponse select(long owner_user_id, Integer pageNum, Integer pageSize) {
        customerExample example = new customerExample();
        example.createCriteria().andOwnerUserIdEqualTo(owner_user_id);
        MyPage page = new MyPage();
        try {
            //================分页================
            PageHelper.startPage(pageNum, pageSize);//1.设置分页
            System.out.println(pageNum);
            System.out.println(pageSize);
            List<customer> list = customerMapper.selectByExample(example);//2.进行查询
            PageInfo<customer> pageInfo = new PageInfo<>(list);//3.进行分页]
            page = MyPage.newInstance(pageInfo);
            //====================================
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
        return RestResponse.success().put("data", page);
    }


    public RestResponse save(customer customer) {
        try {
            customerMapper.insertSelective(customer);
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
        return RestResponse.success();
    }

    public RestResponse delete(int id) {
        try {
            customerExample example = new customerExample();
            example.createCriteria().andCustomerIdEqualTo(id);
            customerMapper.deleteByExample(example);
        } catch (Exception e) {
            return RestResponse.error();
        }
        return RestResponse.success();
    }

    public RestResponse update(customer customer) {
        try {
            customerMapper.updateByPrimaryKeySelective(customer);
        } catch (Exception e) {
            return RestResponse.error();
        }
        return RestResponse.success();
    }

    public RestResponse read(int customer_id) {
        try {
            customerExample example = new customerExample();
            example.createCriteria().andCustomerIdEqualTo(customer_id);
            customer customer = new customer();
            customer = customerMapper.selectByExample(example).get(0);
            customer.setOwner_user_id_info(userService.getUserInfo(customer.getOwnerUserId()));
            return RestResponse.success().put("page", customer);
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    public RestResponse transfer(long owner_user_id, long customer_id) {
        try {
            customer customer = new customer();
            customer.setCustomerId((int) customer_id);
            customer.setOwnerUserId(owner_user_id);
            customerMapper.updateByPrimaryKeySelective(customer);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    public RestResponse lock(int id) {
        customer customer = new customer();
        customer.setCustomerId(id);
        customer.setIsLock(1);
        try {
            customerMapper.updateByPrimaryKeySelective(customer);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error(e.getMessage());
        }
    }

    public RestResponse putInPool(int id) {
//        customer customer=new customer();
//        customer.setCustomerId(id);
//        customer.setIsLock(1);
        return RestResponse.success().put("page", "暂未实现");
    }

}
