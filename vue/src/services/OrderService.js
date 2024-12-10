import axios from 'axios';


const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  customerOrder(order,user){
    console.log(order);
    return http.post('/orders', order,user);
  },
    
  getPizzaOptions() {
    return http.get('/options');
  },
  
  getPizzas() {
    return http.get('/menu');
  }
  

 

}