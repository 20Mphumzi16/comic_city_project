// src/services/apiService.js
import axios from 'axios';

// Create an Axios instance for API requests
const apiService = axios.create({
    baseURL: 'http://localhost:8080/comiccity', // Backend URL
    timeout: 10000, // Optional: set a timeout for requests
    headers: {
        'Content-Type': 'application/json', // Set default headers
    },
});

// Function to create a billing address
export const createBillingAddress = async (billingAddress) => {
    try {
        const response = await apiService.post('/billing_address/create', billingAddress);
        return response.data; // Return the created billing address data
    } catch (error) {
        console.error('Error creating billing address:', error);
        throw error; // Rethrow the error to handle it in the component
    }
};

// Function to read a billing address by ID
export const readBillingAddress = async (id) => {
    try {
        const response = await apiService.get(`/billing_address/read/${id}`);
        return response.data; // Return the fetched billing address data
    } catch (error) {
        console.error('Error reading billing address:', error);
        throw error; // Rethrow the error to handle it in the component
    }
};

// Function to update a billing address
export const updateBillingAddress = async (billingAddress) => {
    try {
        const response = await apiService.post('/billing_address/update', billingAddress);
        return response.data; // Return the updated billing address data
    } catch (error) {
        console.error('Error updating billing address:', error);
        throw error; // Rethrow the error to handle it in the component
    }
};

// Function to delete a billing address by ID
export const deleteBillingAddress = async (id) => {
    try {
        const response = await apiService.delete(`/billing_address/delete/${id}`);
        return response.data; // Return a boolean indicating success
    } catch (error) {
        console.error('Error deleting billing address:', error);
        throw error; // Rethrow the error to handle it in the component
    }
};

// Function to get all billing addresses
export const getAllBillingAddresses = async () => {
    try {
        const response = await apiService.get('/billing_address/getall');
        return response.data; // Return the list of billing addresses
    } catch (error) {
        console.error('Error fetching all billing addresses:', error);
        throw error; // Rethrow the error to handle it in the component
    }
};

// Export the Axios instance if needed
export default apiService;
