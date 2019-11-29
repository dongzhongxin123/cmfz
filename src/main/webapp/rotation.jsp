<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="./boot/css/bootstrap.css">
<link rel="stylesheet" href="./jqgrid/css/trirand/ui.jqgrid-bootstrap.css">

<script src="./boot/js/jquery-2.2.1.min.js"></script>
<script src="./boot/js/bootstrap.min.js"></script>
<script src="./jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
<script src="./jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
<script src="./boot/js/ajaxfileupload.js"></script>
-
<script>
    $(function () {
        $("#tb").jqGrid({
            url: "${pageContext.request.contextPath}/rotation/queryByPage",
            editurl: "${pageContext.request.contextPath}/rotation/edit",
            styleUI: 'Bootstrap',  //设置bootstrap的样式
            datatype: "json",  //以json形式返回
            mtype: "post",     //请求方式
            pager: "#page",    //显示分页
            viewrecords: true, //显示总条数
            rowList: [2, 4, 6],  //设置每页的条数
            colNames: ["编号", "标题", "状态", "描述", "创建时间", "图片"],
            autowidth: true,   //宽度自适应
            height: "60%",
            multiselect: true,
            rownumbers: true,
            colModel: [
                {
                    name: "id",
                    //editable:true,
                },
                {
                    name: "title",
                    editable: true,
                },
                {
                    name: "status",
                    editable: true,
                },
                {
                    name: "describel",
                    editable: true,
                },
                {
                    name: "createTime",
                    editable: true,

                },
                {
                    name: "photo",
                    editable: true,
                    edittype: "file",
                    formatter: function (cellvalue, options, rowObject) {
                        return "<img style='width:100px;height:70px' src='${pageContext.request.contextPath}/img/" + cellvalue + "'/>"
                    }

                }

            ],
            toolbar: [true, "top"],

        }).jqGrid("navGrid", "#page",
            { //处理前台页面按钮组的样式以及展示后者不展示。
                search: false
            },
            {//控制编辑按钮，在编辑之前或者之后要进行的额外操作
                beforeShowForm: function (obj) {
                    obj.find("#photo").attr("disabled", true)
                }
            },
            {//控制添加按钮，在添加之前或者之后要进行的额外操作
                closeAfterAdd: true,
                afterSubmit: function (response) {
                    var rid = response.responseText;
                    console.log(rid)
                    $.ajaxFileUpload({
                        url: "${pageContext.request.contextPath}/rotation/upload",
                        fileElementId: "photo",
                        data: {bId: rid},
                        success: function (data) {
                            console.log(data)
                        }
                    });
                    return "ok"
                }
            },
            {//控制删除按钮，在删除之前或者之后要进行的额外操作
                beforeShowForm: function () {

                }
            })
        $("#t_tb").append("<a href=\"javascript:ex()\" class=\"btn btn-default btn-info\"><span class=\"glyphicon glyphicon-plus\"></span> 添加</a>\n")
    });


    function ex() {
        location.href = "${pageContext.request.contextPath}/rotation/downFile";
    }


</script>
</head>
<table id="tb"></table>
<div id="page" style="height: 50px; width: 420px"></div>
