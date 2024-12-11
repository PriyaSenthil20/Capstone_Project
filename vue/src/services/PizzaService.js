import axios from 'axios';

const userToken = localStorage.getItem('token')

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API,
  headers: {Authorization: `Bearer ${userToken}`}
});

export default {
  menu() {
    return http.get('/menu')
  }
/*
  register(user) {
    return http.post('/register', user)
  },
  registerCustomer(customer){
    return http.post('/customerRegister', customer)
  }
*/
}
