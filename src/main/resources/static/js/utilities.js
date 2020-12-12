
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

//******************************************************************************************

//******************************************************************************************

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
                            label: "Daily Statistics",
                            backgroundColor:["red","yellow","green","purple","cyan"],
                            data: chartData,
                            // order: 4,
                            // minBarLength: 5,
                            yAxisID: 'first-y-axis',
                            maxBarThickness: 50
                            // xAxisID: 'first-x-axis'
                        }
                        ,{
                            label: "count_EDW_No_RE_ABL",
                            backgroundColor:["cyan","yellow","green","cyan","cyan"],
                            data: chartData2,
                            // order: 4,
                            // minBarLength: 5,
                            yAxisID: 'second-y-axis',
                            // xAxisID: 'second-x-axis',
                            maxBarThickness: 50

                            }
                        ]
                    },
                    options: {
                        scales: {
                            yAxes: [{
                                id: 'first-y-axis',
                                type: 'linear',
                                ticks:{
                                    beginAtZero: true
                                }
                            }
                            ,{
                                id: 'second-y-axis',
                                type: 'linear',
                                position: 'right',
                                    ticks:{
                                        beginAtZero: true
                                    },
                                    gridLines:{
                                        drawOnChartArea: false
                                    },
                            }
                            ],
                            xAxes:[
                                {
                                    id: 'first-x-axis',
                                    type: 'category',
                                    labels: lables

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
                        chart.data.datasets[0].data = chartData;
                        chart.data.datasets[1].data = chartData2;
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
