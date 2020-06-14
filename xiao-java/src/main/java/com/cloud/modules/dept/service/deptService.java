package com.cloud.modules.dept.service;

import com.cloud.modules.dept.entity.dept;
import com.cloud.modules.dept.entity.deptExample;
import com.cloud.utils.MyPage;
import com.cloud.utils.RestResponse;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class deptService {
    @Autowired
    com.cloud.modules.dept.dao.deptMapper deptMapper;
    public RestResponse deptIndex(Integer pageIndex, Integer pageSize) {
        MyPage page = new MyPage();
        try {
            deptExample example = new deptExample();
            //================分页================
            PageHelper.startPage(pageIndex, pageSize);//1.设置分页
            List<dept> list = deptMapper.selectByExample(example);//2.进行查询
            PageInfo<dept> pageInfo = new PageInfo<>(list);//3.进行分页
            //====================================
            page = MyPage.newInstance(pageInfo);
            return RestResponse.success().put("data", page);
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse deptSave(dept dept) {
        try {
            deptMapper.insertSelective(dept);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse deptDelete(int id) {
        try {
            deptMapper.deleteByPrimaryKey(id);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse deptUpdate(dept dept) {
        try {
            deptMapper.updateByPrimaryKeySelective(dept);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse deptDetail(int id) {
        try {
            return RestResponse.success().put("data", deptMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            return RestResponse.error();
        }
    }
}