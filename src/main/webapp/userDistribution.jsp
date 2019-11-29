<%@page pageEncoding="UTF-8" isELIgnored="false" language="java" contentType="text/html; UTF-8" %>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="main" style="width: 600px;height:400px;"></div>
<script src="boot/js/jquery-2.2.1.min.js"></script>
<script src="echarts/echarts.min.js"></script>
<script src="echarts/china.js"></script>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('main'));
    var option = {
        title: {
            text: '用户注册情况统计',
            textStyle: {
                color: 'red'
            }
        },
        tooltip: {},
        legend: {
            data: ['男', '女']
        },
        xAxis: {
            data: ["近一周", "近两周", "近三周"]
        },
        yAxis: {},
        series: [{
            name: '男',
            type: 'bar'
        }, {
            name: '女',
            type: 'bar'
        }]
    };

    $.ajax({
        url: '${pageContext.request.contextPath}/user/queryByTime',
        type: 'post',
        dataType: 'json',
        success: function (result) {
            myChart.setOption({
                series: [{
                    data: [result.nan1, result.nan2, result.nan3]
                }, {
                    data: [result.nv1, result.nv2, result.nv3]
                }],
            })
        }
    })

    myChart.setOption(option)//初始化参数

    var goEasy = new GoEasy({
        host: 'hangzhou.goeasy.io', //应用所在的区域地址: 【hangzhou.goeasy.io |singapore.goeasy.io】
        appkey: "BC-b1b54ac7bca244428c0bead5c7f1de23", //替换为您的应用appkey
    });

    //接收消息
    goEasy.subscribe({
        channel: "ASD",
        onMessage: function (data) {
            $.ajax({
                url: '${pageContext.request.contextPath}/user/queryByTime',
                type: 'post',
                dataType: 'json',
                success: function (result) {
                    myChart.setOption({
                        title: {
                            text: data.content
                        },
                        series: [{
                            data: [result.nan1, result.nan2, result.nan3]
                        }, {
                            data: [result.nv1, result.nv2, result.nv3]
                        }]
                    })
                }
            })

        }
    });
</script>
<body>
<div id="main" style="width: 600px;height:400px;"></div>
</body>