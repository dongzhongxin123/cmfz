<%@page contentType="text/html; UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
    <title>搜索结果</title>
    <script>
        $(function () {
            var val='${param.esVal}'
            $.ajax({
                url:'${pageContext.request.contextPath}/article/queryByEs',
                dataType:'json',
                type:'post',
                data:{value:val},
                success:function (data) {

                    console.log(data)
                       $.each(data,function (index,article) {
                           $("#esDiv").append(
                               "<div class=\"media\">\n" +
                               "  <div class=\"media-left\">\n" +
                               "  <div class=\"media-body\">\n" +
                               "    <h4 class=\"media-heading\">"+article.title+"</h4>\n" +
                               article.author+"<br/>"+
                               "    \n" +
                               article.content+
                               "  </div>\n" +
                               "  </div>\n" +
                               "</div>"
                           )
                       })

                }
            })
        })
    </script>
</head>

<div id="esDiv" style="float: left"></div>
