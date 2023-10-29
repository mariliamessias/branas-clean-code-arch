export default class Name {

	constructor (name) {
		if (!this.validate(name)) throw new Error("Invalid name");
		this.value = name;
	}

	validate (name) {
		return String(name)
			.toLowerCase()
			.match(/^[a-z]+\s([a-z]+){1,}$/);
	}

	getValue () {
		return this.value;
	}
}