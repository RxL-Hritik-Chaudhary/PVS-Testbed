import React from 'react';
import './TestCasesContainer.css';

/* Theme Setting */
const darkTheme = {
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

/* Columns Setting */
const columns = [
	{
		name: 'Alert Type',
		selector: row => `${row.alertType}`,
		sortable: true,
		ignoreRowClick: true,
		width: '200px',
		cell: row => {
			if (row.alertType == "null") {
				return (
					<div>-</div>
				)
			}
			else {
				return row.alertType
			}

		},
	},
	{
		name: 'Owner',
		selector: row => `${row.owner}`,
		sortable: true,
		ignoreRowClick: true,

	},
	{
		/* KRW-USD premium */
		name: 'Data Source',
		selector: row => `${row.dataSource}`,
		sortable: true,

	},
	{
		name: 'Products',
		selector: row => `${row.products}`,
		sortable: true,
		ignoreRowClick: true,
		width: '300px',
	},
	{
		name: 'Is Adhoc',
		selector: row => `${row.isAdhoc}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.isAdhoc) {
				return (
					<div className='trueBooleanColor'>
						True
					</div>
				);
			} else {
				return (
					<div className='falseBooleanColor'>
						False
					</div>
				);
			}
		},
	},
	{
		name: 'Exclude Follow-Up',
		selector: row => `${row.isExcludeFollowUp}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.isExcludeFollowUp) {
				return (
					<div className='trueBooleanColor'>
						True
					</div>
				);
			} else {
				return (
					<div className='falseBooleanColor'>
						False
					</div>
				);
			}
		},
	},
	{
		name: 'Data Mining based on SMQ/Event group',
		selector: row => `${row.isDataMiningSMQ}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.isDataMiningSMQ) {
				return (
					<div className='trueBooleanColor'>
						True
					</div>
				);
			} else {
				return (
					<div className='falseBooleanColor'>
						False
					</div>
				);
			}
		},
	},
	{
		name: 'Exclude Non-valid Cases',
		selector: row => `${row.isExcludeNonValidCases}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.isExcludeNonValidCases) {
				return (
					<div className='trueBooleanColor'>
						True
					</div>
				);
			} else {
				return (
					<div className='falseBooleanColor'>
						False
					</div>
				);
			}
		},
	},
	{
		name: 'Include Cases Missed in the Previous Reporting Period',
		selector: row => `${row.isIncludeMissingCases}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.isIncludeMissingCases) {
				return (
					<div className='trueBooleanColor'>
						True
					</div>
				);
			} else {
				return (
					<div className='falseBooleanColor'>
						False
					</div>
				);
			}
		},
	},
	{
		name: 'Apply Alert Stop List',
		selector: row => `${row.isApplyAlertStopList}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.isApplyAlertStopList) {
				return (
					<div className='trueBooleanColor'>
						True
					</div>
				);
			} else {
				return (
					<div className='falseBooleanColor'>
						False
					</div>
				);
			}
		},
	},
	{
		name: 'Include Medically Confirmed Cases Only',
		selector: row => `${row.isIncludeMedicallyConfirmedCases}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.isIncludeMedicallyConfirmedCases) {
				return (
					<div className='trueBooleanColor'>
						True
					</div>
				);
			} else {
				return (
					<div className='falseBooleanColor'>
						False
					</div>
				);
			}
		},
	},
	{
		name: 'Date Range Type',
		selector: row => `${row.dateRangeType}`,
		sortable: true,
		ignoreRowClick: true,

	},
	{
		name: 'Date Range',
		selector: row => `${row.dateRange}`,
		sortable: true,
		ignoreRowClick: true,

	},
	{
		name: 'X For Date Range',
		selector: row => `${row.xForDateRange}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.xForDateRange == "null") {
				return (
					<div>-</div>
				);
			}
			else {
				return row.xForDateRange
			}
		},
	},
	{
		name: 'Start Date',
		selector: row => `${row.startDate}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.startDate == "null") {
				return (
					<div>-</div>
				);
			}
			else {
				return row.startDate
			}
		},
	},
	{
		name: 'End Date',
		selector: row => `${row.endDate}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.endDate == "null") {
				return (
					<div>-</div>
				);
			}
			else {
				return row.endDate
			}
		},
	},
	{
		name: 'Evaluate Case Date On',
		selector: row => `${row.evaluateCaseDateOn}`,
		sortable: true,
		ignoreRowClick: true,

	},
	{
		name: 'Version As Of Date',
		selector: row => `${row.versionAsOfDate}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.versionAsOfDate == "null") {
				return (
					<div>-</div>
				);
			}
			else {
				return row.versionAsOfDate
			}
		},
	},
	{
		name: 'Product Type',
		selector: row => `${row.productType}`,
		sortable: true,
		ignoreRowClick: true,
		width: '200px',
		cell: row => {
			if (row.productType == "null") {
				return (
					<div>-</div>
				);
			}
			else {
				return row.productType
			}
		},
	},
	{
		name: 'Priority',
		selector: row => `${row.priority}`,
		sortable: true,
		ignoreRowClick: true,
		cell: row => {
			if (row.priority == "null") {
				return (
					<div>-</div>
				);
			}
			else {
				return row.priority
			}
		},
	},
	{
		name: 'Assigned To',
		selector: row => `${row.assignedTo}`,
		sortable: true,
		ignoreRowClick: true,

	},
	{
		name: 'Share With',
		selector: row => `${row.shareWith}`,
		sortable: true,
		ignoreRowClick: true,

	},
	{
		name: 'Limit to Case Series',
		selector: row => `${row.limitCaseSeries}`,
		sortable: true,
		ignoreRowClick: true,
		width: '200px',
		cell: row => {
			if (row.limitCaseSeries == "null") {
				return (
					<div>-</div>
				);
			}
			else {
				return row.limitCaseSeries
			}
		},
	}
];

export { darkTheme, columns };
