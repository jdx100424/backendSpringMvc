<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE Html>
<html>
<head>
<jsp:include page="../common/bootstrap.jsp" flush="false" />


<script type="text/javascript">
	$(function() {
		var echoCount = document.getElementById("echoCount").value;
		var echoPage = document.getElementById("echoPage").value;
		selectEchoList(echoCount,echoPage);
	});

	function selectEchoList(echoCount,echoPage) {
		var projectUrl = document.getElementById("projectUrl").value;
		var echoCount = document.getElementById("echoCount").value;
		
		var effectRow = new Object();
		effectRow["count"] = echoCount;
		effectRow["page"] = echoPage;
		$
				.post(
						projectUrl + "/echo/listData",
						effectRow,
						function(rsp) {
							var json = eval('(' + rsp + ')');
							if (json.code == 200) {
								//LIST数据组合
								var echoListStr = "";
								for (var i = 0; i < json.data.echoList.length; i++) {
									var echoList = document
											.getElementById("echoList");
									echoListStr = echoListStr + "<tr><td>"
											+ json.data.echoList[i].id
											+ "</td><td>"
											+ json.data.echoList[i].name
											+ "</td></tr>";
								}
								echoList.innerHTML = echoListStr;
								//分页组合
								var echoListCount = document.getElementById("echoListCount");
								var pages = parseInt(json.data.total/ json.data.count) + 1;
								echoListCount.innerHTML = "第" + json.data.page + "页, 共" + pages + "页, "+ json.data.total + "项";

							    //分页按钮
							    var showButtonCount = 5;
							    var showButtonStr = "";
							    if(json.data.page != 1){
							    	var leftUrl = "selectEchoList(" + echoCount + "," + (json.data.page -1 ) + ")";
							    	showButtonStr = showButtonStr + "<li><span onClick='" + leftUrl + "' aria-hidden='true'>&laquo;</span></li>";
							    }
							    for(var j = 0;j < showButtonCount;j++){
									if(json.data.page > 3){
										if(json.data.page - 2 + j <= pages){
									    	var middleUrl = "selectEchoList(" + echoCount + "," + (json.data.page - 2 + j ) + ")";
											showButtonStr = showButtonStr + "<li><span onClick='" + middleUrl + "' aria-hidden='true'>" + (json.data.page - 2 + j) + "</span></li>";
										}
									}else{
										var middleUrl = "selectEchoList(" + echoCount + "," + (1 + j ) + ")";
										showButtonStr = showButtonStr + "<li><span onClick='" + middleUrl + "' aria-hidden='true'>" + (1 + j) + "</span></li>";	
									}
								}
							    if(json.data.page != pages){
							    	var rightUrl = "selectEchoList(" + echoCount + "," + (json.data.page + 1 ) + ")";
							    	showButtonStr = showButtonStr + "<li><span onClick='" + rightUrl + "' aria-hidden='true'>&raquo;</span></li>"
							    }
							    var echoListCountSqu = document.getElementById("echoListCountSqu");
							    echoListCountSqu.innerHTML = showButtonStr;
							} else {
								alert(json.message);
							}
						}, "text").error(function(rsp) {
					alert("exception");
				});
	}
</script>


</head>
<body style="margin-top: 50px;">
	<input type='hidden' value="${projectUrl}" id='projectUrl' />
	<input type='hidden' value="${count}" id='echoCount' />
	<input type='hidden' value="${page}" id='echoPage' />
	<jsp:include page="../common/navbar.jsp" flush="false" />
	<div class="container-fluid">
		<div class="row">
			<div class="col-sm-3 col-md-2" id="sidebar" style="padding: 0;">
				<jsp:include page="../common/sidebar.jsp" flush="false" />
			</div>
			<div class="col-sm-9 col-md-10">
				<ol class="breadcrumb header">
					<li><span class="icon glyphicon glyphicon-home"></span>echo</li>
					<li class="active">echo简单数据查询</li>
				</ol>
				<div class="panel panel-default">
					<div class="panel-heading">
						<span class="icon glyphicon glyphicon-list"></span>列表
					</div>
					<div class="panel-body">
						<table id="post-table" class="table table-striped list-table">
							<thead>
								<tr>
									<th>id</th>
									<th>name</th>
								</tr>
							</thead>
							<tbody id='echoList'>

							</tbody>
						</table>
						<div class="row-fulid">
							<div class="col-sm-6 col-md-6">
								<div class="page-info" id='echoListCount'></div>
							</div>
							<div class="col-sm-6 col-md-6">
								<div id="pager">
									<ul class="pagination" id="echoListCountSqu">
										<!--  
										<li><a href="${pageUrl}"><span aria-hidden="true">&laquo;</span></a></li>
										<li><a href="${pageUrl}"><span aria-hidden="true">1</span></a></li>
										<li><a href="${pageUrl}"><span aria-hidden="true">&raquo;</span></a></li>-->
									</ul>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>