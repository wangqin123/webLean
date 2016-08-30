package com.webLean.dao;

import com.webLean.domain.WechatReplykey;
import java.util.List;

public interface WechatReplykeyMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatReplykey record);

    WechatReplykey selectByPrimaryKey(Long id);

    List<WechatReplykey> selectAll();

    int updateByPrimaryKey(WechatReplykey record);
}