package com.cloud.modules.record.service;

import com.cloud.modules.record.entity.record;
import com.cloud.modules.record.entity.recordExample;
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
public class recordService {
    @Autowired
    com.cloud.modules.record.dao.recordMapper recordMapper;
    @Autowired
    com.cloud.modules.customer.dao.customerMapper customerMapper;

    public RestResponse recordIndex(String types, Integer types_id, Integer pageIndex, Integer pageSize) {
        try {
            recordExample example = new recordExample();
            MyPage page = new MyPage();
            example.createCriteria().andTypesEqualTo(types).andTypesIdEqualTo(types_id);
            //================分页================
            PageHelper.startPage(pageIndex, pageSize);//1.设置分页
            List<record> list = recordMapper.selectByExample(example);//2.进行查询
            PageInfo<record> pageInfo = new PageInfo<>(list);//3.进行分页
            //====================================
            page = MyPage.newInstance(pageInfo);
            return RestResponse.success().put("data", page);
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse recordSave(record record) {
        try {
            record.setTypes("crm_leads");
            record.setUpdateTime(new Date());
            record.setCreateTime(new Date());
            recordMapper.insertSelective(record);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }


}
