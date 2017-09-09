# Tax
协同办公平台-纳税服务系统

开发环境：Windows7、MyEclipse+Tomcat7.0、Mysql5.7等开发工具。

项目描述：系统后台的功能包括会员管理、信息发布管理、纳税咨询管理、投诉受理管理、预约服务管理、易告知管理、服务调查管理。后台开发中以SSH作为开发框架。在会员管理中利用POI导入大量的初始会员数据和导出系统的会员记录到Excel；信息发布管理引入UEditor来处理系统的多格式文本内容、图表、地图等；在系统的定时任务调度中利用配置和开发使用都方便的Quartz融合Spring中并精确制定任务的执行时间。在系统的投诉数统计图上使用跨终端的Fusioncharts来展示系统的2d/3d柱状图、饼图、曲线图、块图等几十种统计图表。

会员管理：可以对注册的会员信息进行管理并导入导出。

信息发布管理：在后台端可以添加信息，然后由特定管理人员进行信息审核并发布。 

纳税咨询管理：管理由用户提交的一些咨询纳税信息的问题并可以在后台端进行回复；
投诉受理管理：管理由用户提交的投诉信息并可以在后台端进行受理回复；自动受理历史待受理的投诉；生成年度投诉数3D统计图表；

预约服务管理：由预约事项和预约服务组成；其中事项对应有特定的处理人，当用户预约了事项后由该事项对应的处理人进行预约受理。预约的受理情况将在移动端可查看；

易告知管理：主要是在后台端做一个定时对批量手机号用户进行信息的推送和告知其纳税信息；易告知的信息类型包含催办和催缴，当系统定时任务到发布时机将自动推送信息给移动端用户；

服务调查管理：可以制定系统的调查问卷；问卷形式主要由单选和多选题构成，当问卷制定并发布后用户可以在移动端填写调查问卷；管理员可以在系统管理中查看某一问卷的统计情况。


