<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>

<!-- 为ECharts准备一个具备大小（宽高）的Dom -->
<div id="china" style="width: 600px;height: 600px;margin-top: 30px;margin-left: 30px">

</div>
<script type="text/javascript">
    // 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('china'));


    var option = {
        title: {
            text: '人口分布',
            subtext: '2017年6月15日 最新数据',
            left: 'center'
        },
        tooltip: {},
        // 说明
        legend: {
            orient: 'vertical',
            left: 'left',
            data: ['男', '女']
        },
        visualMap: {
            min: 0,
            max: 30000,
            left: 'left',
            top: 'bottom',
            text: ['高', '低'],           // 文本，默认为数值文本
            calculable: true
        },
        // 工具箱
        toolbox: {
            show: true,
            orient: 'vertical',
            left: 'right',
            top: 'center',
            feature: {
                dataView: {readOnly: false},
                restore: {},
                saveAsImage: {}
            }
        },
        series: [
            {
                name: '男',
                type: 'map',
                mapType: 'china',
                roam: false,
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                }
            },
            {
                name: '女',
                type: 'map',
                mapType: 'china',
                label: {
                    normal: {
                        show: true
                    },
                    emphasis: {
                        show: true
                    }
                }
            }
        ]
    };

    myChart.setOption(option);

    $.ajax({
        url: '${pageContext.request.contextPath}/user/queryByLocation',
        type: 'post',
        dataType: 'json',
        success: function (message) {
            myChart.setOption({
                series: [
                    {
                        data: message.nan
                    },
                    {
                        data: message.nv
                    }
                ]
            })
        }
    })

</script>












