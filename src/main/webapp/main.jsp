<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Bootstrap Login Form Template</title>
    <!-- CSS -->
    <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500">
    <link rel="stylesheet" href="./login/assets/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="./login/assets/font-awesome/css/font-awesome.min.css">
    <link rel="stylesheet" href="./login/assets/css/form-elements.css">
    <link rel="stylesheet" href="./login/assets/css/style.css">
    <link rel="stylesheet" href="./boot/fonts/">
    <link rel="icon" href="img/favicon.ico">
    <script src="./boot/js/jquery-1.10.1.min.js"></script>
    <%-- <script src="./boot/js/jquery-2.2.1.min.js"></script>--%>
    <script src="./login/assets/js/scripts.js"></script>
    <script src="./login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="./login/assets/js/jquery.backstretch.min.js"></script>
    <script src="./boot/js/jquery.validate.min.js"></script>
    <script src="./boot/js/ajaxfileupload.js"></script>
    <script src="./kindeditor/kindeditor-all-min.js"></script>
    <script src="./kindeditor/lang/zh-CN.js"></script>
    <!--echart-->
    <script src="echarts/echarts.min.js"></script>
    <script src="echarts/china.js"></script>
    <!--goeasy-->
    <script src="goeasy/goeasy-1.0.3.js"></script>
    <script>

    </script>
    <style>
        .navbar-header {
            height: 55px;
        }

        .navbar-header a span {
            font-size: 27px;
            padding-top: 26px;
        }

        nav div.container-fluid {
            height: 40px;
        }

        nav.navbar.navbar-default {
            height: 70px;
        }

        a.navbar-brand {
            font-size: 26px;
            margin-top: 10px;
        }

        nav.navbar.navbar-default.navbar-fixed-bottom {
            height: 15px;
        }
    </style>
</head>
<nav class="navbar navbar-default">
    <div class="container-fluid">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                    data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">持明法洲后台管理系统</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="#">欢迎：<span class="text-primary">小黑</span></a></li>
                <li><a href="#">退出登录<span class="glyphicon glyphicon-log-out"></span></a></li>
            </ul>
        </div><!-- /.navbar-collapse -->
    </div><!-- /.container-fluid -->
</nav>

<!--左边手风琴-->
<div class="container-fluid">
    <div class="row">
        <div class="col-xs-2">

            <div class="panel-group" id="accordion" role="tablist" aria-multiselectable="true">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne"
                               aria-expanded="true" aria-controls="collapseOne">
                                轮播图管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseOne" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changeContent').load('rotation.jsp')">轮播图列表</a></li>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseTwo" aria-expanded="false" aria-controls="collapseTwo">
                                专辑管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changeContent').load('album.jsp')">专辑列表</a></li>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseThree" aria-expanded="false" aria-controls="collapseThree">
                                文章管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseThree" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingThree">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changeContent').load('article.jsp')">文章列表</a></li>
                        </div>
                    </div>
                </div>
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingF">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseF" aria-expanded="false" aria-controls="collapseF">
                                用户管理
                            </a>
                        </h4>
                    </div>
                    <div id="collapseF" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingF">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changeContent').load('userDetail.jsp')">用户列表</a></li>
                        </div>
                    </div>
                </div>

                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion"
                               href="#collapseFive" aria-expanded="false" aria-controls="collapseF">
                                统计分析
                            </a>
                        </h4>
                    </div>
                    <div id="collapseFive" class="panel-collapse collapse" role="tabpanel"
                         aria-labelledby="headingFive">
                        <div class="panel-body">
                            <li><a href="javascript:$('#changeContent').load('userDistribution.jsp')">统计分析</a></li>
                            <li><a href="javascript:$('#changeContent').load('userStatistics.jsp')">用户分布图</a></li>
                        </div>
                    </div>
                </div>

            </div>
        </div>

        <div id="changeContent" class="col-xs-10">

            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3>欢迎来到后台系统</h3>
                </div>
            </div>
            <div id="carousel-example-generic" class="carousel slide" data-ride="carousel">
                <!-- Indicators -->
                <ol class="carousel-indicators">
                    <li data-target="#carousel-example-generic" data-slide-to="0" class="active"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="1"></li>
                    <li data-target="#carousel-example-generic" data-slide-to="2"></li>
                </ol>

                <!-- Wrapper for slides -->
                <div class="carousel-inner" role="listbox">
                    <div class="item active">
                        <img src="${pageContext.request.contextPath}/img/shouye.jpg" alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                    <div class="item">
                        <img style="width: 100%;height: 327.59px" src="${pageContext.request.contextPath}/img/A2.jpg"
                             alt="...">
                        <div class="carousel-caption">
                        </div>
                    </div>
                </div>

                <!-- Controls -->
                <a class="left carousel-control" href="#carousel-example-generic" role="button" data-slide="prev">
                    <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                    <span class="sr-only">Previous</span>
                </a>
                <a class="right carousel-control" href="#carousel-example-generic" role="button" data-slide="next">
                    <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                    <span class="sr-only">Next</span>
                </a>
            </div>
        </div>
    </div>
</div>
<div class="row">
    <nav class="navbar navbar-default navbar-fixed-bottom">
        <div class="container">
            百知教育@zparkhr.com.cn
        </div>
    </nav>
</div>
</body>
</html>