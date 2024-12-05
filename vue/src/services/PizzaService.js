import axios from 'axios';

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  menu() {
    return http.get('/menu')
  },

  /*
  register(user) {
    return http.post('/register', user)
  },

  registerCustomer(customer){
    return http.post('/customerRegister', customer)
  }
*/
 

}
