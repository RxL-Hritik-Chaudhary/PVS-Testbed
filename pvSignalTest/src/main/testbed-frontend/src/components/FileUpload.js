
import axios from 'axios';
  
import React,{Component} from 'react';
  
class App extends Component {
   
   constructor(props) {
	   super(props);
	   
   }
    state = {
  
      // Initially, no file is selected
      selectedFile: null
    };
     
    // On file select (from the pop up)
    onFileChange = event => {
     
      // Update the state
      this.setState({ selectedFile: event.target.files[0] });
     
    };
     
    // On file upload (click the upload button)
    onFileUpload = () => {
     
      // Create an object of formData
      const formData = new FormData();
     
      // Update the formData object
      formData.append(
        "file",
        this.state.selectedFile,
        this.state.selectedFile.name
      );
     
      // Details of the uploaded file
      console.log(this.state.selectedFile);
     
      // Request made to the backend api
      // Send formData object
      axios.post("http://localhost:8181/files", formData).then(response => {
		        if(response.status == 200) {
					axios.get("http://localhost:8181/api/getFileInfo").then(response => {
						this.props.dataFromExcelSheet(response.data)
					});
				}
	  });
    };
     
    // File content to be displayed after
    // file upload is complete
    fileData = () => {
     
      if (this.state.selectedFile) {
          
        return (
          <div>
            <h2>File Details:</h2>
            <p>File Name: {this.state.selectedFile.name}</p>
  
            <p>File Type: {this.state.selectedFile.type}</p>
  
            <p>
              Last Modified:{" "}
              {this.state.selectedFile.lastModifiedDate.toDateString()}
            </p>
  
          </div>
        );
      } else {
        return (
          <div>
            <br />
            <h4>Choose before Pressing the Upload button</h4>
          </div>
        );
      }
    };
     
    render() {
     
      return (
        <div>
            <h1 style={{color:"darkolivegreen"}}>
              TestBed PV-Signal
            </h1>
            <h3>
              File Upload!
            </h3>
            <div>
                <input type="file" onChange={this.onFileChange} />
                <button className='btnDesign' onClick={this.onFileUpload}>
                  Upload!
                </button>
            </div>
          {this.fileData()}
          <button className='btnDeleteDesign' style={{margin:"10px 0px 8px 0px"}} onClick={()=>{this.props.executionPage(true)}} >
                  Go to Alert Execution Page
                </button>
        </div>
      );
    }
  }
  
  export default App;
  
  