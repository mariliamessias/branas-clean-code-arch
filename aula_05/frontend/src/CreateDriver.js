import React, {useState} from 'react';
import DriverGateway from './infra/gateway/DriverGateway.js';
import './CreateDriver.css';
import Driver from './domain/Driver.js';

function CreateDriver() {

  const [name, setName] = useState("")
  const [email, setEmail] = useState("")
  const [document, setDocument] = useState("")
  const [driverId, setDriverId] = useState("")
  const [carPlate, setCarPlate] = useState("")

  async function handleCreateDriver(){

    const driver = new Driver({
      name,
      email,
      document,
      carPlate
    })

    const driverGateway = new DriverGateway();
    const output = await driverGateway.save(driver)
    setDriverId(output.driverId)
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

  function handleCarPlate(value){
    setCarPlate(value)
  }

  return (
    <div className="Driver">
      <img src='https://cdn-icons-png.flaticon.com/512/1535/1535791.png'></img>
      <h3>Create Driver</h3>
      <input placeholder="Name" className='driver-name' onChange={(e) => handleName(e.target.value)} value={name}/>
      <input placeholder="Email" className='driver-email' onChange={(e) => handleEmail(e.target.value)} value={email}/>
      <input placeholder="Car Plate" className='driver-carplate' onChange={(e) => handleCarPlate(e.target.value)} value={carPlate}/>
      <input placeholder="Document Number" className='driver-document' onChange={(e) => handleDocument(e.target.value)} value={document}/>
      <button data-testid="button-id" className='create-driver-button' onClick={handleCreateDriver}>Create</button>
      <div data-testid="driver-id" className='driver-id'>{driverId}</div>
    </div>
  );
}

export default CreateDriver;
