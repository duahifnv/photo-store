// @ts-ignore
import api from './api.ts';

export const placeOrder = async (orderItems) => {
    try {
        const response = await api.post('/order', orderItems);
        return response;
    } catch (error) {
        console.error('Error placing order:', error);
        throw error;
    }
};

export const ordersApi = {
    placeOrder
};