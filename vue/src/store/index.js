import { createStore as _createStore } from 'vuex';
import axios from 'axios';
import OrderService from '../services/OrderService';
import AuthService from '../services/AuthService';
import AdminService from '../services/AdminService';

export function createStore(currentToken, currentUser) {
  let store = _createStore({
    state: {
      token: currentToken || '',
      user: currentUser || {},
      customer: {},
      order: [],
      adminOrders: [],
      orderStatuses: [],
      drivers: [],
      crusts: [],
      toppings: [],
      premiumToppings:[],
      sauces: [],
      specialtyPizzas: [],
      customPizzas: [],
      products:[],
      options:[],
      orderDetails:{},
      currentOrderDriver: {},
      currentOrderStatus: {}
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
        state.toppings = toppings;
      },
      SET_PREMIUM_TOPPINGS(state, toppings){
        state.premiumToppings = toppings;
      },
      SET_SAUCES(state, sauces){
        state.sauces = sauces;
      },
      SET_SPECIALTY_PIZZAS(state, specialtyPizzas){
        state.specialtyPizzas = specialtyPizzas;
      },
      SET_CUSTOM_PIZZAS(state, customPizzas){
        state.customPizzas = customPizzas;
      },
      SET_ADMIN_ORDERS(state, adminOrders){
        state.adminOrders = adminOrders;
      },
      SET_DRIVERS(state, drivers){
        state.drivers = drivers;
      },
      SET_CURRENT_ORDER_DRIVER(state, currentOrderDriver){
        state.currentOrderDriver = currentOrderDriver;
      },
      SET_CURRENT_ORDER_STATUS(state, currentOrderStatus){
        state.currentOrderStatus = currentOrderStatus;
      },
      SET_ORDER_STATUSES(state, orderStatuses){
        state.orderStatuses = orderStatuses;
      },
      SET_ORDER_DETAILS(state,orderDetails){
        state.orderDetails=orderDetails;
      },
      LOGOUT(state) {
        localStorage.removeItem('token');
        localStorage.removeItem('user');
        state.token = '';
        state.user = {};
        axios.defaults.headers.common = {};
      },
      SET_ORDER(state,order){
        state.order=order;
      },
      SET_PRODUCTS(state,products){
        state.products=products;
      },
      SET_OPTIONS(state,options){
        state.options=options;
      }
    },
    actions:{
      loadData({commit,dispatch}) {
        dispatch('getPizzas');
        dispatch('getOptions');
        
      },
      getPizzas({ commit }) {
      OrderService.getPizzas()
      .then(response => {
        const products = response.data;
        this.commit('SET_PRODUCTS',products);
        this.commit('SET_SPECIALTY_PIZZAS',products
        .filter(product => product.productTypeId === 1));
        this.commit('SET_CUSTOM_PIZZAS',products
        .filter(product => product.productTypeId === 2));
  
      }).catch(error => {
        console.error("Error fetching pizzas:", error);
      });
    },
      getOptions({commit}){
        OrderService.getPizzaOptions()
        .then((response) => {
          const options = response.data;
          this.commit('SET_OPTIONS',options);
          this.commit('SET_CRUSTS',options
            .filter(option => option.optionTypeId=== 3));
            this.commit('SET_PREMIUM_TOPPINGS',options
            .filter(option => option.optionTypeId=== 4));
            this.commit('SET_TOPPINGS',options
            .filter(option => option.optionTypeId=== 1));
            this.commit('SET_SAUCES',options
              .filter(option => option.optionTypeId=== 2));
          })
        .catch(error => {
          console.error('Error fetching pizza options:', error);
        });
    },
    getAdminOrders({commit}){
      AdminService.getOrders()
      .then((response) => {
        const adminOrders = response.data;
        this.commit('SET_ADMIN_ORDERS', adminOrders);
      })
      .catch(error => {
        console.error('Error fetching Admin Orders:', error);
      });
  },
  getOrderStatuses({commit}){
    AdminService.getStatuses()
    .then((response) => {
      const orderStatuses = response.data;
      this.commit('SET_ORDER_STATUSES', orderStatuses);
    })
    .catch(error => {
      console.error('Error fetching list of status types:', error);
    });
},
  getDrivers({commit}){
    AdminService.getDrivers()
      .then((response) => {
      const drivers = response.data;
      this.commit('SET_DRIVERS', drivers);
      })
      .catch(error => {
      console.error('Error fetching all Drivers:', error);
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
      },
    createCustomerOrder({commit}){
      OrderService.customerOrder(this.state.order)
      .then(response=>{
        this.commit('SET_ORDER_DETAILS',response.data);
      })
      .catch(error => {
        console.log(error);
    });
    }}
  });
  return store;
}
