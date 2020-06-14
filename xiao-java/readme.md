sys_user   用户表
sys_user_role   用户和角色表
sys_user_token  用户token

72crm_admin_record  跟进记录

代码生成
http://localhost:8076/sys/generator/code?tables=SYS_CAPTCHA&projectName=platform-plus&packageName=com.cloud.modules&author=孙烽翔&token=85948c1c0607f8700ead94a1adf5668a


后台用户登录接口
http://localhost:8071/sys/login
{
	"userName" : "admin",
	"password" : "admin"
}

//权限接口
http://localhost:8071/sys/menu/nav

//用户列表
http://localhost:8071/sys/user/list?page=1&limit=10&userName=
//添加用户
http://localhost:8071/sys/user/save
{
	"userName": "sfx12",
	"sex": "1", 
	"realName": "ccc",
	"salt": "", 
	"email": "", 
    "mobile": "", 
	"orgNo": "010101",
	"status": 1,
    "roleIdList": [
    ]
}
//用户删除
http://localhost:8071/sys/user/delete
['abc','cccc']
//更新
http://localhost:8071/sys/user/update
{
	"userName": "sfx12",
	"sex": "1", 
	"realName": "ccc",
	"salt": "", 
	"email": "", 
    "mobile": "", 
	"orgNo": "010101",
	"status": 1,
    "roleIdList": [
    ]
}


用户登录接口
http://localhost:8071/login/login
{
	"userName" : "admin",
	"password" : "admin"
} 

