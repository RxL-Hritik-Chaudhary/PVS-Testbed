import React,{useEffect} from 'react';
import ReactDOM from 'react-dom';
import Modal from 'react-modal';
import Axios from 'axios';


const customStyles = {
  content: {
    top: '35%',
    left: '50%',
    right: 'auto',
    bottom: 'auto',
    marginRight: '-50%',
    transform: 'translate(-50%, -50%)',
  },
};

// Make sure to bind modal to your appElement (https://reactcommunity.org/react-modal/accessibility/)
Modal.setAppElement('#model');

function CheckFileAlreadyExists(props) {
  let subtitle;
  const [modalIsOpen, setIsOpen] = React.useState(false);

  function openModal() {
    setIsOpen(true);
  }

  /*function afterOpenModal() {
    // references are now sync'd and can be accessed.
    subtitle.style.color = '#f00';
  }*/

  function closeModal() {
    setIsOpen(false);
  }

	function useExitingFileForTCs () {
		props.runExistingFileTCs();
		setIsOpen(false);
	}
	
	useEffect(
    () => {
      const loadData = async () => {
        const response = await Axios.get("http://localhost:8181/api/checkFileExists");
        setIsOpen(response.data);
      };
      loadData();
    },
    []
  );

  return (
    <div>
 {/*    <button onClick={openModal}>Open Modal</button> */}
        <Modal
        isOpen={modalIsOpen}
        /*onAfterOpen={afterOpenModal}*/
        onRequestClose={closeModal}
        style={customStyles}
        contentLabel="Example Modal"
        shouldCloseOnOverlayClick={false}
      >
{/*        <h2 ref={(_subtitle) => (subtitle = _subtitle)}>Hello</h2> */}
        <h3>File already exists in system</h3>
       {/* <button onClick={closeModal}>close</button>  */}
        <div>Do you want to Upload again?</div>
        <form>
          <button className='btnDeleteDesign' style={{margin:"10px 0px 8px 0px"}} onClick={useExitingFileForTCs} >No, Show existed file test-cases</button>
          <button className='btnDesign' onClick={closeModal} style={{marginLeft:"30px"}}>Yes</button>

        </form>
      </Modal>
    </div>
  );
}

export default CheckFileAlreadyExists;

