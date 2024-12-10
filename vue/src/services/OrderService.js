import axios from 'axios';


const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
});

export default {

  customerOrder(order){
    console.log(order);
    alert(this.$store.state.user);
    return http.post('/customerOrder', order);
  },
    
  getPizzaOptions() {
    return http.get('/options');
  },
  
  getPizzas() {
    return http.get('/menu');
  }
  

 

}