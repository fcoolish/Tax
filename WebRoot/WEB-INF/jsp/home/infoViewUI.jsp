<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>查看信息</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    

  </head>
  
  <body>
    <div align="center " style="text-align: center; width: 100%;height: 100%;overflow: hidden">
    	<!-- 标题 -->
        <div style="text-align: center; width: 100%;">
        	<h1><s:property value="info.title"/></h1>
        </div>
        <hr/>
        <!-- 时间、作者 -->
        <div style="text-align: center; width: 100%;color:#ccc;font-size:12px;">
                                    信息分类：<s:property value="#infoTypeMap[info.type]"/>&nbsp;&nbsp;&nbsp;&nbsp;
        	创建人：<s:property value="info.creator"/>&nbsp;&nbsp;&nbsp;&nbsp;
        	创建时间：<s:date name="info.createTime" format="yyyy-MM-dd HH:mm"/>	
        </div>
        <div style="text-align: center; width: 100%;padding:8px;">
        	<s:property value="info.content" escape="false"/>
        </div>
    </div>
    
  </body>
</html>
