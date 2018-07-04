# java web dbcp2 数据库连接池演示
java web dbcp demo , with jdk 10 , MySQL Connector 8.x  and servlet-api 4  demo

最新版 JAVA 10 + Mysql Connector 8 Dbcp2 数据库连接池演示项目。


## 使用方法
1. 克隆本项目；
2. 使用IDEA打开；
3. 在文件 \src\main\resources\dbcp.properties 修改数据库连接信息
4. 使用数据库工具导入数据库表 user.sql
5. 运行


### dbcp.properties
```properties
driverClassName=com.mysql.cj.jdbc.Driver
url=jdbc:mysql://127.0.0.1:3306/test?useSSL=false&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true
&useLegacyDatetimeCode=false&serverTimezone=Hongkong
username=test
password=test888
initialSize=2
maxActive=15
maxIdle=2
minIdle=1
maxWait=30000
```

##ps

```text
数据库连接参数里面的连接符  & 不能修改为 &amp; 否则报错！
useSSL=false&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Hongkong

```
