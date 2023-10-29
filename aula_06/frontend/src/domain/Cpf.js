export default class Cpf {

	constructor (value) {
		if (!this.validate(value)) throw new Error("Invalid cpf");
		this.value = value;
	}

	validate (cpf) {
		cpf = this.clean(cpf);
		if (this.isValidLength(cpf)) return false;  
		if (this.hasAllDigitsEqual(cpf)) return false;
		const dg1 = this.calculateDigit(cpf, 10);
		const dg2 = this.calculateDigit(cpf, 11);
		return this.extractCheckDigit(cpf) == `${dg1}${dg2}`;
	}

	clean (cpf) {
		return cpf.replace(/\D/g, "");
	}
	
	isValidLength (cpf) {
		return cpf.length !== 11;
	}
	
	hasAllDigitsEqual (cpf) {
		const [firstDigit] = cpf;
		return [...cpf].every(digit => digit === firstDigit);
	}
	
	calculateDigit (cpf, factor) {
		let total = 0;
		for (const digit of cpf) {
			if (factor > 1) total += parseInt(digit) * factor--;
		}
		const rest = total%11;
		return (rest < 2) ? 0 : 11 - rest;
	}

	extractCheckDigit (cpf) {
		return cpf.slice(9);
	}

	getValue () {
		return this.value;
	}
	
}