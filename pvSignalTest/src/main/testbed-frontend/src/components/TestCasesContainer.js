import React, { Component } from 'react';
import DataTable from 'react-data-table-component';
import axios from 'axios';
import { darkTheme, columns } from './tableSetting';


/* Main Component */
class TestCasesContainer extends Component {

	constructor(props) {
		super(props);

		this.state = {
			title: <div className="Load">Loading Test Cases Data from XL Sheet...</div>,
			status: <div className="initLoading">Loading Test Cases Data from XL Sheet...</div>,
			data: [],
			selectedData: [],
			loading: false
		};

		this.handleChangeTable = this.handleChangeTable.bind(this);
	}/*
	state = {
		title: <div className="Load">Loading Test Cases Data from XL Sheet...</div>,
		status: <div className="initLoading">Loading Test Cases Data from XL Sheet...</div>,
		data: [],
		selectedData: [],
	}; */

	/* Error Catch */
	componentDidCatch(error, info) {
		console.log(error, info);
	}

	/* ComponentDidMount Cycle */
	componentDidMount() {
		this.setState({
			statue: 'success',
			data: this.props.testCasesData,
			title: <div className="Load" style={{ display: 'none' }}>Loading Test Cases Data from XL Sheet...</div>,
			selectedData: [],
			loading: false
		});

	}

	componentDidUpdate() {
		//console.log(this.state.selectedData);
	}

	/* componentWillUnmount Cycle */
	componentWillUnmount() {
		clearInterval(this.interval); // Prevent memory leaks
		this.cancelSetState = true;
	}

	runSelectedCases() {
		//test case executing please dont close or touch are system
		this.setState({ loading: true })
		console.log("=================================Selected Rows================================")
		console.log(this.state.selectedData);
		//this.props.executionPage(true)
		
		if (this.state.selectedData) {
			axios.post("http://localhost:8181/api/runAlerts", this.state.selectedData).then(response => {
				if (response.status == 200) {
					console.log("yayyyyyyyyyyyyyyy")
					console.log(response.data)  // ['SmokeTestingPVSignalDev_AGG_2022-Dec-26 11:08:12', 'SmokeTestingPVSignalDev_AGG_2022-Dec-26 11:09:17']
					//this.props.executedAlertsNames(response.data)
					

				}
			});
			this.props.executedAlertsNames(["Smoke"])
			this.props.executionPage(true)
		}
	}

	handleChangeTable(value) {
		this.setState({
			selectedData: value.selectedRows,
		});

	};


	render() {
		const { data, title, status, loading } = this.state;
		const paginationComponentOptions = {
			selectAllRowsItem: true,
			selectAllRowsItemText: "ALL"
		};
		let mainComponent;
		if (loading) {
			mainComponent = <div>
			<div className="loader-container">
				<div className="spinner"></div>
			</div>
			<div className='loader-text'>Test Cases creation in Progress</div>
			</div>

		}
		else {
			mainComponent = <React.Fragment>
				<div className="tableContainer">
					<DataTable
						className="Post"
						columns={columns}
						data={data}
						customTheme={darkTheme}
						responsive={true}
						noDataComponent={this.state.status}
						fixedHeader
						pagination
						height={440}
						paginationPerPage={10}
						selectableRows
						onSelectedRowsChange={this.handleChangeTable}
					/>
				</div>
				<div>
					<button className='btnDesign' onClick={() => this.runSelectedCases()} style={{ float: "left", margin: "5px 0px 25px 30px" }}>
						Run Selected Alert
					</button>
				</div>
			</React.Fragment>
		}
		// console.log(data);
		return (
			<div>
				{mainComponent}
				
			</div>
		)
	}
}

export default TestCasesContainer;
