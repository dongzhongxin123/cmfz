<%@page pageEncoding="UTF-8" isELIgnored="false" language="java" contentType="text/html; UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <link rel="stylesheet" type="text/css" href="boot/js/bootstrap.min.js">
    <script type="text/javascript" src="boot/js/jquery-2.2.1.min.js"></script>
    <title>百度一下</title>
    <script>
        //懒加载
        $(function () {
            $("#btnId").click(function () {

                var param = $("#inp").val();
                $.ajax({
                    url: "${pageContext.request.contextPath}/article/search",
                    type: "post",
                    data: {query: param, pageIndex: 1},
                    dataType: "json",
                    success: function (data) {
                        $("#ff").empty();
                        if (data.length == 0) {
                            var text = $("<p>").html("对不起，没有您想要搜索的");
                            $("#ff").append(text)
                        }
                        if (data.length != 0) {
                            $.each(data, function (index, article) {
                                var hr = $("<hr>");
                                var id = $("<p>").html("id："+article.id);
                                var title = $("<p>").html("标题："+article.title);
                                var author = $("<p>").html("作者："+article.author);
                                var content = $("<p>").html("内容："+article.content);
                                var status = $("<p>").html("状态："+article.status);
                                var publishTime = $("<p>").html("发布时间："+article.publishTime);
                                $("#ff").append(id).append(title).append(author).append(content).append(status).append(publishTime).append(hr)
                            })
                        }
                    }
                });

            });

        });
    </script>
</head>
<body>

<div class="container-fluid">
    <br>
    <div class="row">
        <div class="col-md-4 col-md-offset-4">
            <div class="input-group">
                <input type="text" class="form-control" id="inp" placeholder="Search for...">
                <span class="input-group-btn">
                    <button class="btn btn-info" type="button" id="btnId">百度一下</button>
                </span>
            </div>
        </div>
    </div>
</div>


<hr>
<div class="container-fluid">
    <div class="container-fluid">
        <div id="ff">

        </div>
    </div>
</div>
</body>
</html>