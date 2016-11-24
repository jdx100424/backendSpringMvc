<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE Html>
<html>
 <head>
  <jsp:include page="../common/bootstrap.jsp" flush="false" />
 </head>
 <body style="margin-top: 50px;">
  <jsp:include page="../common/navbar.jsp" flush="false" />
  <div class="container-fluid">
    <div class="row">
      <div class="col-sm-3 col-md-2" id="sidebar" style="padding: 0;">
        <jsp:include page="../common/sidebar.jsp" flush="false" />
      </div>
      <div class="col-sm-9 col-md-10">
        <h3 class="page-header header">ECHO<small>echo测试</small></h3>
        <div class="row" style="padding-top: 15px; ">
          <div class="col-sm-12 col-md-12">
            <div class="panel panel-default">
              <div class="panel-heading"><span class="icon glyphicon glyphicon-certificate"></span>mysqlSelectAndDubbo</div>
              <div class="panel-body">
                <ul class="list-unstyled ul-group">
                  <li>${echoHasResultSelect}</li>
                </ul>
              </div>
            </div>
          </div>

          <div class="col-sm-12 col-md-12">
            <div class="panel panel-default">
              <div class="panel-heading"><span class="icon glyphicon glyphicon-certificate"></span>mysqlInsert</div>
              <div class="panel-body">
                <ul class="list-unstyled ul-group">
                  <li>${echoHasResultInsert}</li>
                </ul>
              </div>
            </div>
          </div>
          
          <div class="col-sm-12 col-md-12">
            <div class="panel panel-default">
              <div class="panel-heading"><span class="icon glyphicon glyphicon-certificate"></span>redis</div>
              <div class="panel-body">
                <ul class="list-unstyled ul-group">
                  <li>${redisHasResult}</li>
                </ul>
              </div>
            </div>
          </div>
          
          <div class="col-sm-12 col-md-12">
            <div class="panel panel-default">
              <div class="panel-heading"><span class="icon glyphicon glyphicon-certificate"></span>kakfaSend</div>
              <div class="panel-body">
                <ul class="list-unstyled ul-group">
                  <li>${kakfaResult}</li>
                </ul>
              </div>
            </div>
          </div>
          <!-- 
          <div class="col-sm-6 col-md-6" style="float: right;">
            <div class="panel panel-default">
              <div class="panel-heading"><span class="icon glyphicon glyphicon-certificate"></span>Disconf</div>
              <div class="panel-body">
                <ul class="list-unstyled ul-group">
                  <li>${disconfResult}</li>
                </ul>
              </div>
            </div>
          </div>-->
          
         <div class="col-sm-12 col-md-12">
            <div class="panel panel-default">
              <div class="panel-heading"><span class="icon glyphicon glyphicon-certificate"></span>Disconf</div>
              <div class="panel-body">
                <ul class="list-unstyled ul-group">
                  <li>${disconfResult}</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
 </body>
</html>
