package com.manager.nacelle_rent.dao;

import com.manager.nacelle_rent.entity.ElectricRes;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ElectricResMapper {
    List<ElectricRes> getElectricRes(String projectId);
}
