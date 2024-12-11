import axios from 'axios';

// Set up axios instance with the base URL
const http = axios.create({
  baseURL: import.meta.env.VITE_REMOTE_API
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

  // Update order status
  updateOrderStatus(orderId, status) {
    return http.put(`/admin/orders/${orderId}/status`, { status });
  },

  // Assign driver to an order
  assignDriver(orderId, driverId) {
    return http.put(`/admin/orders/${orderId}/driver`, { driverId });
  },

  //Get list of All Products Available or Otherwise
  Products() {
    return http.get('/admin/products')
  },

  // Change product availability
  updateProductAvailability(productId, availability) {
    return http.put(`/admin/products/availability`, {
      productId,
      availability,
    });
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
  updateProductOptionAvailability(optionId, availability) {
    return http.put(`/admin/options/availability`, {
      optionId,
      availability,
    });
  },

  // Add a new product option (e.g., crust, topping, sauce)
  createProductOption(productOption) {
    return http.post(`/admin/options`, productOption);
  },

  // Fetch pending orders
  getPendingOrders() {
    return http.get(`/admin/orders?status=Pending`);
  },

  // Historical report of orders (Planned Feature)
  // Search for orders (Planned Feature)
};
