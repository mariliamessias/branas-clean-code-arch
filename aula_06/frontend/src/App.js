import React from 'react';
import './App.css';
import CreateDriver from './view/CreateDriver.js';
import CreatePassenger from './view/CreatePassenger.js';
import PassengerRide from './view/PassengerRide.js';

function App() {

  return (
    <>
    <div className='App'>
    <CreateDriver/>
    <CreatePassenger/>
    <PassengerRide/>
    </div>
    </>
  );
}

export default App;
