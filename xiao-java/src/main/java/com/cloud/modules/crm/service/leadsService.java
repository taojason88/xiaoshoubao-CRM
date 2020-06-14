//package com.cloud.modules.leads.service;
//
//import com.cloud.modules.leads.dao.crm_leadsMapper;
//import com.cloud.modules.leads.entity.crm_leadsEntity;
//import com.cloud.modules.leads.entity.crm_leadsExample;
//import com.cloud.utils.RestResponse;
//import com.cloud.utils.RestResponse;
//import com.github.pagehelper.Page;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
//import io.swagger.annotations.Example;
//import io.swagger.models.auth.In;
//import jdk.internal.org.objectweb.asm.commons.GeneratorAdapter;
//import com.cloud.utils.MyPage;
//import org.springframework.stereotype.Service;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.context.request.WebRequest;
//
//import javax.annotation.Resource;
//import java.util.*;
//
//@Service
//public class leadsService {
//
//    @Resource
//    private crm_leadsMapper crm_leadsMapper;
//
//    public MyPage<crm_leadsEntity> query(Map<String, Object> params) {
//        //排序
//        params.put("sidx", "d.SORT");
//
//        Integer pageNum = 1;
//        Integer pageSize = 10;
//        //分页参数
//        if (params.get("page") != null) {
//            pageNum = Integer.parseInt(params.get("page").toString());
//        }
//        if (params.get("limit") != null) {
//            pageSize = Integer.parseInt(params.get("limit").toString());
//        }
//        PageHelper.startPage(pageNum, pageSize);
//
//        crm_leadsExample example = new crm_leadsExample();
//        example.createCriteria();
//        List<crm_leadsEntity> list = this.crm_leadsMapper.selectByExample(example);
//        PageInfo pageinfo = new PageInfo(list);
//        return MyPage.newInstance(pageinfo);
//    }
//
//    public void add(crm_leadsEntity crm_leadsEntity)
//    {
//        crm_leadsMapper.insertSelective(crm_leadsEntity);
//    }
//
//    public void update(crm_leadsEntity crm_leadsEntity)
//    {
//        crm_leadsMapper.updateByPrimaryKey(crm_leadsEntity);
//    }
//}


package com.cloud.modules.crm.service;

import com.cloud.modules.customer.dao.customerMapper;
import com.cloud.modules.customer.entity.customer;
import com.cloud.modules.crm.dao.leadsMapper;
import com.cloud.modules.crm.entity.leads;
import com.cloud.modules.crm.entity.leads;
import com.cloud.modules.crm.entity.leadsExample;
import com.cloud.utils.MyPage;
import com.cloud.utils.RestResponse;
import com.cloud.utils.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class leadsService {
    @Autowired
    com.cloud.modules.crm.dao.leadsMapper leadsMapper;
    @Autowired
    com.cloud.modules.customer.dao.customerMapper customerMapper;

    public RestResponse leadsIndex(long owner_user_id, Integer pageIndex, Integer pageSize) {
        try {
            leadsExample example = new leadsExample();
            example.createCriteria().andOwnerUserIdEqualTo(owner_user_id);
            MyPage page = new MyPage();
            //================分页================
            PageHelper.startPage(pageIndex, pageSize);//1.设置分页
            List<leads> list = leadsMapper.selectByExample(example);//2.进行查询
            PageInfo<leads> pageInfo = new PageInfo<>(list);//3.进行分页
            //====================================
            page = MyPage.newInstance(pageInfo);
            return RestResponse.success().put("data", page);
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse leadsSave(leads leads) {
        try {
            leads.setUpdateTime(new Date());
            leads.setCreateTime(new Date());
            leadsMapper.insertSelective(leads);
        } catch (Exception e) {
            return RestResponse.error();
        }
        return RestResponse.success();

    }

    public RestResponse leadsDelete(int id) {
        try {
            leadsMapper.deleteByPrimaryKey(id);
        } catch (Exception e) {
            return RestResponse.error();
        }
        return RestResponse.success();

    }

    public RestResponse leadsUpdate(leads leads) {
        try {
            leads.setUpdateTime(new Date());
            leadsMapper.updateByPrimaryKeySelective(leads);
        } catch (Exception e) {
            return RestResponse.error();
        }
        return RestResponse.success();
    }

    public RestResponse leadsDetail(int id) {
        try {
            return RestResponse.success().put("data",leadsMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            return RestResponse.error();
        }
    }


    public RestResponse transfer(int owner_user_id, int leads_id) {
        leads leads = new leads();
        leads.setLeadsId(leads_id);
        leads.setOwnerUserId((long) owner_user_id);
        leads.setUpdateTime(new Date());
        try {
            leadsMapper.updateByPrimaryKeySelective(leads);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse transform(int leads_id) {
        try {
            leads leads = new leads();
            leads = leadsMapper.selectByPrimaryKey(leads_id);
            customer customer = new customer();
            customer.setCustomerName(leads.getLeadsName());
            customer.setAddress(leads.getAddress());
            customer.setMobile(leads.getMobile());
            customer.setTelephone(leads.getTelephone());
            customerMapper.insertSelective(customer);
            long customer_id = customer.getCustomerId();
            leads.setCustomerId((int) customer_id);
            leadsMapper.updateByPrimaryKeySelective(leads);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }
}

