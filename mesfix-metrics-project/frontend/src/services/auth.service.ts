import axios from 'axios';

const API_URL = 'http://localhost:6071/api';

class AuthService {
    async register(data: userDta) {
        console.log("data-> ", data)
        const response = await axios.post(API_URL + '/register', data);
        return response;
    }

    async login(username, password) {
        const response = await axios.post(API_URL + '/login', {
            username,
            password,
        });
        return response;
    }
    async deleteUser(username) {
        const response = await axios.delete(API_URL + '/delete', { data: { username } });
        return response;
    }
}

export default new AuthService();
