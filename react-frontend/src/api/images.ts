// @ts-ignore
import api from './api.ts';

export const fetchProductImage = async (skuCode: string) => {
    try {
        // Для изображений мы ожидаем blob (бинарные данные)
        const response = await api.get(`/images/products/${skuCode}`, {
            responseType: 'blob'
        });
        return URL.createObjectURL(response.data);
    } catch (error) {
        console.error('Error fetching product image:', error);
        // Можно вернуть URL дефолтного изображения при ошибке
        return '/path/to/default-image.png';
    }
};

export const imagesApi = {
    getProductImage: fetchProductImage
};