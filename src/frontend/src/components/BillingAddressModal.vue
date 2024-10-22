<template>
  <div class="modal" v-if="isVisible">
    <div class="modal-content">
      <span class="close" @click="closeModal">&times;</span>
      <h2>Add Billing Address</h2>
      <form @submit.prevent="submitBillingAddress">
        <div class="form-group">
          <label for="streetAddress">Street Address:</label>
          <input type="text" v-model="billingAddress.streetAddress" required />
        </div>
        <div class="form-group">
          <label for="suburb">Suburb:</label>
          <input type="text" v-model="billingAddress.suburb" required />
        </div>
        <div class="form-group">
          <label for="city">City:</label>
          <input type="text" v-model="billingAddress.city" required />
        </div>
        <div class="form-group">
          <label for="postalCode">Postal Code:</label>
          <input type="text" v-model="billingAddress.postalCode" required />
        </div>
        <div class="form-group">
          <label for="postalCode">payment Method:</label>
          <input type="text" v-model="billingAddress.paymentMethod" required />
        </div>
        <button type="submit">Create Address</button>
      </form>
    </div>
  </div>
</template>

<script>
import { createBillingAddress} from '@/services/billingAddressService'; // Adjust the path as necessary

export default {
  props: {
    isVisible: {
      type: Boolean,
      required: true,
    },
  },
  data() {
    return {
      billingAddress: {
        streetAddress: '',
        suburb: '',
        city: '',
        postalCode: '',
        paymentMethod:''
      },
    };
  },
  methods: {
    closeModal() {
      this.$emit('close');
    },
    async submitBillingAddress() {
      try {
        const createdAddress = await createBillingAddress(this.billingAddress);
        console.log('Billing address created:', createdAddress);
        alert("address successfully added")
        this.closeModal();
        this.$emit('address-added'); // Notify parent to refresh address list
      } catch (error) {
        console.error('Failed to create billing address:', error);
      }
    },
  },
};
</script>

<style scoped>
.modal {
  display: flex;
  justify-content: center;
  align-items: center;
  position: fixed;
  z-index: 1000;
  left: 0;
  top: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
}

.modal-content {
  background-color: white;
  padding: 20px;
  border-radius: 5px;
  width: 400px; /* Set a fixed width for the modal */
  max-width: 90%; /* Ensure it doesn't exceed the viewport width */
  box-shadow: 0 2px 10px rgba(0, 0, 0, 0.2); /* Optional shadow for better visibility */
}

.close {
  cursor: pointer;
  font-size: 24px;
  float: right; /* Align close button to the right */
}

.form-group {
  margin-bottom: 15px; /* Add some space between the fields */
}

.form-group label {
  display: block; /* Labels on their own line */
  margin-bottom: 5px; /* Space between label and input */
}

.form-group input {
  width: 100%; /* Make inputs take full width */
  padding: 8px; /* Padding inside inputs */
  border: 1px solid #ccc; /* Border for inputs */
  border-radius: 4px; /* Rounded corners for inputs */
}
</style>
