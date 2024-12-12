import axios from 'axios';

const userToken = localStorage.getItem('token')

const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API,
  headers: {Authorization: `Bearer ${userToken}`}
});

export default {
  // Fetch all orders (admin view)
  getOrders() {
    return http.get('/admin/orders');
  },

  // Fetch all drivers (admin view)
  getDrivers() {
    return http.get('/admin/drivers');
  },

  getStatuses() {
    return http.get('/admin/statuses');
  },

  getOrderById(id) {
    return http.get(`/orders/${id}`)
  },

  getStatusByStatusId(orderStatusDto) {
    return http.get('admin/status/', orderStatusDto);
  },

  // Update order status
  updateOrderStatus(orderStatusDto) {
    return http.put('/admin/order/status', orderStatusDto);
  },

  // Assign driver to an order
  assignDriver(orderDriverDto) {
    return http.put('/admin/driver', orderDriverDto);
  },

  //Get list of All Products Available or Otherwise
  Products() {
    return http.get('/admin/products')
  },

  // Change product availability
  updateProductAvailability(productAvailableDto) {
    return http.put(`/admin/products/availability`, productAvailableDto);
  },

  

  // Add a new product (e.g., specialty pizza)
  createProduct(product) {
    return http.post(`/admin/products`, product);
  },

  //Get list of All Options Available or Otherwise
  Options() {
    return http.get('/admin/options')
  },

  // Change availability of product options
  updateProductOptionAvailability(productOptionAvailableDto) {
    return http.put(`/admin/options/availability`,productOptionAvailableDto);
  },

  // Add a new product option (e.g., crust, topping, sauce)
  createProductOption(productOption) {
    return http.post(`/admin/options`, productOption);
  },

  // Fetch pending orders
  getPendingOrders() {
    return http.get(`/admin/orders?status=Pending`);
  },

  // Get type information based on table in database: option_type, product_type, size (Stretch Goal)
  // Historical report of orders (Planned Feature)
  // Search for orders (Planned Feature)
};
