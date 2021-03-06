package com.cloud.modules.message.entity;

import java.util.Date;

public class message {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.message_id
     *
     * @mbggenerated
     */
    private Long messageId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.title
     *
     * @mbggenerated
     */
    private String title;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.content
     *
     * @mbggenerated
     */
    private String content;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.label
     *
     * @mbggenerated
     */
    private Integer label;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.type
     *
     * @mbggenerated
     */
    private Integer type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.type_id
     *
     * @mbggenerated
     */
    private Integer typeId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.create_user
     *
     * @mbggenerated
     */
    private Long createUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.recipient_user
     *
     * @mbggenerated
     */
    private Long recipientUser;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.create_time
     *
     * @mbggenerated
     */
    private Date createTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.is_read
     *
     * @mbggenerated
     */
    private Integer isRead;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column 72crm_admin_message.read_time
     *
     * @mbggenerated
     */
    private Date readTime;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.message_id
     *
     * @return the value of 72crm_admin_message.message_id
     *
     * @mbggenerated
     */
    public Long getMessageId() {
        return messageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.message_id
     *
     * @param messageId the value for 72crm_admin_message.message_id
     *
     * @mbggenerated
     */
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.title
     *
     * @return the value of 72crm_admin_message.title
     *
     * @mbggenerated
     */
    public String getTitle() {
        return title;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.title
     *
     * @param title the value for 72crm_admin_message.title
     *
     * @mbggenerated
     */
    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.content
     *
     * @return the value of 72crm_admin_message.content
     *
     * @mbggenerated
     */
    public String getContent() {
        return content;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.content
     *
     * @param content the value for 72crm_admin_message.content
     *
     * @mbggenerated
     */
    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.label
     *
     * @return the value of 72crm_admin_message.label
     *
     * @mbggenerated
     */
    public Integer getLabel() {
        return label;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.label
     *
     * @param label the value for 72crm_admin_message.label
     *
     * @mbggenerated
     */
    public void setLabel(Integer label) {
        this.label = label;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.type
     *
     * @return the value of 72crm_admin_message.type
     *
     * @mbggenerated
     */
    public Integer getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.type
     *
     * @param type the value for 72crm_admin_message.type
     *
     * @mbggenerated
     */
    public void setType(Integer type) {
        this.type = type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.type_id
     *
     * @return the value of 72crm_admin_message.type_id
     *
     * @mbggenerated
     */
    public Integer getTypeId() {
        return typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.type_id
     *
     * @param typeId the value for 72crm_admin_message.type_id
     *
     * @mbggenerated
     */
    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.create_user
     *
     * @return the value of 72crm_admin_message.create_user
     *
     * @mbggenerated
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.create_user
     *
     * @param createUser the value for 72crm_admin_message.create_user
     *
     * @mbggenerated
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.recipient_user
     *
     * @return the value of 72crm_admin_message.recipient_user
     *
     * @mbggenerated
     */
    public Long getRecipientUser() {
        return recipientUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.recipient_user
     *
     * @param recipientUser the value for 72crm_admin_message.recipient_user
     *
     * @mbggenerated
     */
    public void setRecipientUser(Long recipientUser) {
        this.recipientUser = recipientUser;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.create_time
     *
     * @return the value of 72crm_admin_message.create_time
     *
     * @mbggenerated
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.create_time
     *
     * @param createTime the value for 72crm_admin_message.create_time
     *
     * @mbggenerated
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.is_read
     *
     * @return the value of 72crm_admin_message.is_read
     *
     * @mbggenerated
     */
    public Integer getIsRead() {
        return isRead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.is_read
     *
     * @param isRead the value for 72crm_admin_message.is_read
     *
     * @mbggenerated
     */
    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column 72crm_admin_message.read_time
     *
     * @return the value of 72crm_admin_message.read_time
     *
     * @mbggenerated
     */
    public Date getReadTime() {
        return readTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column 72crm_admin_message.read_time
     *
     * @param readTime the value for 72crm_admin_message.read_time
     *
     * @mbggenerated
     */
    public void setReadTime(Date readTime) {
        this.readTime = readTime;
    }
}