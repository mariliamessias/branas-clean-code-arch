import axios from "axios";

export default class RideGateway {
    async calculate(ride){
        const response = await axios.post("http://localhost:8080/calculate_ride", ride)
        return response.data;
    }

    async request(ride){
        const response = await axios.post("http://localhost:8080/request_ride", ride)
        return response.data;
    }
}