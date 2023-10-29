import React, {useState} from 'react';
import moment from 'moment';
import './PassengerRide.css';
import RideGateway from '../infra/gateway/RideGateway.js';
import { RideBuilder } from '../domain/Ride.js';

function PassengerRide() {

  const [fromLat, setFromLat] = useState(0)
  const [fromLong, setFromLong] = useState(0)
  const [toLat, setToLat] = useState(0)
  const [toLong, setToLong] = useState(0)
  const [price, setPrice] = useState("")
  const [error, setError] = useState("")
  const [rideId, setRideId] = useState("")
  const [passengerId, setPassengerId] = useState("")
  
  async function handleCalculateRide(){
 
    try {
      const ride = new RideBuilder(
        passengerId,
        fromLat,
        fromLong,
        toLat,
        toLong
      ).build()
      setError("");
      const rideGateway = new RideGateway();
      const output = await rideGateway.calculate(
        {
            positions: [
                {
                    lat: ride.from.lat,
                    long: ride.from.long,
                    date: moment().format('YYYY-MM-DDTHH:mm:ss')
                },
                {
                    lat: ride.to.lat,
                    long: ride.to.long,
                    date: moment().format('YYYY-MM-DDTHH:mm:ss')
                }
            ]
        }
      )
      setPrice(output.price)
    }catch(error) {
        console.log(error)
      setError(error.message);
    }
  }

  async function handleRequestRide(){
    const ride = new RideBuilder(
        passengerId,
        fromLat,
        fromLong,
        toLat,
        toLong
      ).build()
      setError("");
      const rideGateway = new RideGateway();
      const output = await rideGateway.request(
        {
                passengerId: ride.passengerId,
                from : {
                    lat: ride.from.lat,
                    long: ride.from.long,
                },
                to : {
                    lat: ride.to.lat,
                    long: ride.to.long,
                },
                date: moment().format('YYYY-MM-DDTHH:mm:ss')
        })

        setRideId(output.rideId)
    }
  function handlePassengerId(value){
    setPassengerId(value)
  }
  function handleFromLat(value){
    setFromLat(value)
  }
  function handleFromLong(value){
    setFromLong(value)
  }
  function handleToLat(value){
    setToLat(value)
  }
  function handleToLong(value){
    setToLong(value)
  }

  return (
    <div className="Passenger">
      <img src='https://cdn-icons-png.flaticon.com/512/6122/6122142.png'></img>
      <h3>Calculate Ride</h3>
      <input placeholder="Passenger ID" className='ride-passenger-id' onChange={(e) => handlePassengerId(e.target.value)} value={passengerId}/>
      <input placeholder="Ride From Latitude" className='ride-from-lat' onChange={(e) => handleFromLat(e.target.value)} value={fromLat}/>
      <input placeholder="Ride From Longitude" className='ride-from-long' onChange={(e) => handleFromLong(e.target.value)} value={fromLong}/>
      <input placeholder="Ride To Latitude" className='ride-to-lat' onChange={(e) => handleToLat(e.target.value)} value={toLat}/>
      <input placeholder="Ride To Longitude" className='ride-to-long' onChange={(e) => handleToLong(e.target.value)} value={toLong}/>
      <button data-testid="calculate-ride-button" className='calculate-ride-button' onClick={handleCalculateRide}>Calculate Ride</button>
      <div data-testid="error">{error}</div>
      <div data-testid="ride-price" className='ride-price'>{price}</div>
      <div data-testid="ride-id" className='ride-id'>{rideId}</div>
      <button data-testid="request-ride-button" className='request-ride-button' onClick={handleRequestRide}>Request Ride</button>
    </div>
  );
}

export default PassengerRide;
