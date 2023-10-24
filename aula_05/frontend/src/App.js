import React, {useState} from 'react';
import axios from 'axios';
import './App.css';

function App() {

  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [document, setDocument] = useState("")
  const [passengerId, setPassengerId] = useState("")

  async function handleCreatePassenger(){

    const input = {
      name: name,
      email: email,
      document: document
    }

    const response = await axios.post("http://localhost:8080/passengers", input)
    const output = response.data
    setPassengerId(output.passengerId)
  }

  function handleName(value){
    setName(value)
  }
  function handleEmail(value){
    setEmail(value)
  }
  function handleDocument(value){
    setDocument(value)
  }

  return (
    <div className="Passenger">
      <input className='passenger-name' onChange={(e) => handleName(e.target.value)} value={name}/>
      <input className='passenger-email' onChange={(e) => handleEmail(e.target.value)} value={email}/>
      <input className='passenger-document' onChange={(e) => handleDocument(e.target.value)} value={document}/>
      <button data-testid="button-id" className='create-passenger-button' onClick={handleCreatePassenger}></button>
      <div data-testid="passenger-id" className='passenger-id'>{passengerId}</div>
    </div>
  );
}

export default App;
