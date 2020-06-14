package com.cloud.modules.record.dao;

import com.cloud.modules.record.entity.record;
import com.cloud.modules.record.entity.recordExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Component
@Mapper
public interface recordMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    int countByExample(recordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    int deleteByExample(recordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer recordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    int insert(record record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    int insertSelective(record record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    List<record> selectByExample(recordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    record selectByPrimaryKey(Integer recordId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") record record, @Param("example") recordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") record record, @Param("example") recordExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(record record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_admin_record
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(record record);
}