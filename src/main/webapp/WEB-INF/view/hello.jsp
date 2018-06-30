<%@ page isELIgnored="false"%>
<%@ page language="Java" contentType="text/html;charset=UTF-8"
	pageEncoding="UTF-8"%>
<html>
<body>
<script src="<%=request.getContextPath()%>/static/jquery-3.3.1.js"></script>
	<h1>${test}</h1>
	<input type="button" value="退出" id="loginOut">
	<script type="text/javascript">
		$("#loginOut").on("click",function(){
			var url="<%=request.getContextPath()%>"+"/logout";
			$.post(url,function(data){
				console.log(data)
			},"json")
		})
	</script>
</body>
</html>