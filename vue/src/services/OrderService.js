import axios from 'axios';

const userToken = localStorage.getItem('token')

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API,
  headers: {Authorization: `Bearer ${userToken}`}
});

export default {

  customerOrder(order){
    console.log(order);
    return http.post('/orders', order);
  },
    
  getPizzaOptions() {
    return http.get('/options');
  },
  
  getPizzas() {
    return http.get('/menu');
  }
  

 

}