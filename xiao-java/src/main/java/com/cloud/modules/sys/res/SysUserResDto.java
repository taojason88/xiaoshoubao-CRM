package com.cloud.modules.sys.res;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysUserResDto {
    /**
     * 用户ID
     */
    @TableId
    private String userId;

    /**
     * 用户性别
     */
    private Integer sex;

    /**
     * 用户真实姓名
     */
    private String realName;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 机构编码
     */
    private String orgNo;

    /**
     * 邮箱服务器地址
     */
    private String emailHost;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 邮箱密码
     */
    private String emailPw;

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 状态  0：禁用   1：正常
     */
    private Integer status;

    /**
     * 角色ID列表
     */
    private List<String> roleIdList;

    /**
     * 创建者ID
     */
    private String createUserId;

    /**
     * 创建者所属机构
     */
    private String createUserOrgNo;

    /**
     * 创建时间
     */
    private Date createTime;
}
