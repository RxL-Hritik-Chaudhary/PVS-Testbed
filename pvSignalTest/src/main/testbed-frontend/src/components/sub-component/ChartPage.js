import React from 'react';
import CanvasJSReact from './../../assests/canvasjs.react'  //'./canvasjs.react';
var CanvasJS = CanvasJSReact.CanvasJS;
var CanvasJSChart = CanvasJSReact.CanvasJSChart;



class ChartPage extends React.Component {

	constructor(props) {
		super();
		this.toggleDataSeries = this.toggleDataSeries.bind(this);

	}

	toggleDataSeries(e) {
		if (typeof (e.dataSeries.visible) === "undefined" || e.dataSeries.visible) {
			e.dataSeries.visible = false;
		}
		else {
			e.dataSeries.visible = true;
		}
		this.chart.render();
	}
	render() {
		console.log(this.props.chartObjectData)
		const options = {
			animationEnabled: true,
			exportEnabled: true,
			title: {
				text: "Alert Execution Status",
				fontFamily: "verdana"
			},
			axisY: {
				//title: "in Eur",
				includeZero: true,

			},
			toolTip: {
				shared: true,
				reversed: true
			},
			legend: {
				verticalAlign: "center",
				horizontalAlign: "right",
				reversed: true,
				cursor: "pointer",
				itemclick: this.toggleDataSeries
			},
			data: [
				{
					type: "stackedColumn",
					name: "Passed",
					showInLegend: true,
					//yValueFormatString: "#,###k",
					dataPoints: [
						{ label: "Aggregate Review", y: this.props.chartObjectData[0].passedCount },
						{ label: "Individual case Review", y: this.props.chartObjectData[1].passedCount },
						{ label: "Aggregate Adhoc Review", y: this.props.chartObjectData[2].passedCount },
						{ label: "Inividual Adhoc Case Review", y: this.props.chartObjectData[3].passedCount }
					]
				},
				{
					type: "stackedColumn",
					name: "Failed",
					showInLegend: true,
					//yValueFormatString: "#,###k",
					dataPoints: [
						{ label: "Aggregate Review", y: this.props.chartObjectData[0].failedCount },
						{ label: "Individual Case Review", y: this.props.chartObjectData[1].failedCount },
						{ label: "Aggregate Adhoc Review", y: this.props.chartObjectData[2].failedCount },
						{ label: "Inividual Adhoc Case Review", y: this.props.chartObjectData[3].failedCount }
					]
				}

			]
		}
		return (
			<div>
				<CanvasJSChart options={options} 
				/*	onRef={ref => this.chart = ref} */
				/>
				{/*You can get reference to the chart instance as shown above using onRef. This allows you to access all chart properties and methods*/}
			</div>
		);
	}
}


export default ChartPage;
