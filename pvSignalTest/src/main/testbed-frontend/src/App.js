import React, { useState, useEffect } from 'react';
import './App.css';
import axios from 'axios';
import FileUpload from './components/FileUpload';
import TestCasesContainer from './components/TestCasesContainer';
import ExecutionPage from './components/ExecutionPage';

function App() {
	const [showTable, setShowTable] = useState(false);
	const [testCasesData, setTestCasesData] = useState('');
	const [count, setCount] = useState("null");
	const [execPage, setExecPage] = useState(false);
	const [alertNames, setAlertNames] = useState([]);

	const fetchData = () => {
		return axios.get("http://localhost:8181")
			.then((response) => { console.log(response.data); setCount(response.data); });
	}

	useEffect(() => {
		fetchData();
	}, [])

	const dataFromExcelSheet = (data) => {
		console.log(data)
		setTestCasesData(data);
		setShowTable(true);
	}

	const executedAlertsNames = (data) => {
		console.log(data);
		setAlertNames(data);
	}

	const executionPage = (data) => {
		setExecPage(true)
	}

	return (
		<div className="App">

			{execPage ? <ExecutionPage alertNames={alertNames} /> : (<React.Fragment><FileUpload dataFromExcelSheet={dataFromExcelSheet} executionPage={executionPage}/>
				{showTable ? <TestCasesContainer testCasesData={testCasesData} executionPage={executionPage} executedAlertsNames={executedAlertsNames} /> : ""}</React.Fragment>)
			}
		</div>
	);
}

export default App;
