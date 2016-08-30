package com.webLean.dao;

import com.webLean.domain.WechatProperties;
import java.util.List;

public interface WechatPropertiesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatProperties record);

    WechatProperties selectByPrimaryKey(Long id);

    List<WechatProperties> selectAll();

    int updateByPrimaryKey(WechatProperties record);
}