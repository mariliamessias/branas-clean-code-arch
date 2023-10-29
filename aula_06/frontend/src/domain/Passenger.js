import Cpf from "./Cpf.js";
import Email from "./Email.js";
import Name from "./Name.js";

export default class Passenger{
      constructor (passengerId, name, email, document) {
        this.passengerId = passengerId;
        this.name = new Name(name);
        this.email = new Email(email);
        this.document = new Cpf(document);
      }
    
      static create (builder) {
        return new Passenger(builder.passengerId, builder.name, builder.email, builder.document);
      }
}

export class PassengerBuilder {
	constructor (name, email, document) {
    this.name = new Name(name);
    this.email = new Email(email);
    this.document = new Cpf(document);
  }

	build () {
		return Passenger.create(this);
	}
}