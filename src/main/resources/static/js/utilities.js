var enrshBorderColor= 'rgb(0, 179, 134)';
var enrshBackgroundColor = 'rgb(0, 204, 153, 0.5)';
var enraBorderColor= 'rgb(26, 26, 255)';
var enraBackgroundColor = 'rgb(51, 51, 255, 0.5)';
var eashdBorderColor= 'rgb(204, 204, 0)';
var eashdBackgroundColor = 'rgb(230, 230, 0, 0.5)';
var enmaAndenreBorderColor= 'rgb(179, 0, 89)';
var enmaAndenreBackgroundColor = 'rgb(204, 0, 102, 0.5)';
var enrshDescription = "Ref ID available on ABL, but SHWG has no Ref ID";
var enmaAndenreDescription = "Ref ID available on SHWG, but ABL has no Ref ID";
var eashdDescription = "Different Ref IDs available on ABL and SHWG";
var enraDescription = "No Ref ID available on ABL and SHWG";
var noReportThreshold = 0;
//******************************************************************************************
function setTodayDateISO(id) {
    var today = new Date().toISOString().split("T")[0];
    $(id).val(today);
}
//******************************************************************************************
function getDatePickerDateISO(id) {
    console.log("getDatePickerDateISO got ID: "+id);
    var date = new Date($(id).val()).toISOString().split("T")[0];
    return date;
}
//******************************************************************************************
function setYesterdayDateISO(id) {
    var today = new Date();
    var yesterday = new Date(today.setDate(today.getDate()-1)).toISOString().split("T")[0];
    // yesterday = yesterday.getDate().toISOString().split("T")[0];
    $(id).val(yesterday);
}
//******************************************************************************************
function getDailyStatsFromTo(datePicker1 , datePicker2 , chartID , chartID2){
    var chart , chart2;
    var chartCTX = document.getElementById(chartID).getContext('2d');
    var chartCTX2 = document.getElementById(chartID2).getContext('2d');
    var from = getDatePickerDateISO(datePicker1);
    var to = getDatePickerDateISO(datePicker2);
    var dates = new Array();
    dates[0] = from;
    dates[1] = to;
    $.ajax({
        type: "POST",
        url: "/dashboard/stats/dailyFromTo",
        data: JSON.stringify(dates),
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        dataType: "json",
        success: function (data, status, jqXHR) {
            console.log(status);
            console.log(data);
            var enrsh = new Array(), eashd= new Array(), enmaAndenre= new Array(), enra= new Array();
            var chartLabels = new Array();
            var chartLabels2 = new Array();
            $.each(data , function (k, v) {
                $.each(v , function (k2, v2) {
                    if(k2 ==='count_EDW_No_RE_SHWG')
                        enrsh.push(v2);
                    else if (k2 === 'count_EDW_ABL_SHWG_DIF')
                        eashd.push(v2);
                    else if (k2 === 'count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER')
                        enmaAndenre.push(v2);
                    else if (k2 === 'count_EDW_No_RE_ABL')
                        enra.push(v2);
                    else if (k2 === 'statDate')
                        chartLabels.push(v2)
                });
            });
            console.log("fromTo labels: "+chartLabels);
            console.log("fromTo enrsh: "+enrsh);
            chart = new Chart(chartCTX ,{
                type: "line",
                data: {
                    datasets: [{
                        label: enrshDescription,
                        borderColor: function (context) {
                            if (context.dataset.data[context.dataIndex] === noReportThreshold){
                                return 'rgb(255 , 0 , 0)';
                            } else {
                                return enrshBorderColor;
                            }
                        },
                        backgroundColor: function (context) {
                            if (context.dataset.data[context.dataIndex] === noReportThreshold){
                                return 'rgb(255 , 0 , 0)';
                            } else {
                                return enrshBackgroundColor;
                            }
                        },
                        data: enrsh,
                        yAxisID: 'first-Y',
                        xAxisID: 'first-X',
                        fill: false,
                        pointStyle:'circle'
                    }
                    ,{
                            label: enmaAndenreDescription,
                            borderColor: function (context) {
                                if (context.dataset.data[context.dataIndex] === noReportThreshold){
                                    return 'rgb(255 , 0 , 0)';
                                } else {
                                    return enmaAndenreBorderColor;
                                }
                            },
                            backgroundColor: function (context) {
                                if (context.dataset.data[context.dataIndex] === noReportThreshold){
                                    return 'rgb(255 , 0 , 0)';
                                } else {
                                    return enmaAndenreBackgroundColor;
                                }
                            },
                            data: enmaAndenre,
                            yAxisID: 'first-Y',
                            xAxisID: 'first-X',
                            fill: false,
                            pointStyle: 'triangle'
                        }
                        ,{
                            label: eashdDescription,
                            borderColor: function (context) {
                                if (context.dataset.data[context.dataIndex] === noReportThreshold){
                                    return 'rgb(255 , 0 , 0)';
                                } else {
                                    return eashdBorderColor;
                                }
                            },
                            backgroundColor: function (context) {
                                if (context.dataset.data[context.dataIndex] === noReportThreshold){
                                    return 'rgb(255 , 0 , 0)';
                                } else {
                                    return eashdBackgroundColor;
                                }
                            },
                            data: eashd,
                            yAxisID: 'first-Y',
                            xAxisID: 'first-X',
                            fill: false,
                            pointStyle: 'star'
                        }]
                },
                options:{
                    tooltips:{
                        bodyFontSize: 16
                    },
                    title:{
                        display: true,
                        text: "Weekly/Monthly Statistics",
                        fontSize: 18
                    },
                    elements: {
                        line: {
                            cubicInterpolationMode: 'monotone'
                        }
                    },
                    scales: {
                        yAxes:[{
                            id: 'first-Y',
                            type: 'linear',
                            position: 'left',
                            ticks:{
                                beginAtZero: true
                            }
                        }],
                        xAxes: [{
                            id: 'first-X',
                            labels: chartLabels
                        }]
                    }
                }
            });
            chart2 = new Chart(chartCTX2,{
                type: "line",
                data:{
                    datasets:[{
                        label: enraDescription,
                        borderColor: function (context) {
                            if (context.dataset.data[context.dataIndex] === noReportThreshold){
                                return 'rgb(255 , 0 , 0)';
                            } else {
                                return enraBorderColor;
                            }
                        },
                        backgroundColor: function (context) {
                            if (context.dataset.data[context.dataIndex] === noReportThreshold){
                                return 'rgb(255 , 0 , 0)';
                            } else {
                                return enraBackgroundColor;
                            }
                        },
                        data: enra,
                        yAxisID: 'first-Y',
                        xAxisID: 'first-X',
                        fill: true,
                        spanGaps: false,
                        pointStyle: 'rect'
                    }
                ]},
                options:{
                    tooltips:{
                        bodyFontSize: 16
                    },
                    title:{
                        display: true,
                        text: "Weekly/Monthly Statistics: "+enraDescription,
                        fontSize: 18
                    },
                    elements: {
                        line: {
                            cubicInterpolationMode: 'monotone'
                        }
                    },
                    scales: {
                        yAxes:[{
                            id: 'first-Y',
                            type: 'linear',
                            position: 'right',
                            ticks:{
                                beginAtZero: true
                            }
                        }],
                        xAxes: [{
                            id: 'first-X',
                            labels: chartLabels
                        }]
                    }
                }
            });
            $(datePicker1).change(function () {
                dates[0] = getDatePickerDateISO(datePicker1);
                $.ajax({
                    type: "POST",
                    url: "/dashboard/stats/dailyFromTo",
                    data: JSON.stringify(dates),
                    contentType: "application/json; charset=utf-8",
                    crossDomain: true,
                    dataType: "json",

                    success: function (data1, status, jqXHR) {
                        // console.log(status);
                        // console.log(data1);
                        enrsh = new Array(), eashd= new Array(), enmaAndenre= new Array(), enra= new Array();
                        chartLabels = new Array();
                        $.each(data1 , function (k, v) {
                            $.each(v , function (k2, v2) {
                                if(k2 ==='count_EDW_No_RE_SHWG')
                                    enrsh.push(v2);
                                else if (k2 === 'count_EDW_ABL_SHWG_DIF')
                                    eashd.push(v2);
                                else if (k2 === 'count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER')
                                    enmaAndenre.push(v2);
                                else if (k2 === 'count_EDW_No_RE_ABL')
                                    enra.push(v2);
                                else if (k2 === 'statDate')
                                    chartLabels.push(v2)
                            });
                        });

                        chart.data.datasets[0].data = enrsh;
                        chart.data.datasets[1].data = enmaAndenre;
                        chart.data.datasets[2].data = eashd;
                        chart2.data.datasets[0].data = enra;
                        chart.options.scales.xAxes[0].labels = chartLabels;
                        chart2.options.scales.xAxes[0].labels = chartLabels;
                        chart.update();
                        chart2.update();
                    }
                });
            });
            $(datePicker2).change(function () {
                dates[1] = getDatePickerDateISO(datePicker2);
                $.ajax({
                    type: "POST",
                    url: "/dashboard/stats/dailyFromTo",
                    data: JSON.stringify(dates),
                    contentType: "application/json; charset=utf-8",
                    crossDomain: true,
                    dataType: "json",

                    success: function (data1, status, jqXHR) {
                        console.log(status);
                        console.log(data1);
                        enrsh = new Array(), eashd= new Array(), enmaAndenre= new Array(), enra= new Array();
                        chartLabels = new Array();
                        $.each(data1 , function (k, v) {
                            $.each(v , function (k2, v2) {
                                if(k2 ==='count_EDW_No_RE_SHWG')
                                    enrsh.push(v2);
                                else if (k2 === 'count_EDW_ABL_SHWG_DIF')
                                    eashd.push(v2);
                                else if (k2 === 'count_EDW_NO_MSISDN_ABLT_and_EDW_No_RE_ER')
                                    enmaAndenre.push(v2);
                                else if (k2 === 'count_EDW_No_RE_ABL')
                                    enra.push(v2);
                                else if (k2 === 'statDate')
                                    chartLabels.push(v2)
                            });
                        });

                        chart.data.datasets[0].data = enrsh;
                        chart.data.datasets[1].data = enmaAndenre;
                        chart.data.datasets[2].data = eashd;
                        chart2.data.datasets[0].data = enra;
                        chart.options.scales.xAxes[0].labels = chartLabels;
                        chart2.options.scales.xAxes[0].labels = chartLabels;
                        chart.update();
                        chart2.update();
                    }
                });
            });

        }
    });
}
//******************************************************************************************
function getDailyStatsAndDraw(datePickerID , chartID , chartID2) {
    var chart , chart2;
    var chartJsCTX = document.getElementById(chartID).getContext('2d');
    var chartJsCTX2 = document.getElementById(chartID2).getContext('2d');
    var statDate = getDatePickerDateISO(datePickerID);
    $.ajax({
        type: "POST",
        url: "/dashboard/stats/daily",
        data: JSON.stringify(statDate),
        contentType: "application/json; charset=utf-8",
        crossDomain: true,
        dataType: "json",
        success: function (data, status, jqXHR) {
            var stats = data;
            var lables = [];
            $.each(stats , function (k , v) {
                if (k !== "statDate" )
                    lables.push(k.toString());
            });
            var chartData = [];
            var chartData2 = [];

            $.each(stats , function (k , v) {
                if (k !== "statDate" && k !=='count_EDW_No_RE_ABL') {
                    chartData.push(v);
                }
                else if(k ==='count_EDW_No_RE_ABL'){
                    chartData2.push(v);
                }
            });

                console.log("First Draw of Graph\nLabels: "+lables+"\nData: "+chartData);
                console.log("First Draw of Graph\nLabels: "+lables+"\nData2: "+chartData2);

                chart = new Chart(chartJsCTX , {
                    type: 'bar',
                    data: {
                        datasets: [{
                            label: "Ref ID available on ABL, but SHWG has no Ref ID",
                            backgroundColor:['rgb(0, 204, 153, 0.5)','rgb(0, 204, 153, 0.5)','rgb(0, 204, 153, 0.5)','rgb(0, 204, 153, 0.5)'],
                            borderColor:['rgb(0, 179, 134)','rgb(0, 179, 134)','rgb(0, 179, 134)','rgb(0, 179, 134)'],
                            borderWidth:2,
                            data: [chartData[0],0,0,0],
                            yAxisID: 'first-y-axis',
                            maxBarThickness: 100
                        },{
                            label: "Different Ref IDs available on ABL and SHWG",
                            backgroundColor:["rgb(230, 230, 0, 0.5)","rgb(230, 230, 0, 0.5)","rgb(230, 230, 0, 0.5)","rgb(230, 230, 0, 0.5)"],
                            borderColor:['rgb(204, 204, 0)','rgb(204, 204, 0)','rgb(204, 204, 0)','rgb(204, 204, 0)'],
                            borderWidth:2,
                            data: [0,chartData[1],0,0],
                            yAxisID: 'first-y-axis',
                            maxBarThickness: 100
                        },{
                            label: "Ref ID available on SHWG, but ABL has no Ref ID",
                            backgroundColor:["rgb(204, 0, 102, 0.5)","rgb(204, 0, 102, 0.5)","rgb(204, 0, 102, 0.5)","rgb(204, 0, 102, 0.5)"],
                            borderColor:['rgb(179, 0, 89)','rgb(179, 0, 89)','rgb(179, 0, 89)','rgb(179, 0, 89)'],
                            borderWidth:2,
                            data: [0,0,chartData[2],0],
                            yAxisID: 'first-y-axis',
                            maxBarThickness: 100
                        }]
                    },
                    options: {
                        tooltips:{
                            bodyFontSize: 16
                        },
                        title:{
                            display: true,
                            text: "Daily Statistics",
                            fontSize: 18
                        },
                        scales: {
                            yAxes: [{
                                id: 'first-y-axis',
                                type: 'linear',
                                ticks:{
                                    beginAtZero: true,
                                    fontSize: 16
                                }
                            }
                            ],
                            xAxes:[
                                {
                                    id: 'first-x-axis',
                                    type: 'category',
                                    labels: ["A","B","C"]
                                }
                            ]
                        }
                    }
                });
                chart2 = new Chart(chartJsCTX2 , {
                    type: 'bar',
                    data: {
                        datasets: [{
                            label: "No Ref ID available on ABL and SHWG",
                            backgroundColor:["rgb(51, 51, 255, 0.5)"],
                            borderColor:['rgb(26, 26, 255)'],
                            borderWidth:2,
                            data: chartData2,
                            yAxisID: 'first-y-axis',
                            maxBarThickness: 100
                        }]
                    },
                    options: {
                        tooltips:{
                            bodyFontSize: 16
                        },
                        title:{
                            display: true,
                            text: "Daily Statistics: No Ref ID available on Ability and Shahkar Gateway",
                            fontSize: 18
                        },
                        scales: {
                            yAxes: [{
                                id: 'first-y-axis',
                                type: 'linear',
                                position: 'right',
                                ticks:{
                                    beginAtZero: true,
                                    fontSize: 16
                                }
                            }],
                            xAxes:[
                                {
                                    id: 'first-x-axis',
                                    type: 'category',
                                    labels: ["D"]
                                }]
                        }
                    }
                });

            $(datePickerID).change(function () {
                $.ajax({
                    type: "POST",
                    url: "/dashboard/stats/daily",
                    data: JSON.stringify(getDatePickerDateISO(datePickerID)),
                    contentType: "application/json; charset=utf-8",
                    crossDomain: true,
                    dataType: "json",
                    success: function (data, status, jqXHR) {
                       stats = data;
                       lables = [];
                        $.each(stats , function (k , v) {
                            if (k !== "statDate" )
                                lables.push(k.toString());
                        });
                        chartData = [];
                        chartData2 = [];
                        $.each(stats , function (k , v) {
                            if (k !== "statDate" && k !=='count_EDW_No_RE_ABL') {
                                chartData.push(v);
                            }
                            else if(k ==='count_EDW_No_RE_ABL'){
                                chartData2.push(v);
                            }
                        });
                        console.log("Updating the daily stats graph\nLabels: "+lables+"\nData: "+chartData);
                        console.log("Updating the daily stats graph\nLabels: "+lables+"\nData2: "+chartData2);
                        chart.data.datasets[0].data = [chartData[0],0,0,0];
                        chart.data.datasets[1].data = [0,chartData[1],0,0];
                        chart.data.datasets[2].data = [0,0,chartData[2],0];
                        chart2.data.datasets[0].data = chartData2;
                        chart.update();
                        chart2.update();
                    },
                    error: function (jqXHR, status) {
                        console.log(status);
                        alert('failed to load Daily Statistics: ' + status.code);
                    }
                });
            });
        },
        error: function (jqXHR, status) {
            console.log(status);
            alert('failed to load Daily Statistics: ' + status.code);
        }
    });
}
//******************************************************************************************
