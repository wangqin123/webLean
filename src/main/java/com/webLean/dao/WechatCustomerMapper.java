package com.webLean.dao;

import com.webLean.domain.WechatCustomer;
import java.util.List;

public interface WechatCustomerMapper {
    int deleteByPrimaryKey(String id);

    int insert(WechatCustomer record);

    WechatCustomer selectByPrimaryKey(String id);

    List<WechatCustomer> selectAll();

    int updateByPrimaryKey(WechatCustomer record);
}