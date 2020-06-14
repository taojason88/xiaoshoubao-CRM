package com.cloud.modules.sys.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysOrgResDto {
    private  String id;

    private  String pid;

    private String orgType;

    private String Name;

    private String icon;
}
