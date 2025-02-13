
<!--<template>-->
<!--  <div class="orders-page">-->
<!--    <div class="main-content">-->
<!--      <div class="orders-header">-->
<!--        <font-awesome-icon :icon="['fas', 'box-open']" />-->
<!--        <h1>Orders</h1>-->
<!--      </div>-->
<!--      <table class="orders-table" v-if="orders.length > 0">-->
<!--        <thead>-->
<!--        <tr>-->
<!--          <th>Order</th>-->
<!--          <th>Date</th>-->
<!--          <th>Items</th>-->
<!--          <th>Total</th>-->
<!--        </tr>-->
<!--        </thead>-->
<!--        <tbody>-->
<!--        <tr v-for="(order, index) in orders" :key="index">-->
<!--          <td>{{ order.id }}</td>-->
<!--          <td>{{ formatDate(order.date) }}</td>-->
<!--          <td>{{ order.items.length }}</td>-->
<!--          <td>{{ formatCurrency(order.total) }}</td>-->
<!--        </tr>-->
<!--        </tbody>-->
<!--      </table>-->
<!--      <div v-else>-->
<!--        <div class="no-orders">-->
<!--          <font-awesome-icon :icon="['fas', 'exclamation-circle']" />-->
<!--          <span>No order has been made yet</span>-->
<!--        </div>-->
<!--        <button class="shop-button" @click="goToShop">Browse and shop for comics</button>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import axios from 'axios';-->

<!--export default {-->
<!--  name: 'OrdersPage',-->
<!--  data() {-->
<!--    return {-->
<!--      orders: [] // Array to store orders fetched from the API-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    fetchOrders() {-->
<!--      axios.get('/CustomerOrder')-->
<!--          .then(response => {-->
<!--            this.orders = response.data;-->
<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error fetching orders:', error.response?.data?.message || error.message);-->
<!--          });-->
<!--    },-->
<!--    formatDate(date) {-->
<!--      const options = { year: 'numeric', month: 'long', day: 'numeric' };-->
<!--      return new Date(date).toLocaleDateString(undefined, options);-->
<!--    },-->
<!--    formatCurrency(amount) {-->
<!--      return new Intl.NumberFormat('en-ZA', {-->
<!--        style: 'currency',-->
<!--        currency: 'ZAR'-->
<!--      }).format(amount);-->
<!--    },-->
<!--    goToShop() {-->
<!--      this.$router.push('/shop');-->
<!--    }-->
<!--  },-->
<!--  mounted() {-->
<!--    this.fetchOrders(); // Fetch orders when the component is mounted-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.orders-page {-->
<!--  display: flex;-->
<!--  padding: 20px;-->
<!--}-->

<!--.main-content {-->
<!--  width: 100%;-->
<!--  padding-left: 20px;-->
<!--}-->

<!--.orders-header {-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.orders-header i {-->
<!--  font-size: 24px;-->
<!--  margin-right: 10px;-->
<!--}-->

<!--.orders-header h1 {-->
<!--  font-size: 24px;-->
<!--  margin: 0;-->
<!--}-->

<!--.orders-table {-->
<!--  width: 100%;-->
<!--  border-collapse: collapse;-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.orders-table th, .orders-table td {-->
<!--  border-bottom: 1px solid #ccc;-->
<!--  padding: 10px;-->
<!--  text-align: left;-->
<!--}-->

<!--.no-orders {-->
<!--  text-align: center;-->
<!--  padding: 20px;-->
<!--}-->

<!--.no-orders i {-->
<!--  font-size: 24px;-->
<!--  display: block;-->
<!--  margin-bottom: 10px;-->
<!--}-->

<!--.shop-button {-->
<!--  display: block;-->
<!--  margin: 0 auto;-->
<!--  padding: 10px 20px;-->
<!--  background-color: #000;-->
<!--  color: #fff;-->
<!--  border: none;-->
<!--  border-radius: 5px;-->
<!--  cursor: pointer;-->
<!--}-->

<!--.shop-button:hover {-->
<!--  background-color: #444;-->
<!--}-->
<!--</style>-->
<template>
  <div class="orders-page">
    <div class="main-content">
      <div class="orders-header">
        <font-awesome-icon :icon="['fas', 'box-open']" />
        <h1>Orders</h1>
      </div>
      <table class="orders-table" v-if="orders.length > 0">
        <thead>
        <tr>
          <th>Order</th>
          <th>Date</th>
          <th>Items</th>
          <th>Total</th>
        </tr>
        </thead>
        <tbody>
        <tr v-for="(order, index) in orders" :key="index">
          <td>{{ order.id }}</td>
          <td>{{ formatDate(order.orderDate) }}</td>
          <td>{{ order.comicBooks.length }}</td>
          <td>{{ formatCurrency(order.totalAmount) }}</td>
        </tr>
        </tbody>
      </table>
      <div v-else>
        <div class="no-orders">
          <font-awesome-icon :icon="['fas', 'exclamation-circle']" />
          <span>No order has been made yet</span>
        </div>
        <button class="shop-button" @click="goToShop">Browse and shop for comics</button>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'OrdersPage',
  data() {
    return {
      orders: [] // Array to store orders fetched from the API
    };
  },
  methods: {
    fetchOrders() {
      axios.get('/CustomerOrder/getAll')
          .then(response => {
            this.orders = response.data; // Set the fetched orders to the orders array
          })
          .catch(error => {
            console.error('Error fetching orders:', error.response?.data?.message || error.message);
          });
    },
    formatDate(date) {
      const options = { year: 'numeric', month: 'long', day: 'numeric' };
      return new Date(date).toLocaleDateString(undefined, options);
    },
    formatCurrency(amount) {
      return new Intl.NumberFormat('en-ZA', {
        style: 'currency',
        currency: 'ZAR'
      }).format(amount);
    },
    goToShop() {
      this.$router.push('/shop');
    }
  },
  mounted() {
    this.fetchOrders(); // Fetch orders when the component is mounted
  }
};
</script>

<style scoped>
.orders-page {
  display: flex;
  padding: 20px;
}

.main-content {
  width: 100%;
  padding-left: 20px;
}

.orders-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.orders-header i {
  font-size: 24px;
  margin-right: 10px;
}

.orders-header h1 {
  font-size: 24px;
  margin: 0;
}

.orders-table {
  width: 100%;
  border-collapse: collapse;
  margin-bottom: 20px;
}

.orders-table th, .orders-table td {
  border-bottom: 1px solid #ccc;
  padding: 10px;
  text-align: left;
}

.no-orders {
  text-align: center;
  padding: 20px;
}

.no-orders i {
  font-size: 24px;
  display: block;
  margin-bottom: 10px;
}

.shop-button {
  display: block;
  margin: 0 auto;
  padding: 10px 20px;
  background-color: #000;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.shop-button:hover {
  background-color: #444;
}
</style>
