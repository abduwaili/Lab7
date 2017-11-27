<!DOCTYPE>
<html>
<head>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>首页</title>
<style type="text/css">
</style>
<link href="css/index_style.css" rel="stylesheet" type="text/css">
</head>


<body>
	
    <!--顶部-->
<div class="dingbu">
  <div class="denglu"><a href="stlogin.jsp">登录</a></div>
            <img src="logo/logo0.png" width="356" height="86" class="logo_1">
            
            <!--搜索框-->
            <div align="center">
            	  <form name="form1" method="post" action="">
                  	<input type="text" name="textfield" id="textfield" placeholder="请输入教师名字" class="sousuokuang">
                    <input type="submit" name="button" id="button" value="搜索" class="sousuo_anniu">
                </form>
        </div>
     </div><!--顶部-->



    <!--中间-->
    <div class="zhongjian">
    
          <!--中间左边-->	
          <div class="zhongjian_zuo">
                <div>教师推荐</div>
                <div class="jiaoshituijian">
                	
                    <%
                	for (int i=0;i<10;i++)
                	{
                		out.println("第   "+i+"  个老师\n");
                	%>
                	<br><br><br>
                	<% 
                	}
                	%>

                </div>
          </div>
              
          <!--中间右边-->
          <div class="zhongjian_you">   
          
          	  <div>教师风采</div>       
            <div class="jiaoshifengcai"><img src="images/jiaoshifdengcai_1.jpg" width="100%" height="100%"></div>
              
            <div>最新消息</div>
              <div class="zuixinxiaoxi"><img src="images/jiaoshifdengcai_2.jpg" width="100%" height="100%"></div>
              
      </div>
          
    </div><!--中间-->
    
        <!--底部-->
	<div class="dibu" align="center">
	  <table width="80%">
	    <tr>
	      <td align="center">关于本站</td>
	      <td align="center">加入我们</td>
	      <td align="center">联系方式</td>
	      <td align="center">投诉建议</td>
        </tr>
      </table>
    </div><!--底部-->
    
    

    
	

</body>




</html>