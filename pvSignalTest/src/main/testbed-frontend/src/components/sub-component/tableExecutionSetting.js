import React from 'react';
import { AiFillInfoCircle } from "react-icons/ai";
import OverflowTip from './OverflowTip';

import './../TestCasesContainer.css';


//time function
function timeDifference(date1, date2) {
	var difference = date1.getTime() - date2.getTime();

	var daysDifference = Math.floor(difference / 1000 / 60 / 60 / 24);
	difference -= daysDifference * 1000 * 60 * 60 * 24

	var hoursDifference = Math.floor(difference / 1000 / 60 / 60);
	difference -= hoursDifference * 1000 * 60 * 60

	var minutesDifference = Math.floor(difference / 1000 / 60);
	difference -= minutesDifference * 1000 * 60

	var secondsDifference = Math.floor(difference / 1000);

	console.log('difference = ' +
		daysDifference + ' day/s ' +
		hoursDifference + ' hour/s ' +
		minutesDifference + ' minute/s ' +
		secondsDifference + ' second/s ');
	let diff_time = "";

	if (daysDifference) {
		return (
			daysDifference + ' day ' +
			hoursDifference + ' hour ' +
			minutesDifference + ' minute ' +
			secondsDifference + ' seconds ');
	}
	else if (hoursDifference) {
		return (

			hoursDifference + ' hour ' +
			minutesDifference + ' minute ' +
			secondsDifference + ' seconds ');
	}
	else if (minutesDifference) {
		return (
			minutesDifference + ' minute ' +
			secondsDifference + ' seconds ');
	}
	else if (secondsDifference) {
		return (
			secondsDifference + ' seconds ');
	}
	else {
		return "0 seconds"
	}


}

//extract product from json row.product
export function extractProduct(productJson) {
	const x = JSON.parse(`${productJson}`)
	let val = "";
	for (var i = 1; i <= 4; i++) {
		if (x[i].length) {
			for (var j = 0; j < x[i].length; j++) {
				if (j === x[i].length - 1) {
					val += (x[i][j].name);
				}
				else {
					val += (x[i][j].name + ", ")
				}

				//console.log(x[i][j].name)
			}
		}

	}
	return val

}

//extract product from json row.product
export function extractProductGroup(dataVal) {
	console.log("++++++++++++++++++++++++++++++++++++++++++++++")
	console.log(dataVal)
		const data = JSON.parse(`${dataVal}`)

	let val = "";
	for (var i = 0; i < data.length; i++) {
		if (i == data.length - 1) {
			val += data[i].name.split(" ")[0];
		}
		else {
			val += (data[i].name.split(" ")[0] + ", ")
		}
	}
	return val;

}

/* Theme Setting */
const artificialTheme = {
	name: {
		fontSize: '80px'
	},
	title: {
		fontSize: '80px',
		fontColor: '#FFFFFF',
		backgroundColor: '#363640',
	},
	contextMenu: {
		fontSize: '80px',
		backgroundColor: '#E91E63',
		fontColor: '#FFFFFF',
	},
	header: {
		fontSize: '80px',
		fontColor: '#FFFFFF',
		backgroundColor: '#363640',
	},
	rows: {
		fontSize: '50px',
		fontColor: '#FFFFFF',
		backgroundColor: '#363640',
		borderColor: 'rgba(255, 255, 255, .12)',
		hoverFontColor: 'black',
		hoverBackgroundColor: 'rgba(0, 0, 0, .24)',
	},
	cells: {
		cellPadding: '10px',
	},
};



const columns = [
	{
		name: 'Alert Name',
		selector: row => `${row.NAME}`,
		sortable: true,
		ignoreRowClick: true,
		width: '200px',
		cell: row => {
			return row.NAME

		},
	},
	{
		name: 'Alert Type',
		selector: row => `${row.TYPE}`,
		sortable: true,
		ignoreRowClick: true,
		width: '200px',
		cell: row => {
			return row.TYPE

		},
	},
	{
		name: 'Products',
		selector: row => `${row.PRODUCTS}`,
		sortable: true,
		ignoreRowClick: true,
		width: '300px',

		cell: row => {
			if (row.PRODUCTS) {
				return extractProduct(row.PRODUCTS)
			}
			else {
				return extractProductGroup(row.PRODUCT_GROUP)
			}

		},
	},
	{
		name: 'Is_Adhoc',
		selector: row => `${row.ADHOC_RUN}`,
		sortable: true,
		ignoreRowClick: true,
		width: '100px',
		cell: row => {
			if (row.ADHOC_RUN) {
				return "Yes"
			}
			else {
				return "No"
			}

		},
	},
	{
		name: 'Frequency',
		selector: row => `${row.FREQUENCY}`,
		sortable: true,
		ignoreRowClick: true,
		width: '200px',
		cell: row => {
			return row.FREQUENCY

		},
	},
	{
		name: 'Execution Status',
		selector: row => `${row.EX_STATUS}`,
		sortable: true,
		ignoreRowClick: true,
		width: '200px',
		cell: row => {
			if (row.EX_STATUS == "ERROR") {
				return <div style={{ color: "#ff531a" }}> FAILED </div>

			}
			else if (row.EX_STATUS == "COMPLETED") {
				return <div style={{ color: "#00cc33" }}> COMPLETED </div>
			}
			else {
				return <div style={{ color: "steelblue" }}> {row.EX_STATUS} </div>
			}

		},
	},
	{
		name: 'Date Created',
		selector: row => `${row.DATE_CREATED}`,
		sortable: true,
		ignoreRowClick: true,
		width: '200px',
		cell: row => {
			return row.DATE_CREATED

		},
	},
	{
		name: 'Stack Trace',
		selector: row => `${row.STACK_TRACE}`,
		sortable: true,
		ignoreRowClick: true,
		width: '400px',
		cell: row => {
			if (row.EX_STATUS != "ERROR") {
				return (
					<div>-</div>
				)
			}
			else {
				return <OverflowTip value={row.STACK_TRACE} someLongText={row.STACK_TRACE} />  //<div className='stackTraceCol'> {row.stackTrace}</div>
			}

		},
	},
	{
		name: 'Run Duration',
		selector: row => `${row.END_TIME}`,
		sortable: true,
		ignoreRowClick: true,
		width: '200px',
		cell: row => {
			if (!row.END_TIME) {
				return (
					<div>-</div>
				)
			}
			else {
				let e_time = new Date(row.END_TIME)
				let s_time = new Date(row.START_TIME)
				let run_dur = timeDifference(e_time, s_time);

				return `${run_dur}`
			}

		},
	},
	{
		name: 'PE Count',
		selector: row => `${row.PEC_COUNT}`,
		sortable: true,
		ignoreRowClick: true,
		width: '100px',
		cell: row => {
			if (row.PEC_COUNT > 1) {
				return row.PEC_COUNT
			}
			else return 0;

		},
	}
]

export { artificialTheme, columns };
