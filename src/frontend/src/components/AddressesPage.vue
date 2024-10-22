<template>
  <div class="addresses-page">
    <div class="main-content">
      <div class="addresses-header">
        <i class="fas fa-map-marker-alt"></i>
        <h1>Addresses</h1>
      </div>
      <p>The following address will be used on the checkout page by default</p>
      <div class="addresses">
        <div class="address-box">
          <h2>Billing address</h2>
          <div v-if="billingAddress">
            <p>{{ billingAddress.streetAddress }}, {{ billingAddress.suburb }}, {{ billingAddress.city }}, {{ billingAddress.postalCode }}</p>
            <p>Payment Method: {{ billingAddress.paymentMethod }}</p>
          </div>
          <div v-else>
            <p>You have not set up this type of address yet</p>
            <button class="add-address-button" @click="openModal">ADD ADDRESS</button>
          </div>
        </div>
      </div>
      <billing-address-modal
          :isVisible="isModalVisible"
          @close="isModalVisible = false"
          @address-added="fetchBillingAddress"
      />
    </div>
  </div>
</template>

<script>
import axios from 'axios';
import BillingAddressModal from './BillingAddressModal.vue';

export default {
  name: 'AddressesPage',
  components: {
    BillingAddressModal,
  },
  data() {
    return {
      billingAddress: null, // This will store the fetched billing address
      isModalVisible: false, // State to control modal visibility
    };
  },
  methods: {
    openModal() {
      this.isModalVisible = true; // Show the modal
    },
    fetchBillingAddress() {
      // Fetch the billing address from the backend
      axios.get('/billing_address/get')
          .then(response => {
            this.billingAddress = response.data; // Assuming response contains a single billing address
          })
          .catch(error => {
            console.error('Error fetching billing address:', error);
          });
    }
  },
  mounted() {
    this.fetchBillingAddress(); // Fetch billing address when the page loads
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
  margin-top: 20px;
}

.address-box {
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
  background-color: #ccc;
  border: none;
  border-radius: 5px;
  cursor: pointer;
}

.add-address-button:hover {
  background-color: #bbb;
}
</style>
