export default class CarPlate {

	constructor (value) {
		if (!this.validate(value)) throw new Error("Invalid car plate");
		this.value = value;
	}

	validate (email) {
		return String(email)
			.toLowerCase()
			.match(
			/^[a-z]{3}[0-9]{4}$/
			);
	}

	getValue () {
		return this.value;
	}
}