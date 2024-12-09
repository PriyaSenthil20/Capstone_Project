import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import OrderService from '../services/OrderService';
import AuthService from '../services/AuthService';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      customer: {},
      order:[],
      crusts:[],
      toppings:[],
      sauces:[],
      specialtyPizzas:[],
      customPizzas:[],
      cart:{}
    },
    mutations: {
      SET_AUTH_TOKEN(state, token) {
        state.token = token;
        localStorage.setItem('token', token);
        axios.defaults.headers.common['Authorization'] = `Bearer ${token}`;
      },
      SET_USER(state, user) {
        state.user = user;
        localStorage.setItem('user', JSON.stringify(user));
      },
      SET_CUSTOMER(state, customer){
        state.customer = customer;
      },
      SET_CRUSTS(state, crusts){
        state.crusts = crusts;
      },
      SET_TOPPINGS(state, toppings){
        state.toppings= toppings;
      },
      SET_SAUCES(state, sauces){
        state.sauces=sauces;
      },
      SET_SPECIALTY_PIZZAS(state, specialtyPizzas){
        state.specialtyPizzas=specialtyPizzas;
      },
      SET_CUSTOM_PIZZAS(state, customPizzas){
        state.customPizzas=customPizzas;
      },
      ADD_CART(state, cart){
        state.cart.push(cart);
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      CUSTOMER_ORDER(state,order){
        state.order.push(order);
      }
    },
    actions:{
      loadData({commit,dispatch}) {
        dispatch('getPizzas');
        dispatch('getOptions');
      },
      getPizzas({ commit }) {
      OrderService.getPizzas().then(response => {
        const products = response.data;
    
        this.commit('SET_SPECIALTY_PIZZAS',products
        .filter(product => product.productTypeId === 1));
        this.commit('SET_CUSTOM_PIZZAS',products
        .filter(product => product.productTypeId === 2));
  
      }).catch(error => {
        console.error("Error fetching pizzas:", error);
      });
    },
      getOptions({commit}){
        OrderService.getPizzaOptions().then((response) => {
          const options = response.data;
          this.commit('SET_CRUSTS',options
            .filter(option => option.optionTypeId=== 3));
            this.commit('SET_TOPPINGS',options
            .filter(option => option.optionTypeId=== 1));
            this.commit('SET_SAUCES',options
              .filter(option => option.optionTypeId=== 2));
          })
        .catch(error => {
          console.error('Error fetching pizza options:', error);
        });
    },
    addCustomer({commit}, customer){
      
        AuthService.addCustomer(customer) 
          .then(response => {
            this.commit('SET_CUSTOMER', customer);
          })
          .catch(error => {
            console.log(error);
        }); 
      }
    }
  });
  return store;
}
