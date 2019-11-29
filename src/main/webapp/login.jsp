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
    <link rel="shortcut icon" href="./login/assets/ico/favicon.png">
    <link rel="apple-touch-icon-precomposed" sizes="144x144"
          href="./login/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114"
          href="./login/assets/ico/apple-touch-icon-114-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="72x72"
          href="./login/assets/ico/apple-touch-icon-72-precomposed.png">
    <link rel="apple-touch-icon-precomposed" href="./login/assets/ico/apple-touch-icon-57-precomposed.png">

    <script src="./boot/js/jquery-2.2.1.min.js"></script>
    <script src="./login/assets/bootstrap/js/bootstrap.min.js"></script>
    <script src="./login/assets/js/jquery.backstretch.min.js"></script>
    <script src="./login/assets/js/scripts.js"></script>
    <script src="./boot/js/jquery.validate.min.js"></script>
    <script src="./boot/js/jquery.validate.min.js"></script>
    <style>
        body {
            background: url("/login/assets/img/backgrounds/1.jpg");
        }
    </style>
    <script>
        $(function () {
            $("#captchaImage").click(function () {
                $("#captchaImage").prop("src", "${pageContext.request.contextPath}/Kaptcha?t=" + new Date().getTime())
            })
            //表单验证
            $.extend($.validator.messages, {
                required: "<span style='color: orangered'>这是必填字段</span>",
            });
            //进行懒加载
            $("#loginButtonId").click(function () {
                var flag = $("#loginForm").valid();
                if (flag) {
                    $.ajax({
                        url: "${pageContext.request.contextPath}/admin/login",
                        type: "post",
                        data: $("#loginForm").serialize(),
                        datatype: "json",
                        success: function (data) {
                            if (data.msg == 'ok') {
                                location.href = '${app}/main.jsp';
                            } else {
                                $("#msgDiv").html(data.msg)
                            }
                        }
                    });
                }
            });
        });
    </script>
</head>

<body>
<!-- Top content -->
<div class="top-content">
    <div class="inner-bg">
        <div class="container">
            <div class="row">
                <div class="col-sm-8 col-sm-offset-2 text">
                    <h1><strong>CMFZ</strong> Login Form</h1>
                    <div class="description">
                        <p>
                            <a href="#"><strong>CMFZ</strong></a>
                        </p>
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-6 col-sm-offset-3 form-box">
                    <div class="form-top" style="width: 450px">
                        <div class="form-top-left">
                            <h3>Login to showall</h3>
                            <p>Enter your username and password to log on:</p>
                        </div>
                        <div class="form-top-right">
                            <i class="fa fa-key"></i>
                        </div>
                    </div>
                    <div class="form-bottom" style="width: 450px">
                        <form role="form" action="${pageContext.request.contextPath}/admin/login" method="post"
                              class="login-form" id="loginForm">
                            <span style="color: #4cae4c" id="msgDiv"></span>
                            <div class="form-group">
                                <label class="sr-only" for="form-username">Username</label>
                                <input type="text" required name="username" placeholder="请输入用户名..."
                                       class="form-username form-control" id="form-username">
                            </div>
                            <div class="form-group">
                                <label class="sr-only" for="form-password">Password</label>
                                <input type="password" required name="password" placeholder="请输入密码..."
                                       class="form-password form-control" id="form-password">
                            </div>
                            <div class="form-group">
                                <img id="captchaImage" style="height: 48px" class="captchaImage"
                                     src="${pageContext.request.contextPath}/Kaptcha">
                                <input style="width: 289px;height: 50px;border:3px solid #ddd;border-radius: 4px;"
                                       required type="test" name="code" id="form-code">
                            </div>
                            <input type="button" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;"
                                   id="loginButtonId" value="登录">
                            <%-- <input type="button" style="width: 400px;border:1px solid #9d9d9d;border-radius: 4px;" id="loginButtonId" value="登录">--%>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

</html>