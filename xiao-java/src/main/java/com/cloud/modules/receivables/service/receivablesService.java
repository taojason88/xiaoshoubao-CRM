package com.cloud.modules.receivables.service;

import com.cloud.modules.receivables.entity.receivables;
import com.cloud.modules.receivables.entity.receivablesExample;
import com.cloud.utils.MyPage;
import com.cloud.utils.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class receivablesService {
    @Autowired
    com.cloud.modules.receivables.dao.receivablesMapper receivablesMapper;
    @Autowired
    com.cloud.modules.customer.dao.customerMapper customerMapper;

    public RestResponse receivablesIndex(long owner_user_id, Integer pageIndex, Integer pageSize) {
        try {
            receivablesExample example = new receivablesExample();
            example.createCriteria().andOwnerUserIdEqualTo(owner_user_id);
            MyPage page = new MyPage();
            //================分页================
            PageHelper.startPage(pageIndex, pageSize);//1.设置分页
            List<receivables> list = receivablesMapper.selectByExample(example);//2.进行查询
            PageInfo<receivables> pageInfo = new PageInfo<>(list);//3.进行分页
            //====================================
            page = MyPage.newInstance(pageInfo);
            return RestResponse.success().put("data", page);
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse receivablesSave(receivables receivables) {
        try {
            receivables.setUpdateTime(new Date());
            receivables.setCreateTime(new Date());
            receivablesMapper.insertSelective(receivables);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }

    }

    public RestResponse receivablesDelete(int id) {
        try {
            receivablesMapper.deleteByPrimaryKey(id);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse receivablesUpdate(receivables receivables) {
        try {
            receivables.setUpdateTime(new Date());
            receivablesMapper.updateByPrimaryKeySelective(receivables);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse receivablesDetail(int id) {
        try {
            return RestResponse.success().put("data",receivablesMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse receivablesRevokeCheck(int id, String content) {


        try {
            receivables receivables = new receivables();
            receivables.setReceivablesId(id);
            receivables.setRemark(content);
            receivables.setCheckStatus(4);
            receivables.setUpdateTime(new Date());
            receivablesMapper.updateByPrimaryKeySelective(receivables);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }
}
