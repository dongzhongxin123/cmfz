<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>


<link rel="stylesheet" href="statics/boot/css/bootstrap.css">
<link rel="stylesheet" href="statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">

<%--<script src="./boot/js/jquery-2.2.1.min.js"></script>--%>
<script src="./boot/js/jquery-1.10.1.min.js"></script>
<script src="./boot/js/bootstrap.min.js"></script>
<script src="statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
<script src="statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
<script src="./boot/js/ajaxfileupload.js"></script>
<script src="./kindeditor/kindeditor-all-min.js"></script>
<script src="./kindeditor/lang/zh-CN.js"></script>

<script>

    $(function () {
        //懒加载
        $("#dd1").jqGrid({
            url: "${pageContext.request.contextPath}/article/queryByPage",
            editurl: "${pageContext.request.contextPath}/article/edit",
            styleUI: 'Bootstrap',  //设置bootstrap的样式
            datatype: "json",  //以json形式返回
            mtype: "post",     //请求方式
            pager: "#page1",    //显示分页
            viewrecords: true, //显示总条数
            rowList: [2, 4, 6, 8],  //设置每页的条数
            colNames: ["标题", "状态", "发布时间", "操作",],
            autowidth: true,   //宽度自适应
            multiselect: true,
            rownumbers: true,
            colModel: [
                {
                    name: "title",
                    editable: true,
                },
                {
                    name: "status",
                    editable: true,
                },
                {
                    name: "publishTime", editable: true, "formatter": "date"
                },
                {
                    formatter: function (cellvalue, options, rowobject) {
                        return "<a href='#' onclick=\"lookModel('" + rowobject.id + "')\" class=\"glyphicon glyphicon-th-list\"></a>"
                    }
                }
            ]
        }).jqGrid("navGrid", "#page1",{
            add:false
        })

    })

    //点击后出现模态框，供添加使用
    function showModal() {
        //通过模态框、Ajax发出请求

        //显示模态框
        $("#myModal").modal("show");
        //初始化参数
        $("#modal_footer").html(
            "<button type=\"button\" onclick='addArticle()' class=\"btn btn-primary\">添加</button>\n" +
            "<button type=\"button\"   class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>"
        );
        //初始化kindeditor参数
        KindEditor.create("#editor", {
            //支持上传图片
            uploadJson: "${pageContext.request.contextPath}/kindeditor/upload",
            //制定上传文件的form表单
            filePostName: "img",
            //获取服务器文件夹的路径
            fileManagerJson: "${pageContext.request.contextPath}/kindeditor/findAllImg",
            allowFileManager: true,
        });
        $("#addArticleFrom")[0].reset();
    }

    //添加文章
    function addArticle() {
        $.ajax({
            url: "${pageContext.request.contextPath}/article/add",
            type: "post",
            data: $("#addArticleFrom").serialize(),
            success: function (result) {
                $("#dd1").trigger("reloadGrid");
                $("#myModal").modal("toggle");
            }
        })
    }


    //查看详情
    function lookModel(id) {
        $("#addArticleFrom")[0].reset();
        $("#myModal").modal("show");
        $.ajax({
            url:'${pageContext.request.contextPath}/article/queryById?id='+id,
            type:'get',
            dataType:'json',
            success:function (data) {
                $("#title").val(data.title)
                $("#author").val(data.author)
                $("#status").val(data.status)
                $("#publishTime").val(data.publishTime)
                $("#content").val(data.content)
            }
        });


        KindEditor.create('#editor',{
            uploadJson:"${pageContext.request.contextPath}/kindeditor/upload",
            filePostName:"img",
            fileManagerJson:"${pageContext.request.contextPath}/kindeditor/getAllImg",
            allowFileManager:true,
            afterBlur:function () {
                this.sync();
            }
        });
        KindEditor.html("#editor","");
        KindEditor.appendHtml("#editor",value.content);


        $("#modal_footer").html(
            "<button type=\"button\" onclick=\"updateArticle('"+id+"')\" class=\"btn btn-primary\">修改</button>\n" +
            "<button type=\"button\" class=\"btn btn-danger\" data-dismiss=\"modal\">取消</button>"
        );

    }

    $("#basic-addon2").click(function () {

        var valu=$("#esValue").val()
        console.log(valu)
        $('#changeContent').load('es.jsp?esVal='+valu)
    })



</script>
</head>
<!--标签页-->
<div class="container-fluid">
    <div class="row col-md-12">

        <ul id="myTab" class="nav nav-tabs">
            <li class="active">
                <a href="#articleList" data-toggle="tab">
                    文章信息
                </a>
            </li>
            <li><a href="#addArticle" onclick="showModal()" data-toggle="tab">添加文章</a></li>
            <li>
                <div class="input-group" style="width: 300px;float: right">
                    <input  type="text"  id="esValue" class="form-control" placeholder="请输入您想查找的内容" aria-describedby="basic-addon2">
                    <span class="input-group-addon" id="basic-addon2">搜索</span>
                </div>
            </li>
        </ul>

        <div id="myTabContent" class="tab-content">
            <div class="tab-pane fade in active" id="articleList">
                <!--加载显示查询到的结果-->
                <table id="dd1"></table>
                <div id="page1" style="height: 55px"></div>
            </div>
        </div>
    </div>
</div>



<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1">
    <div class="modal-dialog">
        <div class="modal-content" style="width:750px">
            <!--模态框标题-->
            <div class="modal-header">
                <!--
                    用来关闭模态框的属性:data-dismiss="modal"
                -->
                <button type="button" class="close" data-dismiss="modal"><span>&times;</span></button>
                <h4 class="modal-title">编辑用户信息</h4>
            </div>

            <!--模态框内容体-->
            <div class="modal-body">
                <form action="${pageContext.request.contextPath}/article/editArticle" class="form-horizontal"
                      id="addArticleFrom">
                    <div class="form-group">
                        <label class="col-sm-1 control-label">标题</label>
                        <div class="col-sm-5">
                            <input type="text" name="title" id="title" placeholder="请输入标题" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">作者</label>
                        <div class="col-sm-5">
                            <input type="text" name="author" id="author" placeholder="请输入作者" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">状态</label>
                        <div class="col-sm-5">
                            <select class="form-control" name="status" id="status">
                                <option value="y">展示</option>
                                <option value="n">不展示</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-1 control-label">发布时间</label>
                        <div class="col-sm-5">
                            <input type="date" name="publishTime" id="publishTime" class="form-control">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-12">
                            <textarea id="content" name="content" style="width:700px;height:300px;"></textarea>
                        </div>
                    </div>
                    <input id="addInsertImg" name="insertImg" hidden>
                </form>
            </div>
            <!--模态页脚-->
            <div class="modal-footer" id="modal_footer">
            </div>
        </div>
    </div>
</div>