import axios from "axios";

export default class PassengerGateway {
    async save(passenger){
        const response = await axios.post("http://localhost:8080/passengers", passenger)
        return response.data;
    }
}