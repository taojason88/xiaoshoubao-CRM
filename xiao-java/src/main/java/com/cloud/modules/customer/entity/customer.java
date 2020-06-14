package com.cloud.modules.customer.entity;

import com.cloud.modules.user.entity.user_info;

import java.util.Date;

public class customer {
    //================自定义================
    private user_info owner_user_id_info;
    private user_info create_user_id_info;

    public user_info getOwner_user_id_info() {
        return owner_user_id_info;
    }

    public void setOwner_user_id_info(user_info owner_user_id_info) {
        this.owner_user_id_info = owner_user_id_info;
    }
    //=================================

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.customer_id
     *
     * @mbggenerated
     */
    private Integer customerId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.customer_name
     *
     * @mbggenerated
     */
    private String customerName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.followup
     *
     * @mbggenerated
     */
    private Integer followup;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.is_lock
     *
     * @mbggenerated
     */
    private Integer isLock;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.next_time
     *
     * @mbggenerated
     */
    private Date nextTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.deal_status
     *
     * @mbggenerated
     */
    private Integer dealStatus;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.mobile
     *
     * @mbggenerated
     */
    private String mobile;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.telephone
     *
     * @mbggenerated
     */
    private String telephone;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.website
     *
     * @mbggenerated
     */
    private String website;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.remark
     *
     * @mbggenerated
     */
    private String remark;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.create_user_id
     *
     * @mbggenerated
     */
    private Long createUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.owner_user_id
     *
     * @mbggenerated
     */
    private Long ownerUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.ro_user_id
     *
     * @mbggenerated
     */
    private String roUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.rw_user_id
     *
     * @mbggenerated
     */
    private String rwUserId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.address
     *
     * @mbggenerated
     */
    private String address;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.location
     *
     * @mbggenerated
     */
    private String location;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.detail_address
     *
     * @mbggenerated
     */
    private String detailAddress;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.lng
     *
     * @mbggenerated
     */
    private String lng;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.lat
     *
     * @mbggenerated
     */
    private String lat;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.update_time
     *
     * @mbggenerated
     */
    private Date updateTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.batch_id
     *
     * @mbggenerated
     */
    private String batchId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_crm_customer.last_content
     *
     * @mbggenerated
     */
    private String lastContent;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.customer_id
     *
     * @return the value of 72crm_crm_customer.customer_id
     * @mbggenerated
     */
    public Integer getCustomerId() {
        return customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.customer_id
     *
     * @param customerId the value for 72crm_crm_customer.customer_id
     * @mbggenerated
     */
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.customer_name
     *
     * @return the value of 72crm_crm_customer.customer_name
     * @mbggenerated
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.customer_name
     *
     * @param customerName the value for 72crm_crm_customer.customer_name
     * @mbggenerated
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName == null ? null : customerName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.followup
     *
     * @return the value of 72crm_crm_customer.followup
     * @mbggenerated
     */
    public Integer getFollowup() {
        return followup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.followup
     *
     * @param followup the value for 72crm_crm_customer.followup
     * @mbggenerated
     */
    public void setFollowup(Integer followup) {
        this.followup = followup;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.is_lock
     *
     * @return the value of 72crm_crm_customer.is_lock
     * @mbggenerated
     */
    public Integer getIsLock() {
        return isLock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.is_lock
     *
     * @param isLock the value for 72crm_crm_customer.is_lock
     * @mbggenerated
     */
    public void setIsLock(Integer isLock) {
        this.isLock = isLock;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.next_time
     *
     * @return the value of 72crm_crm_customer.next_time
     * @mbggenerated
     */
    public Date getNextTime() {
        return nextTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.next_time
     *
     * @param nextTime the value for 72crm_crm_customer.next_time
     * @mbggenerated
     */
    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.deal_status
     *
     * @return the value of 72crm_crm_customer.deal_status
     * @mbggenerated
     */
    public Integer getDealStatus() {
        return dealStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.deal_status
     *
     * @param dealStatus the value for 72crm_crm_customer.deal_status
     * @mbggenerated
     */
    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.mobile
     *
     * @return the value of 72crm_crm_customer.mobile
     * @mbggenerated
     */
    public String getMobile() {
        return mobile;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.mobile
     *
     * @param mobile the value for 72crm_crm_customer.mobile
     * @mbggenerated
     */
    public void setMobile(String mobile) {
        this.mobile = mobile == null ? null : mobile.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.telephone
     *
     * @return the value of 72crm_crm_customer.telephone
     * @mbggenerated
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.telephone
     *
     * @param telephone the value for 72crm_crm_customer.telephone
     * @mbggenerated
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.website
     *
     * @return the value of 72crm_crm_customer.website
     * @mbggenerated
     */
    public String getWebsite() {
        return website;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.website
     *
     * @param website the value for 72crm_crm_customer.website
     * @mbggenerated
     */
    public void setWebsite(String website) {
        this.website = website == null ? null : website.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.remark
     *
     * @return the value of 72crm_crm_customer.remark
     * @mbggenerated
     */
    public String getRemark() {
        return remark;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.remark
     *
     * @param remark the value for 72crm_crm_customer.remark
     * @mbggenerated
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.create_user_id
     *
     * @return the value of 72crm_crm_customer.create_user_id
     * @mbggenerated
     */
    public Long getCreateUserId() {
        return createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.create_user_id
     *
     * @param createUserId the value for 72crm_crm_customer.create_user_id
     * @mbggenerated
     */
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.owner_user_id
     *
     * @return the value of 72crm_crm_customer.owner_user_id
     * @mbggenerated
     */
    public Long getOwnerUserId() {
        return ownerUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.owner_user_id
     *
     * @param ownerUserId the value for 72crm_crm_customer.owner_user_id
     * @mbggenerated
     */
    public void setOwnerUserId(Long ownerUserId) {
        this.ownerUserId = ownerUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.ro_user_id
     *
     * @return the value of 72crm_crm_customer.ro_user_id
     * @mbggenerated
     */
    public String getRoUserId() {
        return roUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.ro_user_id
     *
     * @param roUserId the value for 72crm_crm_customer.ro_user_id
     * @mbggenerated
     */
    public void setRoUserId(String roUserId) {
        this.roUserId = roUserId == null ? null : roUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.rw_user_id
     *
     * @return the value of 72crm_crm_customer.rw_user_id
     * @mbggenerated
     */
    public String getRwUserId() {
        return rwUserId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.rw_user_id
     *
     * @param rwUserId the value for 72crm_crm_customer.rw_user_id
     * @mbggenerated
     */
    public void setRwUserId(String rwUserId) {
        this.rwUserId = rwUserId == null ? null : rwUserId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.address
     *
     * @return the value of 72crm_crm_customer.address
     * @mbggenerated
     */
    public String getAddress() {
        return address;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.address
     *
     * @param address the value for 72crm_crm_customer.address
     * @mbggenerated
     */
    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.location
     *
     * @return the value of 72crm_crm_customer.location
     * @mbggenerated
     */
    public String getLocation() {
        return location;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.location
     *
     * @param location the value for 72crm_crm_customer.location
     * @mbggenerated
     */
    public void setLocation(String location) {
        this.location = location == null ? null : location.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.detail_address
     *
     * @return the value of 72crm_crm_customer.detail_address
     * @mbggenerated
     */
    public String getDetailAddress() {
        return detailAddress;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.detail_address
     *
     * @param detailAddress the value for 72crm_crm_customer.detail_address
     * @mbggenerated
     */
    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.lng
     *
     * @return the value of 72crm_crm_customer.lng
     * @mbggenerated
     */
    public String getLng() {
        return lng;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.lng
     *
     * @param lng the value for 72crm_crm_customer.lng
     * @mbggenerated
     */
    public void setLng(String lng) {
        this.lng = lng == null ? null : lng.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.lat
     *
     * @return the value of 72crm_crm_customer.lat
     * @mbggenerated
     */
    public String getLat() {
        return lat;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.lat
     *
     * @param lat the value for 72crm_crm_customer.lat
     * @mbggenerated
     */
    public void setLat(String lat) {
        this.lat = lat == null ? null : lat.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.create_time
     *
     * @return the value of 72crm_crm_customer.create_time
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.create_time
     *
     * @param createTime the value for 72crm_crm_customer.create_time
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.update_time
     *
     * @return the value of 72crm_crm_customer.update_time
     * @mbggenerated
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.update_time
     *
     * @param updateTime the value for 72crm_crm_customer.update_time
     * @mbggenerated
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.batch_id
     *
     * @return the value of 72crm_crm_customer.batch_id
     * @mbggenerated
     */
    public String getBatchId() {
        return batchId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.batch_id
     *
     * @param batchId the value for 72crm_crm_customer.batch_id
     * @mbggenerated
     */
    public void setBatchId(String batchId) {
        this.batchId = batchId == null ? null : batchId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_crm_customer.last_content
     *
     * @return the value of 72crm_crm_customer.last_content
     * @mbggenerated
     */
    public String getLastContent() {
        return lastContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_crm_customer.last_content
     *
     * @param lastContent the value for 72crm_crm_customer.last_content
     * @mbggenerated
     */
    public void setLastContent(String lastContent) {
        this.lastContent = lastContent == null ? null : lastContent.trim();
    }
}