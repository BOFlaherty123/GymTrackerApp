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


};

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