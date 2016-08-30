package com.webLean.dao;

import com.webLean.domain.WechatQrcode;
import java.util.List;

public interface WechatQrcodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(WechatQrcode record);

    WechatQrcode selectByPrimaryKey(Long id);

    List<WechatQrcode> selectAll();

    int updateByPrimaryKey(WechatQrcode record);
}