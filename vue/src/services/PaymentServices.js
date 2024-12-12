import axios from 'axios';


const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  submitPayment(payment){
    return http.post('/payments', payment);
  }
}