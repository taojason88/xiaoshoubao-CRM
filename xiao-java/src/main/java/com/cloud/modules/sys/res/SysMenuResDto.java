package com.cloud.modules.sys.res;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SysMenuResDto {
    private  String id;

    private  String pid;

    private String name;

    private String url;
}
