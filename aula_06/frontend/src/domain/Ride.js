import Coord from "./Coord.js";

export default class Ride{
      constructor (passengerId, fromLat, fromLong, toLat, toLong) {
        this.passengerId = passengerId;
        this.from =  new Coord(fromLat, fromLong);
        this.to = new Coord(toLat, toLong);
      }
    
      static create (builder) {
        return new Ride(builder.passengerId, builder.fromLat, builder.fromLong, builder.toLat, builder.toLong);
      }
}

export class RideBuilder {
	constructor (passengerId, fromLat, fromLong, toLat, toLong) {
    this.passengerId = passengerId;
    this.fromLat = fromLat;
    this.fromLong = fromLong;
    this.toLat = toLat;
    this.toLong = toLong;
  }

	build () {
		return Ride.create(this);
	}
}