
<!--<template>-->
<!--  <div class="addresses-page">-->
<!--    <div class="main-content">-->
<!--      <div class="addresses-header">-->
<!--        <i class="fas fa-map-marker-alt"></i>-->
<!--        <h1>Addresses</h1>-->
<!--      </div>-->
<!--      <p>The following addresses will be used on the checkout page by default</p>-->
<!--      <div class="addresses">-->
<!--        <div class="address-box">-->
<!--          <h2>Billing address</h2>-->
<!--          <div v-if="billingAddress && billingAddress.length">-->
<!--            <div v-for="(address, index) in billingAddress" :key="index" class="billing-address">-->
<!--              <p>{{ address.street }}, {{ address.suburb }}, {{ address.city }}, {{ address.postalCode }}</p>-->
<!--              <p>Payment Method: {{ address.paymentMethod }}</p>-->
<!--            </div>-->
<!--          </div>-->
<!--          <div v-else>-->
<!--            <p>You have not set up this type of address yet</p>-->
<!--            <button class="add-address-button" @click="goToBillingAddress">ADD ADDRESS</button>-->
<!--          </div>-->
<!--        </div>-->
<!--        <div class="address-box">-->
<!--          <h2>Shipping address</h2>-->
<!--          <p>You have not set up this type of address yet</p>-->
<!--          <button class="add-address-button" @click="goToShippingAddress">ADD ADDRESS</button>-->
<!--        </div>-->
<!--      </div>-->
<!--    </div>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import axios from 'axios';-->

<!--export default {-->
<!--  name: 'AddressesPage',-->
<!--  data() {-->
<!--    return {-->
<!--      billingAddress: [] // Changed from null to an empty array to handle multiple addresses-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    goToBillingAddress() {-->
<!--      this.$router.push('/billing-address');-->
<!--    },-->
<!--    goToShippingAddress() {-->
<!--      this.$router.push('/shipping-address');-->
<!--    },-->
<!--    fetchBillingAddress() {-->
<!--      axios.get('/api/comiccity/billing_address/getall')-->
<!--          .then(response => {-->
<!--            // Store the array of billing addresses in the data property-->
<!--            this.billingAddress = response.data;-->
<!--          })-->
<!--          .catch(error => {-->
<!--            console.error('Error fetching billing address:', error.response?.data?.message || error.message);-->
<!--          });-->
<!--    }-->
<!--  },-->
<!--  mounted() {-->
<!--    this.fetchBillingAddress(); // Fetch billing addresses when the component is mounted-->
<!--  }-->
<!--};-->
<!--</script>-->

<!--<style scoped>-->
<!--.addresses-page {-->
<!--  display: flex;-->
<!--  padding: 20px;-->
<!--}-->

<!--.main-content {-->
<!--  width: 75%;-->
<!--  padding-left: 20px;-->
<!--}-->

<!--.addresses-header {-->
<!--  display: flex;-->
<!--  align-items: center;-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.addresses-header i {-->
<!--  font-size: 24px;-->
<!--  margin-right: 10px;-->
<!--}-->

<!--.addresses-header h1 {-->
<!--  font-size: 24px;-->
<!--  margin: 0;-->
<!--}-->

<!--.addresses {-->
<!--  display: flex;-->
<!--  justify-content: space-between;-->
<!--  margin-top: 20px;-->
<!--}-->

<!--.address-box {-->
<!--  width: 45%;-->
<!--  border: 1px solid #ccc;-->
<!--  padding: 20px;-->
<!--  text-align: center;-->
<!--}-->

<!--.address-box h2 {-->
<!--  font-size: 18px;-->
<!--  margin-bottom: 10px;-->
<!--}-->

<!--.address-box p {-->
<!--  margin-bottom: 20px;-->
<!--}-->

<!--.add-address-button {-->
<!--  padding: 10px 20px;-->
<!--  background-color: #000;-->
<!--  color: #fff;-->
<!--  border: none;-->
<!--  border-radius: 5px;-->
<!--  cursor: pointer;-->
<!--}-->

<!--.add-address-button:hover {-->
<!--  background-color: #333;-->
<!--}-->

<!--.billing-address {-->
<!--  margin-bottom: 20px;-->
<!--  text-align: left;-->
<!--}-->

<!--.billing-address p {-->
<!--  margin: 0;-->
<!--}-->
<!--</style>-->
<template>
  <div class="addresses-page">
    <div class="main-content">
      <div class="addresses-header">
        <i class="fas fa-map-marker-alt"></i>
        <h1>Addresses</h1>
      </div>
      <p>The following addresses will be used on the checkout page by default</p>
      <div class="addresses">
        <div class="address-box">
          <h2>Billing address</h2>
          <div v-if="billingAddress && billingAddress.length">
            <div v-for="(address, index) in billingAddress" :key="index" class="billing-address">
              <p>{{ address.street }}, {{ address.suburb }}, {{ address.city }}, {{ address.postalCode }}</p>
              <p>Payment Method: {{ address.paymentMethod }}</p>
            </div>
          </div>
          <div v-else>
            <p>You have not set up this type of address yet</p>
            <button class="add-address-button" @click="goToBillingAddress">ADD ADDRESS</button>
          </div>
        </div>
        <div class="address-box">
          <h2>Shipping address</h2>
          <div v-if="shippingAddress && shippingAddress.length">
            <div v-for="(address, index) in shippingAddress" :key="index" class="shipping-address">
              <p>{{ address.street }}, {{ address.suburb }}, {{ address.city }}, {{ address.postalCode }}</p>
            </div>
          </div>
          <div v-else>
            <p>You have not set up this type of address yet</p>
            <button class="add-address-button" @click="goToShippingAddress">ADD ADDRESS</button>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import axios from 'axios';

export default {
  name: 'AddressesPage',
  data() {
    return {
      billingAddress: [], // Holds the array of billing addresses
      shippingAddress: [] // Holds the array of shipping addresses
    };
  },
  methods: {
    goToBillingAddress() {
      this.$router.push('/billing-address');
    },
    goToShippingAddress() {
      this.$router.push('/shipping-address');
    },
    fetchBillingAddress() {
      axios.get('/api/comiccity/billing_address/getall')
          .then(response => {
            this.billingAddress = response.data;
          })
          .catch(error => {
            console.error('Error fetching billing address:', error.response?.data?.message || error.message);
          });
    },
    fetchShippingAddress() {
      axios.get('/api/comiccity/shipping_address/getall')
          .then(response => {
            this.shippingAddress = response.data;
          })
          .catch(error => {
            console.error('Error fetching shipping address:', error.response?.data?.message || error.message);
          });
    }
  },
  mounted() {
    this.fetchBillingAddress(); // Fetch billing addresses when the component is mounted
    this.fetchShippingAddress(); // Fetch shipping addresses when the component is mounted
  }
};
</script>

<style scoped>
.addresses-page {
  display: flex;
  padding: 20px;
}

.main-content {
  width: 75%;
  padding-left: 20px;
}

.addresses-header {
  display: flex;
  align-items: center;
  margin-bottom: 20px;
}

.addresses-header i {
  font-size: 24px;
  margin-right: 10px;
}

.addresses-header h1 {
  font-size: 24px;
  margin: 0;
}

.addresses {
  display: flex;
  justify-content: space-between;
  margin-top: 20px;
}

.address-box {
  width: 45%;
  border: 1px solid #ccc;
  padding: 20px;
  text-align: center;
}

.address-box h2 {
  font-size: 18px;
  margin-bottom: 10px;
}

.address-box p {
  margin-bottom: 20px;
}

.add-address-button {
  padding: 10px 20px;
  background-color: #000;
  color: #fff;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.add-address-button:hover {
  background-color: #333;
}

.billing-address,
.shipping-address {
  margin-bottom: 20px;
  text-align: left;
}

.billing-address p,
.shipping-address p {
  margin: 0;
}
</style>
