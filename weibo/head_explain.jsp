<%@ page contentType="text/html; charset=gb2312" language="java" import="java.sql.*" errorPage="" %>
<jsp:directive.page import="com.wy.form.ArticleTypeForm"/>
<jsp:directive.page import="com.wy.form.ArticleForm"/>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
<link href="CSS/style.css" type="text/css"  rel="stylesheet">
<title>ǰ̨-˵��</title>
<%@ page language="java" import="java.util.*" %>
<style type="text/css">
<!--
body {
	background-image: url(images/bg_01.gif);
}
-->
</style></head>
<jsp:useBean id="articleTypeDao" scope="request" class="com.wy.dao.ArticleTypeDao"></jsp:useBean>
<jsp:useBean id="articleDao" scope="request" class="com.wy.dao.ArticleDao"></jsp:useBean>

<body>
<!--��ҳͷ����-->
<jsp:include page="head_top.jsp" flush="true" />



<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="74"><img src="images/head_06.jpg" width="74" height="846"></td>
    <td height="846" valign="top" background="images/head_07.jpg">
	<!--��¼�û�����-->
		<br>
		
		




<table width="390" border="1" align="center" cellpadding="1" cellspacing="1" bordercolor="#FFFFFF" bgcolor="#F7D069">
  <tr>
    <td height="25" align="center" bgcolor="#FFFFFF">

    
      <div align="left"><strong>Ұ�����վ�ͻ�����ල���ģ�</strong></div></td>
  </tr>
  <tr>
    <td height="55" valign="top" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;WOLF��վ�ͻ�����ල����Ϊ���ṩ�������õķ���WOLF�����ˡ��ͻ�����ල���ġ���ϣ�������ļල���ƽ�һ���ṩ���ǵķ���ˮƽ��</td>
  </tr>
  <tr>
    <td height="25" align="center" bgcolor="#FFFFFF"><div align="left"><strong>����Χ��</strong></div></td>
  </tr>
  <tr>
    <td height="61" valign="top" bgcolor="#FFFFFF">&nbsp;&nbsp;&nbsp;&nbsp;�ڷ��񼰽��׹�����Ա�����˵Ĳ�������Ϊ�������������ڣ�����̬�ȡ�����ʽ������ʱЧ���Լ����ù���֮��̰�ۡ��ܻߡ�������Ӫ˽Ӫ˽����Ϊ��</td>
  </tr>
  <tr>
    <td height="25" align="center" bgcolor="#FFFFFF"><div align="left"><strong>Ͷ�����䣺</strong></div></td>
  </tr>
  <tr>
    <td height="35" align="center" bgcolor="#FFFFFF"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;*****@****.com</div></td>
  </tr>
  <tr>
    <td height="25" align="center" bgcolor="#FFFFFF"><div align="left"><strong>Ͷ�ߴ��棺</strong></div></td>
  </tr>
  <tr>
    <td height="32" align="center" bgcolor="#FFFFFF"><div align="left">&nbsp;&nbsp;&nbsp;&nbsp;��0431��8*****66</div></td>
  </tr>
</table>






















</td>
    <td width="10" background="images/head_07.jpg"><img src="images/head_08.jpg" width="13" height="846"></td>
    <td width="184" valign="top">
	
	
	
	
	  	<!--�Ҳ��������-->
	  <jsp:include page="head_right.jsp" flush="true" />
	  
	  
	  
    </td>
    <td width="122"><img src="images/head_10.jpg" width="122" height="846"></td>
  </tr>
</table>
<!--��ҳβ����-->
<jsp:include page="head_down.jsp" flush="true" />




</body>
</html>
