import React, { Component } from 'react';
import DataTable from 'react-data-table-component';
import axios from 'axios';
import { artificialTheme, columns } from './tableExecutionSetting';
import ChartPage from './ChartPage';
import ColorCard from './chart-components/ColorCard';

class ExecutionContainer extends Component {

	constructor(props) {
		super(props);
		console.log("-----------Execution Component----------")
		console.log(this.props.alertNames)
		this.state = {
			data: [{
				name: "Smoke Testing 786",
				type: "Aggregate Case Alert",
				exStatus: "ERROR",
				spotfireExecutionStatus: "null",
				stackTrace: "java.sql.SQLSyntaxErrorException: ORA-00942: table or view does not exist",
				dateCreated: "2022-12-15 13:30:22.569",
				frequency: "3"
			}],
			chartData: [
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
			],
			counts: {
				failedCounts: 0,
				passedCounts: 0
			}

		};

		this.fetchExeStatus = this.fetchExeStatus.bind(this)

	}

	componentDidCatch(error, info) {
		console.log(error, info);
	}

	fetchExeStatus = async () => {
		axios.post("http://localhost:8181/exeStatus", this.props.alertNames).then(response => {
			if (response.status == 200) {


				let sortedExeAlert = response.data.sort(
					(p1, p2) => (p1.DATE_CREATED < p2.DATE_CREATED) ? 1 : (p1.DATE_CREATED > p2.DATE_CREATED) ? -1 : 0);

				console.log("extracting passed and failed alert counts")
				console.log(response.data)
				let passCnt = 0, failCnt = 0;
				let chartDataObject = [
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

				for (var i = 0; i < response.data.length; i++) {
					if (response.data[i].TYPE == "Aggregate Case Alert" && response.data[i].ADHOC_RUN == 0) {
						if (response.data[i].EX_STATUS == "ERROR") {
							chartDataObject[0].failedCount += 1;
							failCnt += 1;
						}
						else if (response.data[i].EX_STATUS == "COMPLETED") {
							chartDataObject[0].passedCount += 1;
							passCnt += 1;
						}
					}
					else if (response.data[i].TYPE == "Single Case Alert" && response.data[i].ADHOC_RUN == 0) {
						if (response.data[i].EX_STATUS == "ERROR") {
							chartDataObject[1].failedCount += 1;
							failCnt += 1;
						}
						else if (response.data[i].EX_STATUS == "COMPLETED") {
							chartDataObject[1].passedCount += 1;
							passCnt += 1;
						}
					}
					else if (response.data[i].TYPE == "Aggregate Case Alert" && response.data[i].ADHOC_RUN == 1) {
						if (response.data[i].EX_STATUS == "ERROR") {
							chartDataObject[2].failedCount += 1;
							failCnt += 1;
						}
						else if (response.data[i].EX_STATUS == "COMPLETED") {
							chartDataObject[2].passedCount += 1;
							passCnt += 1;
						}
					}
					else if (response.data[i].TYPE == "Single Case Alert" && response.data[i].ADHOC_RUN == 1) {
						if (response.data[i].EX_STATUS == "ERROR") {
							chartDataObject[3].failedCount += 1;
							failCnt += 1;
						}
						else if (response.data[i].EX_STATUS == "COMPLETED") {
							chartDataObject[3].passedCount += 1;
							passCnt += 1;
						}
					}

				}

				this.setState({
					data: sortedExeAlert,
					chartData: chartDataObject,
					counts: {
						failedCounts: failCnt,
						passedCounts: passCnt
					}
				});

			}
		})
	}

	/* ComponentDidMount Cycle */
	async componentDidMount() {


		setInterval(this.fetchExeStatus, 5000)


	}

	//ComponentDidUpdate 

	componentDidUpdate() {

	}


	render() {
		const { data } = this.state;


		return (
			<div>
				<div style={{ display: "flex" }}>
					<div style={{ flex: "1.2", margin: "40px 0px 0px 100px" }}>
						<div>
							<ColorCard passColor={'Success'} count={this.state.counts.passedCounts} />
						</div>
						<div style={{ marginTop: "25px" }}>
							<ColorCard passColor={'Danger'} count={this.state.counts.failedCounts} />
						</div>
					</div>
					<div style={{ flex: "1.8", marginRight: "10px" }} >
						<ChartPage chartObjectData={this.state.chartData} />
					</div>

				</div>
				
				<div className="tableContainer">
					<DataTable
						className="Post"
						columns={columns}
						data={data}
						customTheme={artificialTheme}
						responsive={true}
						//noDataComponent={this.state.status}
						fixedHeader
						pagination
						height={440}
						paginationPerPage={10}
					/>
				</div>
			</div>
		)
	}

}
export default ExecutionContainer;

