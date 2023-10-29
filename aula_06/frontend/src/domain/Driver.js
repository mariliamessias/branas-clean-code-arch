import CarPlate from "./CarPlate.js";
import Cpf from "./Cpf.js";
import Email from "./Email.js";
import Name from "./Name.js";

export default class Driver{

    constructor(driverId, name, email, document, carPlate) {  
      this.driverId = driverId;
      this.name = new Name(name);
      this.email = new Email(email);
      this.document = new Cpf(document);
      this.carPlate = new CarPlate(carPlate);
      }

      static create (builder) {
        return new Driver(builder.driverId, builder.name, builder.email, builder.document, builder.carPlate);
      }
}

export class DriverBuilder {
  constructor(name, email, document, carPlate) {  
    this.name = name;
    this.email = email;
    this.document = document;
    this.carPlate = carPlate;
    }
  
	build () {
		return Driver.create(this);
	}
}
