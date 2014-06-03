// Average Distance Bar Chart
function displayUserWeightLineChart(avgRunning, avgCycling, avgRowing) {

    $('#avgCaloriesByActivityDiv').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Avg. Calories per Activity'
        },
        xAxis: {
            categories: ['Activity']
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Calories'
            }
        },
        tooltip: {
            headerFormat: '<span style="font-size:10px">{point.key}</span><table>',
            pointFormat: '<tr><td style="color:{series.color};padding:0">{series.name}: </td>' +
                '<td style="padding:0"><b>{point.y:.1f} avg. calories</b></td></tr>',
            footerFormat: '</table>',
            shared: true,
            useHTML: true
        },
        legend: {
            align: 'right',
            verticalAlign: 'top',
            layout: 'vertical',
            x: 0,
            y: 100
        },
        plotOptions: {
            column: {
                pointPadding: 0.2,
                borderWidth: 0
            }
        },
        series: [{
            name: 'Running',
            color: '#CCC',
            data: [avgRunning]

        }, {
            name: 'Cycling',
            color: '#333',
            data: [avgCycling]

        }, {
            name: 'Rowing',
            color: '#666',
            data: [avgRowing]

        }]
    });

}

function displayAvgDistanceLineChart(avgRunning, avgCycling, avgRowing) {

    $('#activityDistanceDiv').highcharts({
        chart: {
            type: 'column'
        },
        title: {
            text: 'Avg. Distance per Activity'
        },
        xAxis: {
            type: 'category',
            labels: {
                rotation: -45,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Distance (km)'
            }
        },
        legend: {
            enabled: false
        },
        tooltip: {
            pointFormat: '<b>{point.y:.1f}</b> (km)'
        },
        series: [{
            name: 'Population',
            data: [
                ['Running', avgRunning],
                ['Cycling', avgCycling],
                ['Rowing', avgRowing]
            ],
            dataLabels: {
                enabled: true,
                rotation: -90,
                color: '#000',
                align: 'right',
                x: 4,
                y: 10,
                style: {
                    fontSize: '13px',
                    fontFamily: 'Verdana, sans-serif'
                }
            }
        }]
    });

}

// Average Duration Pie Chart
function displayActivityPieChart(avgRunning, avgCycling, avgRowing) {

    Highcharts.setOptions({
        colors: ['#CCC', '#333', '#666', '#24CBE5']
    });

    $('#activityDurationDiv').highcharts({
        chart: {
            plotBackgroundColor: null,
            plotBorderWidth: null,
            plotShadow: false
        },
        title: {
            text: 'Avg. Activity Duration'
        },
        plotOptions: {
            dataLabels: {
                style: {
                    color: Highcharts.theme
                }
            }
        },
        series: [{
            type: 'pie',
            name: 'Duration',
            data: [
                ['Running', avgRunning],
                ['Cycling', avgCycling],
                ['Rowing', avgRowing]
            ]
        }]
    });


}

// Average Duration Bar Chart
function displayAvgDurationLineChart(avgRunning, avgCycling, avgRowing) {

    $('#activityAvgDurationDiv').highcharts({
        chart: {
            type: 'bar'
        },
        title: {
            text: 'Avg. Duration per Activity'
        },
        xAxis: {
            categories: ['Activity'],
            title: {
                text: null
            }
        },
        yAxis: {
            min: 0,
            title: {
                text: 'Duration (minutes)',
                align: 'high'
            },
            labels: {
                overflow: 'justify'
            }
        },
        tooltip: {
            valueSuffix: ' minutes'
        },
        plotOptions: {
            bar: {
                dataLabels: {
                    enabled: true
                }
            }
        },
        legend: {
            layout: 'vertical',
            align: 'right',
            verticalAlign: 'top',
            x: -40,
            y: 100,
            floating: true,
            borderWidth: 1,
            backgroundColor: (Highcharts.theme && Highcharts.theme.legendBackgroundColor || '#FFFFFF'),
            shadow: true
        },
        credits: {
            enabled: false
        },
        series: [{
            name: 'Running',
            data: [avgRunning]
        }, {
            name: 'Cycling',
            data: [avgCycling]
        }, {
            name: 'Rowing',
            data: [avgRowing]
        }]
    });

}