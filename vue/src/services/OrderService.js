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
  getCrusts() {
    return axios.get('/menu/crusts');
  },
  
  getToppings() {
    return axios.get('/menu/toppings');
  },
  getSauces() {
    return axios.get('/menu/sauces');
  },
  getCheeses() {
    return axios.get('/menu/cheeses');
  },
  
  getSizes() {
    return axios.get('/menu/sizes');
  },
  getSpecialtyPizzas() {
    return axios.get('/menu/specialtyPizzas');
  }


 

}