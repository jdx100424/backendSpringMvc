<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<nav class="navbar navbar-inverse navbar-fixed-top">
	<div class="container-fulid" style="padding-right: 15px;">
		<div class="navbar-header">
			<a class="navbar-brand" target="_blank" href="${projectUrl}">maoshen</a>
		</div>
		<div class="navbar-collapse collapse">
			<ul class="nav navbar-nav">
				<li><a class="dropdown-toggle" data-toggle="dropdown" href="#">快捷路径<span class="caret" /></a>
					<ul class="dropdown-menu">
						<li><a href="${projectUrl}/backend/posts/edit">XX</a></li>
						<li><a href="${projectUrl}/backend/links/edit">YY</a></li>
						<li><a href="${projectUrl}/backend/users/edit">ZZ</a></li>
					</ul></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<!-- 
				<li><a href="#">Dashboard</a></li>-->
				<li><a class="dropdown-toggle" data-toggle="dropdown" href="#">你好,""<span
						class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="${projectUrl}/backend/options/general">设置</a></li>
						<li><a href="${projectUrl}/backend/users/my">编辑我的个人资料</a></li>
						<li><a href="${projectUrl}/backend/logout">退出</a></li>
					</ul></li>
			</ul>
			<!-- 
			<form action="#" class="navbar-form navbar-right">
				<input type="text" autocomplete="off" placeholder="Search..."
					class="form-control" />
			</form> -->
		</div>
	</div>
</nav>