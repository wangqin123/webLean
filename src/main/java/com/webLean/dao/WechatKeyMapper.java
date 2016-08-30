package com.webLean.dao;

import com.webLean.domain.WechatKey;
import java.util.List;

public interface WechatKeyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatKey record);

    WechatKey selectByPrimaryKey(Long id);

    List<WechatKey> selectAll();

    int updateByPrimaryKey(WechatKey record);
}