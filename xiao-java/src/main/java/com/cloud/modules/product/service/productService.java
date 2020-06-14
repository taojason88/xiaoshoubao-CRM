package com.cloud.modules.product.service;

import com.cloud.modules.product.entity.product;
import com.cloud.modules.product.entity.productExample;
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
public class productService {
    @Autowired
    com.cloud.modules.product.dao.productMapper productMapper;
    @Autowired
    com.cloud.modules.customer.dao.customerMapper customerMapper;

    public RestResponse productIndex(long owner_user_id, Integer pageIndex, Integer pageSize) {
        try {
            MyPage page = new MyPage();
            productExample example = new productExample();
            example.createCriteria().andOwnerUserIdEqualTo(owner_user_id);
            System.out.println(owner_user_id);
            //================分页================
            PageHelper.startPage(pageIndex, pageSize);//1.设置分页
            List<product> list = productMapper.selectByExample(example);//2.进行查询
            PageInfo<product> pageInfo = new PageInfo<>(list);//3.进行分页
            //====================================
            page = MyPage.newInstance(pageInfo);
            return RestResponse.success().put("data", page);
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse productSave(product product) {
        try {
            product.setUpdateTime(new Date());
            product.setCreateTime(new Date());
            productMapper.insertSelective(product);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse productDelete(int id) {
        try {
            productMapper.deleteByPrimaryKey(id);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse productUpdate(product product) {
        try {
            product.setUpdateTime(new Date());
            productMapper.updateByPrimaryKeySelective(product);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }

    public RestResponse productDetail(int id) {
        try {
            return RestResponse.success().put("data", productMapper.selectByPrimaryKey(id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return RestResponse.error();
        }
    }

    public RestResponse status(int id, int status) {

        try {
            product product = new product();
            product.setStatus(status);
            product.setProductId(id);
            productMapper.updateByPrimaryKeySelective(product);
            return RestResponse.success();
        } catch (Exception e) {
            return RestResponse.error();
        }
    }


}
