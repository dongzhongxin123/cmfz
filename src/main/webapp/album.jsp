<%@page contentType="text/html; UTF-8" language="java" isELIgnored="false" pageEncoding="UTF-8" %>
<link rel="stylesheet" href="statics/boot/css/bootstrap.css">
<link rel="stylesheet" href="statics/jqgrid/css/trirand/ui.jqgrid-bootstrap.css">

<script src="./boot/js/jquery-2.2.1.min.js"></script>
<script src="statics/boot/js/bootstrap.min.js"></script>
<script src="statics/jqgrid/js/trirand/i18n/grid.locale-cn.js"></script>
<script src="statics/jqgrid/js/trirand/jquery.jqGrid.min.js"></script>
<script src="./boot/js/ajaxfileupload.js"></script>
<style>
    .ui-th-column ui-th-ltr {
        width: 60px;
    }
</style>
<script>
    //懒加载函数
    $("#tb").jqGrid({
        url: "${pageContext.request.contextPath}/album/queryByPage",
        editurl: "${pageContext.request.contextPath}/album/edit",
        datatype: "json",
        mtype: "post",
        styleUI: 'Bootstrap',
        pager: "#page",    //显示分页
        viewrecords: true, //显示总条数
        rowList: [2, 4, 6],  //设置每页的条数
        colNames: ["标题", "分数", "作者", "播音员", "章节数", "专辑简介", "状态", "发行时间", "上传时间", "插图"],
        autowidth: true,   //宽度自适应
        height: "60%",
        multiselect: true,
        rownumbers: true,
        colModel: [
            {name: "title", editable: true,},
            {name: "score", editable: true,},
            {name: "author", editable: true,},
            {name: "announcer", editable: true,},
            {name: "chapterNumber", editable: true,},
            {name: "albumIntroduction", editable: true,},
            {name: "status", editable: true,},
            {name: "publishTime", editable: true, edittype: "date"},
            {name: "uploadTime", editable: true, edittype: "date"},
            {
                name: "illustration", editable: true, edittype: "file",
                formatter: function (cellvalue, options, rowObject) {
                    return "<img style='width:100px;height:70px' src='${pageContext.request.contextPath}/img/" + cellvalue + "'/>"
                }
            }
        ],
        subGrid: true,
        caption: "Grid as Subgrid",
        subGrid: true,
        subGridRowExpanded: function (subGridId, albumId) {
            var tableId = subGridId + "table";
            var pagerId = subGridId + "pager";
            $("#" + subGridId).html(
                "<table id=" + tableId + "></table>\n" +
                "<div id=" + pagerId + "></div>"
            );
            $("#" + tableId).jqGrid({
                url: "${pageContext.request.contextPath}/chapter/queryByPage?id=" + albumId,
                datatype: "json",
                editurl: "${pageContext.request.contextPath}/chapter/edit?aId=" + albumId,
                // mtype:"post",
                // data:{id:albumId},
                colNames: ["大小", "时长", "上传时间", "音频", "操作"],
                colModel: [
                    {name: "size"},
                    {name: "timeLength"},
                    {name: "publishTime", editable: true, edittype: "date"},
                    {name: "audioFile", editable: true, edittype: 'file'},
                    {
                        width: 90,
                        formatter: function (cellvalue, options, rowObject) {
                            return "<a><span onclick=\"playAudio('" + rowObject.audioFile + "')\" class='glyphicon glyphicon-play'></span></a>  " + "  " +
                                "<a><span onclick=\"download('" + rowObject.audioFile + "')\" class='glyphicon glyphicon-download-alt'></span></a>";
                        }
                    }
                ],
                styleUI: "Bootstrap",
                autowidth: true,
                pager: "#" + pagerId,
                rowNum: 2,
                page: 1,
                rowList: [2, 4, 8],
                multiselect: true,
                viewrecords: true,
                height: "60%"
            }).jqGrid("navGrid", "#" + pagerId,

                {},
                {},
                {
                    closeAfterAdd: true,
                    afterSubmit: function (response) {
                        var chapterId = response.responseText;
                        $.ajaxFileUpload({
                            url: "${pageContext.request.contextPath}/chapter/upload",
                            fileElementId: "audioFile",
                            data: {id: chapterId},
                            success: function (data) {
                                $("#" + tableId).trigger("reloadGrid");
                            }
                        });

                        return response;
                    }
                },
                {}
            )
        }


    }).jqGrid("navGrid", "#page",
        { //处理前台页面按钮组的样式以及展示后者不展示。
            search: false
        },
        {//控制编辑按钮，在编辑之前或者之后要进行的额外操作
            beforeShowForm: function (obj) {
                obj.find("#illustration").attr("disabled", true)
            }
        },
        {//控制添加按钮，在添加之前或者之后要进行的额外操作
            closeAfterAdd: true,
            afterSubmit: function (response) {
                var rid = response.responseText;
                console.log(rid)
                $.ajaxFileUpload({
                    url: "${pageContext.request.contextPath}/album/upload",
                    fileElementId: "illustration",
                    data: {bId: rid},
                    success: function (data) {
                        $("#tb").trigger("reloadGrid");
                    }
                });
                return "ok"
            }
        },
        {//控制删除按钮，在删除之前或者之后要进行的额外操作
            beforeShowForm: function () {

            }
        })

    function download(a) {
        location.href = "${pageContext.request.contextPath}/chapter/down?audioFile=" + a;
    }

    function playAudio(b) {
        $("#audioModel").modal("show");
        $("#audioId").attr("src", "${pageContext.request.contextPath}/audio/" + b);
    }
</script>
<div id="audioModel" class="modal fade" tabindex="-1" role="dialog">
    <div class="modal-dialog" role="document">
        <audio id="audioId" src="" controls></audio>
    </div><!-- /.modal-dialog -->
</div>
<!-- /.modal -->
<table id="tb"></table>
<div id="page" style="height: 50px"></div>