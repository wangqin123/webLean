package com.webLean.dao;

import com.webLean.domain.WechatScanRecord;
import java.util.List;

public interface WechatScanRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatScanRecord record);

    WechatScanRecord selectByPrimaryKey(Long id);

    List<WechatScanRecord> selectAll();

    int updateByPrimaryKey(WechatScanRecord record);
}