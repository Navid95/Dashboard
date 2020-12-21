var enrshBorderColor= 'rgb(0, 179, 134)';
var enrshBackgroundColor = 'rgb(0, 204, 153, 0.5)';
var enraBorderColor= 'rgb(26, 26, 255)';
var enraBackgroundColor = 'rgb(51, 51, 255, 0.5)';
var eashdBorderColor= 'rgb(204, 204, 0)';
var eashdBackgroundColor = 'rgb(230, 230, 0, 0.5)';
var enmaAndenreBorderColor= 'rgb(179, 0, 89)';
var enmaAndenreBackgroundColor = 'rgb(204, 0, 102, 0.5)';
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
function getDailyStatsFromTo(datePicker1 , datePicker2 , chartID){
    var chart;
    var chartCTX = document.getElementById(chartID).getContext('2d');
    var from = getDatePickerDateISO(datePicker1);
    var to = getDatePickerDateISO(datePicker2);
    var dates = new Array();
    // dates[0] = from;
    // dates[1] = to;
    dates[0] = "2020-12-04";
    dates[1] = "2020-12-12";
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
                        label: 'enrsh',
                        borderColor: enrshBorderColor,
                        backgroundColor: enrshBackgroundColor,
                        data: enrsh,
                        yAxisID: 'first-Y',
                        xAxisID: 'first-X',
                        fill: false
                    }
                    ,{
                            label: 'enmaAndenre',
                            borderColor: enmaAndenreBorderColor,
                            backgroundColor: enmaAndenreBackgroundColor,
                            data: enmaAndenre,
                            yAxisID: 'first-Y',
                            xAxisID: 'first-X',
                            fill: false
                        }
                        ,{
                            label: 'eashd',
                            borderColor: eashdBorderColor,
                            backgroundColor: eashdBackgroundColor,
                            data: eashd,
                            yAxisID: 'first-Y',
                            xAxisID: 'first-X',
                            fill: false
                        }
                    ,{
                            label: 'enra',
                            borderColor: enraBorderColor,
                            backgroundColor: enraBackgroundColor,
                            data: enra,
                            yAxisID: 'second-Y',
                            xAxisID: 'first-X',
                            fill: false
                    }]
                },
                options:{
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
                        },
                            {
                                id: 'second-Y',
                                type: 'linear',
                                position: 'right',
                                ticks:{
                                    beginAtZero: true,
                                    stepSize: 10000
                                    // fontSize: 16
                                },
                                gridLines:{
                                    drawOnChartArea: false
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
                        chart.data.datasets[3].data = enra;
                        chart.options.scales.xAxes[0].labels = chartLabels;
                        chart.update();
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
                        chart.data.datasets[3].data = enra;
                        chart.options.scales.xAxes[0].labels = chartLabels;
                        chart.update();
                    }
                });
            });

        }
    });
}
//******************************************************************************************
function getDailyStatsAndDraw(datePickerID , chartID ) {
    var chart;
    var chartJsCTX = document.getElementById(chartID).getContext('2d');
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
            var label2 =[];
            $.each(stats , function (k , v) {
                // if (k !== "statDate" && k !=='count_EDW_No_RE_ABL')
                if (k !== "statDate" )
                    lables.push(k.toString());
                    // else if(k ==='count_EDW_No_RE_ABL')
                    //     label2.push(k.toString());
            });

            var chartData = [];
            var chartData2 = [];

            $.each(stats , function (k , v) {
                if (k !== "statDate" && k !=='count_EDW_No_RE_ABL') {
                    // if (k !== "statDate" )
                    chartData2.push(0);
                    chartData.push(v);
                }
                else if(k ==='count_EDW_No_RE_ABL'){
                    chartData2.push(v);
                }
            });

                console.log("First Draw of Graph\nLabels: "+lables+"\nData: "+chartData);

                chart = new Chart(chartJsCTX , {
                    type: 'bar',
                    data: {
                        // labels : lables,
                        datasets: [{
                            label: "Ref ID available on ABL, but SHWG has no Ref ID",
                            backgroundColor:['rgb(0, 204, 153, 0.5)','rgb(0, 204, 153, 0.5)','rgb(0, 204, 153, 0.5)','rgb(0, 204, 153, 0.5)'],
                            borderColor:['rgb(0, 179, 134)','rgb(0, 179, 134)','rgb(0, 179, 134)','rgb(0, 179, 134)'],
                            borderWidth:2,
                            // fill:false,
                            data: [chartData[0],0,0,0],
                            yAxisID: 'first-y-axis',
                            maxBarThickness: 120
                            // xAxisID: 'first-x-axis'
                        },{
                            label: "Different Ref IDs available on ABL and SHWG",
                            backgroundColor:["rgb(230, 230, 0, 0.5)","rgb(230, 230, 0, 0.5)","rgb(230, 230, 0, 0.5)","rgb(230, 230, 0, 0.5)"],
                            borderColor:['rgb(204, 204, 0)','rgb(204, 204, 0)','rgb(204, 204, 0)','rgb(204, 204, 0)'],
                            borderWidth:2,
                            data: [0,chartData[1],0,0],
                            yAxisID: 'first-y-axis',
                            maxBarThickness: 120
                        },{
                            label: "Ref ID available on SHWG, but ABL has no Ref ID",
                            backgroundColor:["rgb(204, 0, 102, 0.5)","rgb(204, 0, 102, 0.5)","rgb(204, 0, 102, 0.5)","rgb(204, 0, 102, 0.5)"],
                            borderColor:['rgb(179, 0, 89)','rgb(179, 0, 89)','rgb(179, 0, 89)','rgb(179, 0, 89)'],
                            borderWidth:2,
                            data: [0,0,chartData[2],0],
                            yAxisID: 'first-y-axis',
                            maxBarThickness: 120
                        }
                        ,{
                            label: "No Ref ID available on ABL and SHWG",
                            backgroundColor:["rgb(51, 51, 255, 0.5)","rgb(51, 51, 255, 0.5)","rgb(51, 51, 255, 0.5)","rgb(51, 51, 255, 0.5)"],
                            borderColor:['rgb(26, 26, 255)','rgb(26, 26, 255)','rgb(26, 26, 255)','rgb(26, 26, 255)'],
                            borderWidth:2,
                            data: chartData2,
                            yAxisID: 'second-y-axis',
                            maxBarThickness: 120
                            }
                        ]
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
                            ,{
                                id: 'second-y-axis',
                                type: 'linear',
                                position: 'right',
                                    ticks:{
                                        beginAtZero: true,
                                        stepSize: 10000,
                                        fontSize: 16
                                    },
                                    gridLines:{
                                        drawOnChartArea: false
                                    }
                            }
                            ],
                            xAxes:[
                                {
                                    id: 'first-x-axis',
                                    type: 'category',
                                    labels: ["A","B","C","D"]

                                }
                                // ,{
                                //     id: 'second-x-axis',
                                //     type: 'category',
                                //     position: 'top',
                                //     labels: label2
                                // }
                            ]
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
                       label2 = [];
                        $.each(stats , function (k , v) {
                            // if (k !== "statDate" && k !=='count_EDW_No_RE_ABL')
                            if (k !== "statDate" )
                                lables.push(k.toString());
                            // else if(k ==='count_EDW_No_RE_ABL')
                            //     label2.push(k.toString());
                        });

                        chartData = [];
                        chartData2 = [];
                        $.each(stats , function (k , v) {
                            if (k !== "statDate" && k !=='count_EDW_No_RE_ABL') {
                                // if (k !== "statDate" )
                                chartData2.push(0);
                                chartData.push(v);
                            }
                            else if(k ==='count_EDW_No_RE_ABL'){
                                chartData2.push(v);
                            }
                        });

                        console.log("Updating the daily stats graph\nLabels: "+lables+"\nData: "+chartData);

                        // chart.data.labels = lables;
                        chart.data.datasets[0].data = [chartData[0],0,0,0];
                        chart.data.datasets[1].data = [0,chartData[1],0,0];
                        chart.data.datasets[2].data = [0,0,chartData[2],0];
                        chart.data.datasets[3].data = chartData2;
                        chart.update();
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
