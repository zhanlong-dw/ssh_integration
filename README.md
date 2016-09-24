# ssh_integration
一个用SSH整合的员工查询系统

整合过程：<br>
1. 加入 Spring<br>
　　1). 加入 jar 包<br>
　　2). 配置 web.xml 文件<br>
　　3). 加入 Spring 的配置文件. <br><br>
2. 加入 Hibernate<br>
　　1). 同时建立持久化类, 和其对应的 .hbm.xml 文件, 生成对应的数据表<br>
　　2). Spring 整合 Hibernate<br>
　　3). 步骤:<br>
　　　　①. 加入 jar 包<br>
　　　　②. 在类路径下加入 hibernate.cfg.xml 文件, 在其中配置 hibernate 的基本属性<br>
　　　　③. 建立持久化类, 和其对应的 .hbm.xml 文件<br>
　　　　④. 和 Spring 进行整合<br>
　　　　　　i.  加入 c3p0 和 MySQL 的驱动<br>
　　　　　　ii. 在 Spring 的配置文件中配置: 数据源, SessionFactory, 声明式事务<br>
　　　　⑤. 启动项目, 会看到生成对应的数据表<br><br>
3. 加入 Struts2<br>
　　1). 加入 jar 包: 若有重复的 jar 包, 则需要删除版本较低的. javassist-3.11.0.GA.jar<br>
　　2). 在 web.xml 文件中配置 Struts2 的 Filter<br>
　　3). 加入 Struts2 的配置文件<br>
　　4). 整合 Spring<br>
　　　　①. 加入 Struts2 的 Spring 插件的 jar 包<br>
　　　　②. 在 Spring 的配置文件中正常配置 Action, 注意 Action 的 scope 为 prototype<br>
　　　　③. 在 Struts2 的配置文件中配置 Action 时, class 属性指向该 Action 在 IOC 中的 id<br><br>
4. 完成功能. <br>
　　1). 获取所有的员工信息: 若在 Dao 中只查询 Employee 的信息, 而且 Employee 和 Department 还是使用的懒加载. 页面上还需要显示员工信息, 此时会出现懒加载异常, 代理对象不能被初始化:2.org.hibernate.LazyInitializationException: could not initialize proxy - no Session<br>
　　解决:<br>
　　　　①. 打开懒加载: 不推荐使用<br>
　　　　②. 获取 Employee 时使用 迫切左外连接同时初始化其关联的 Department 对象. <br>
　　　　③. 使用 OpenSessionInViewFilter: 后面再提.<br>

　　2). 删除员工信息: <br>
　　　　①. 正常删除, 返回值需要是 redirect 类型, 而且重定向到 emp-list<br>
　　　　②. 确定要删除吗? 的提示使用 jQuery 完成<br>
　　　　③. Ajax 的使用参见 struts-2.3.15.3-all/struts-2.3.15.3/docs/WW/docs/ajax.html<br>

　　3). 添加员工:<br>
　　　　①. 显示表单页面: 需要先查询所有的部门信息<br>
　　　　②. 使用 Struts2 的 ModelDriven 和 Preparable 拦截器<br>
　　　　③. 时间是一个字符串, 需要转为一个 Date 类型的对象<br>
