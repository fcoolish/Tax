<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    pageContext.setAttribute("basePath", request.getContextPath()+"/") ;
%>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>投诉信息</title>
</head>
<body class="rightBody">
    <div class="vp_d_1">
    <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
        <div class="vp_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>工作主页</strong>&nbsp;-&nbsp;投诉信息</div></div>
    <div class="tableH2">投诉详细信息<span style="color:red;">(<s:property value="#complainStateMap[comp.state]"/>)</span></div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
    	<tr><td colspan="2" align="center">投诉人信息</td></tr>
        <tr>
            <td class="tdBg" width="250px">是否匿名投诉：</td>
            <td><s:property value="comp.isNm?'匿名投诉':'非匿名投诉'"/></td>
        </tr>
        <tr>
            <td class="tdBg">投诉人单位：</td>
            <td>
            <s:if test="!comp.isNm">
            	<s:property value="comp.compCompany"/>
            </s:if>
            </td>
        </tr>
        <tr>
            <td class="tdBg">投诉人姓名：</td>
            <td><s:if test="!comp.isNm"><s:property value="comp.compName"/></s:if></td>
        </tr>
        <tr>
            <td class="tdBg">投诉人手机：</td>
            <td>
            <s:if test="!comp.isNm">
            <s:property value="comp.compMobile"/>
            </s:if><s:elseif test="%{comp.compMobile.length() > 10}">
            <s:property value="%{comp.compMobile.substring(0,3) + '****' + comp.compMobile.substring(7,11)}"/>
            </s:elseif>
            </td>
        </tr>
        <tr><td colspan="2" align="center">投诉信息</td></tr>
        <tr>
            <td class="tdBg">投诉时间：</td>
            <td>
            	<s:date name="comp.compTime" format="yyyy-MM-dd HH:mm"/>
            </td>
        </tr>
        <tr>
            <td class="tdBg">被投诉部门：</td>
            <td><s:property value="comp.toCompDept"/></td>
        </tr>
        <tr>
            <td class="tdBg">被投诉人：</td>
            <td><s:property value="comp.toCompName"/></td>
        </tr>
        <tr>
            <td class="tdBg">投诉标题：</td>
            <td><s:property value="comp.compTitle"/></td>
        </tr>
        <tr>
            <td class="tdBg">投诉内容：</td>
            <td><s:property value="comp.compContent" escape="false"/></td>
        </tr>
        <tr><td colspan="2" align="center">受理信息</td></tr>
        <tr>
            <td colspan="2">
            	<s:iterator value="comp.complainReplies" status="st">
            		<fieldset style="border: solid 1px #c0c0c0;margin-top:5px;"><legend style="color:green;font-weight:bold;">回复<s:property value="#st.count"/>&nbsp;</legend>
						<div style="width:100%; text-align:center;color:#ccc;maring-top:5px;">
						回复部门：<s:property value="replyDept"/>&nbsp;&nbsp;
						回复人：<s:property value="replyer"/>&nbsp;&nbsp;
						回复时间：<s:date name="replyTime" format="yyyy-MM-dd HH:mm"/>
						</div>
						<div style="width:100%;maring-top:10px;font-size:13px;padding-left:5px;">
						<s:property value="replyContent"/>
						</div>
					</fieldset>
            	</s:iterator>
            </td>
        </tr>
    </table>
    </div></div>
    <div style="width:1%;float:left;">&nbsp;&nbsp;&nbsp;&nbsp;</div>
    </div>
</body>
</html>