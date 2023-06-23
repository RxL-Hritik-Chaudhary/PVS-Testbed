import React, { useState, useEffect } from 'react';
import ExecutionContainer from './sub-component/ExecutionContainer';
import ChartPage from './sub-component/ChartPage';
import './TestCasesContainer.css';
import axios from 'axios';

function ExecutionPage(props) {
	
	let chartDataState = [
			{
				type: "Aggregate Case Alert",
				failedCount: 0,
				passedCount: 0
			},
			{
				type: "Individual Case Alert",
				failedCount: 0,
				passedCount: 0
			},
			{
				type: "Aggregate Adhoc Review",
				failedCount: 0,
				passedCount: 0
			},
			{
				type: "Individual Adhoc Case Review",
				failedCount: 0,
				passedCount: 0
			}
		]
	
	const [chartData, setChartData] = useState(chartDataState)

	const extractExecutionStatusDataForChart = (data) => {

		


	}


	const listObjectExecutionStatus = (data) => {
		console.log("extracting passed and failed alert counts")
		console.log(data)
		setChartData(chartDataState)
		for (var i = 0; i < data.length; i++) {
			if (data[i].TYPE == "Aggregate Case Alert" && data[i].ADHOC_RUN == 0) {
				if (data[i].EX_STATUS == "ERROR") {
					chartData[0].failedCount += 1;
				}
				else if (data[i].EX_STATUS == "COMPLETED") {
					chartData[0].passedCount += 1;
				}
			}
			else if (data[i].TYPE == "Single Case Alert" && data[i].ADHOC_RUN == 0) {
				if (data[i].EX_STATUS == "ERROR") {
					chartData[1].failedCount += 1;
				}
				else if (data[i].EX_STATUS == "COMPLETED") {
					chartData[1].passedCount += 1;
				}
			}
			else if (data[i].TYPE == "Aggregate Case Alert" && data[i].ADHOC_RUN == 1) {
				if (data[i].EX_STATUS == "ERROR") {
					chartData[2].failedCount += 1;
				}
				else if (data[i].EX_STATUS == "COMPLETED") {
					chartData[2].passedCount += 1;
				}
			}
			else if (data[i].TYPE == "Single Case Alert" && data[i].ADHOC_RUN == 1) {
				if (data[i].EX_STATUS == "ERROR") {
					chartData[3].failedCount += 1;
				}
				else if (data[i].EX_STATUS == "COMPLETED") {
					chartData[3].passedCount += 1;
				}
			}

		}
		setChartData(chartData)
		console.log(chartData)
	}
	
	const deleteSmokeAlerts = async () => {
		axios.delete("http://localhost:8181/deleteAlerts").then(response => {
			if(response.data == "Success") {
				console.log("Deleted succesfully")
			}
			else{
				console.log("Error during deletion")
			}
		})
	}



	return (
		<div>
			{/*<ChartPage chartObjectData = {chartData}/>*/}
			<ExecutionContainer alertNames={props.alertNames} listObjectExecutionStatus={listObjectExecutionStatus} />
			<button className='btnDeleteDesign' style={{ float: "left", marginLeft: "10px", marginTop: "10px", marginBottom: "20px" }} onClick={deleteSmokeAlerts}>
				Delete Test Alerts
			</button>
		</div>
	);
}

export default ExecutionPage;