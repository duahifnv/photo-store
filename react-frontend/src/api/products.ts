// @ts-ignore
import api from './api.ts';

export const fetchProductsByCategory = async (categoryCode: string) => {
    try {
        const response = await api.get(`/inventory/products?categoryCode=${categoryCode}`);
        return response.data;
    } catch (error) {
        console.error('Error fetching products:', error);
        throw error;
    }
};

export const productsApi = {
    getByCategory: fetchProductsByCategory
};