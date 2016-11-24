<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<ul class="side-menu">
	<li><a class="nav-header" href="#"> <i
			class="left glyphicon glyphicon-home" aria-hidden="true"></i>系统设置 <i
			class="right glyphicon glyphicon-chevron-down" aria-hidden="true"></i></a>
		<ul class="sub-menu" style="display: block;">
			<li><a href="${g.domain}/backend/index">首页</a></li>
		</ul></li>

	<li><a class="nav-header" href="#"> <i
			class="left glyphicon glyphicon-send" aria-hidden="true"></i>echo <i
			class="right glyphicon glyphicon-chevron-up" aria-hidden="true"></i>
	</a>
		<ul class="sub-menu" style="display: none;">
			<li><a href="${projectUrl}/echo/index">echo测试</a></li>
		</ul></li>

	<li><a class="nav-header" href="#"> <i
			class="left glyphicon glyphicon-send" aria-hidden="true"></i>XX功能 <i
			class="right glyphicon glyphicon-chevron-up" aria-hidden="true"></i>
	</a>
		<ul class="sub-menu" style="display: none;">
			<li><a href="${g.domain}/backend/posts/edit">XX增加</a></li>
			<li><a href="${g.domain}/backend/posts/edit">XX查询</a></li>
			<li><a href="${g.domain}/backend/posts/edit">XX统计</a></li>

		</ul></li>

</ul>
<script type="text/javascript"
	src="${g.domain}/resource/js/backend/sidebar.js"></script>