import React, {useState} from 'react';
import './App.css';
import CreateDriver from './CreateDriver.js';
import CreatePassenger from './CreatePassenger.js';

function App() {

  return (
    <>
    <div className='App'>
    <CreateDriver/>
    <CreatePassenger/>
    </div>
    </>
  );
}

export default App;
