import React, {useState} from 'react';
import './CreatePassenger.css';
import PassengerGateway from '../infra/gateway/PassengerGateway.js';
import { PassengerBuilder } from '../domain/Passenger.js';

function CreatePassenger() {

  const [name, setName] = useState("")
  const [error, setError] = useState("")
  const [email, setEmail] = useState("")
  const [document, setDocument] = useState("")
  const [passengerId, setPassengerId] = useState("")
  async function handleCreatePassenger(){

    try {
      const passenger = new PassengerBuilder(
        name,
        email,
        document,
      ).build()
      setError("");
      const passengerGateway = new PassengerGateway();
      const output = await passengerGateway.save(passenger)
      setPassengerId(output.passengerId)
    }catch(error) {
      setError(error.message);
    }
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
      <img src='https://cdn-icons-png.flaticon.com/512/2566/2566202.png'></img>
      <h3>Create Passenger</h3>
      <input placeholder="Name" className='passenger-name' onChange={(e) => handleName(e.target.value)} value={name}/>
      <input placeholder="Email" className='passenger-email' onChange={(e) => handleEmail(e.target.value)} value={email}/>
      <input placeholder="Document Number" className='passenger-document' onChange={(e) => handleDocument(e.target.value)} value={document}/>
      <button data-testid="button-id" className='create-passenger-button' onClick={handleCreatePassenger}>Create</button>
      <div data-testid="error">{error}</div>
      <div data-testid="passenger-id" className='passenger-id'>{passengerId}</div>
    </div>
  );
}

export default CreatePassenger;
