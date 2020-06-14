package com.cloud.modules.contract.service;

import com.cloud.modules.contract.entity.contract;
import com.cloud.modules.contract.entity.contractExample;
import com.cloud.utils.MyPage;
import com.cloud.utils.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class contractService {
    @Autowired
    com.cloud.modules.contract.dao.contractMapper contractMapper;
    @Autowired
    com.cloud.modules.customer.dao.customerMapper customerMapper;

    public RestResponse contractIndex(long owner_user_id, Integer pageIndex, Integer pageSize) {
        MyPage page = new MyPage();
        try {
            contractExample example = new contractExample();
            example.createCriteria().andOwnerUserIdEqualTo(owner_user_id);
            //================分页================
            PageHelper.startPage(pageIndex, pageSize);//1.设置分页
            List<contract> list = contractMapper.selectByExample(example);//2.进行查询
            PageInfo<contract> pageInfo = new PageInfo<>(list);//3.进行分页
            //====================================
            page = MyPage.newInstance(pageInfo);
            return RestResponse.success().put("data", page);
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse contractSave(contract contract) {
        try {
            contract.setUpdateTime(new Date());
            contract.setCreateTime(new Date());
            contractMapper.insertSelective(contract);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse contractDelete(int id) {
        try {
            contractMapper.deleteByPrimaryKey(id);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse contractUpdate(contract contract) {
        try {
            contract.setUpdateTime(new Date());
            contractMapper.updateByPrimaryKeySelective(contract);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse contractDetail(int id) {
        try {
            return RestResponse.success().put("data", contractMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse contractRevokeCheck(int id, String content) {
        try {
            contract contract = new contract();
            contract.setContractId(id);
            contract.setRemark(content);
            contract.setCheckStatus(4);
            contract.setUpdateTime(new Date());
            contractMapper.updateByPrimaryKeySelective(contract);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse contractTransfer(int owner_user_id, int contract_id) {
        try {
            contract contract = new contract();
            contract.setContractId(contract_id);
            contract.setOwnerUserId((long) owner_user_id);
            contract.setUpdateTime(new Date());
            contractMapper.updateByPrimaryKeySelective(contract);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }
}
