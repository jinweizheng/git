package com.igeek.student.service;

import java.util.List;

import com.igeek.student.common.DictTree;
import com.igeek.student.pojo.Dict;

public interface DictService{
    

    List<DictTree> listDict(Dict dict);
    int saveDict(Dict dict);
    int del(Integer id);
}