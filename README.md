## Introduction ##
一个后台管理安全框架


## Design ##
sms = SpringMVC + Mybatis + Shiro


## Deploy ##

1. 导入[sql](https://github.com/jasonsoso/jason-sms/blob/master/src/main/resources/META-INF/sql/sms.sql "sql")
2. 修改[数据库连接](https://github.com/jasonsoso/jason-sms/blob/master/src/main/resources/configuration.properties)
3. `jetty:run`跑起来 
4. 访问本地`http://localhost:8080/`
5. 两个账号可以访问`admin/admin` , `test/test`