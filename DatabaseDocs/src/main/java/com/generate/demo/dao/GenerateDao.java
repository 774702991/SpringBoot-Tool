package com.generate.demo.dao;

import com.generate.demo.entity.TableInfo;
import com.generate.demo.entity.TableFileds;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author FSARSIGHT-IOT-01
 * @date Created in 2019-12-04 15:22
 */
@Mapper
public interface GenerateDao {
    @Select("select table_name as name,table_comment as comment from information_schema.tables where table_schema =#{dbName} order by table_name")
    List<TableInfo> getTables(@Param("dbName") String dbName);

    @Select("SHOW FULL FIELDS FROM ${tableName}")
    List<TableFileds> getTableStructure(@Param("tableName") String tableName);
}
