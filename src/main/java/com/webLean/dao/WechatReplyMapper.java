package com.webLean.dao;

import com.webLean.domain.WechatReply;
import java.util.List;

public interface WechatReplyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatReply record);

    WechatReply selectByPrimaryKey(Long id);

    List<WechatReply> selectAll();

    int updateByPrimaryKey(WechatReply record);
}