package com.cloud.modules.crm.dao;

import com.cloud.modules.crm.entity.crm_leadsEntity;
import com.cloud.modules.crm.entity.crm_leadsExample;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface crm_leadsMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    int countByExample(crm_leadsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    int deleteByExample(crm_leadsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    int deleteByPrimaryKey(Integer leadsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    int insert(crm_leadsEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    int insertSelective(crm_leadsEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    List<crm_leadsEntity> selectByExample(crm_leadsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    crm_leadsEntity selectByPrimaryKey(Integer leadsId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    int updateByExampleSelective(@Param("record") crm_leadsEntity record, @Param("example") crm_leadsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    int updateByExample(@Param("record") crm_leadsEntity record, @Param("example") crm_leadsExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    int updateByPrimaryKeySelective(crm_leadsEntity record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table 72crm_crm_leads
     *
     * @mbggenerated
     */
    int updateByPrimaryKey(crm_leadsEntity record);
}