package com.cloud.utils;

import com.github.pagehelper.PageInfo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
public class MyPage<T> {
    // 总记录数
    private Long total;
    // 数据
    private List<T> list;

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public static MyPage newInstance(PageInfo pageInfo) {
        MyPage MyPage=new MyPage();

        MyPage.setList(pageInfo.getList());

        MyPage.setTotal(pageInfo.getTotal());

        return MyPage;
    }
}
