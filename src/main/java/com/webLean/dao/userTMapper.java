package com.webLean.dao;

import com.webLean.domain.userT;
import java.util.List;

public interface userTMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(userT record);

    userT selectByPrimaryKey(Integer id);

    List<userT> selectAll();

    int updateByPrimaryKey(userT record);
}