package com.igeek.student.mapper;

import java.util.List;

import com.igeek.student.pojo.Dict;
import com.igeek.student.pojo.DictExample;

public interface DictMapper {
    int deleteByPrimaryKey(Integer did);

    int insert(Dict record);

    int insertSelective(Dict record);

    List<Dict> selectByExample(DictExample example);

    Dict selectByPrimaryKey(Integer did);

    int updateByPrimaryKeySelective(Dict record);

    int updateByPrimaryKey(Dict record);
    long count(Integer did);

}