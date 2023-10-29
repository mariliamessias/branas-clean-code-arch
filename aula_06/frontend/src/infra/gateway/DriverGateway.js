import axios from "axios";

export default class DriverGateway {
    async save(driver){
        const response = await axios.post("http://localhost:8080/drivers", driver)
        return response.data;
    }
}