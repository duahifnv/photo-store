import React, { useState, useEffect } from 'react';
import { Item } from '../item/Item';
import { productsApi } from '../../api/products.ts';
import { imagesApi } from '../../api/images.ts';
import '../../styles/page/category-page.css';

export const CategoryPage = (props) => {
    const [products, setProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [images, setImages] = useState({});

    useEffect(() => {
        const loadProducts = async () => {
            try {
                const productsData = await productsApi.getByCategory(props.categoryCode);
                setProducts(productsData);
                setLoading(false);

                // Загружаем изображения параллельно после отображения товаров
                for (const product of productsData) {
                    const imageUrl = await imagesApi.getProductImage(product.skuCode);
                    setImages(prev => ({ ...prev, [product.skuCode]: imageUrl }));
                }
            } catch (err) {
                setError(err.message);
                setLoading(false);
            }
        };

        loadProducts();
    }, [props.categoryCode]);

    const formatPrice = (price) => {
        return new Intl.NumberFormat('ru-RU', {
            style: 'currency',
            currency: 'RUB',
            maximumFractionDigits: 0
        }).format(price).replace('₽', '₽');
    };

    if (loading) return <div>Загрузка...</div>;
    if (error) return <div>Ошибка: {error}</div>;

    return (
        <>
            <div id="welcome-screen">
                <p id="welcome-text">{props.label}</p>
            </div>
            <div id="main-content">
                <div id="catalog-wrapper">
                    {products.map(product => (
                        <Item
                            key={product.skuCode}
                            label={product.name}
                            itemProps={[
                                `Цена: ${formatPrice(product.price)}`,
                                `Дата выхода: ${product.releaseDate}`
                            ]}
                            price={formatPrice(product.price)}
                            prevPrice={formatPrice(product.price)}
                            imgSrc={images[product.skuCode]}
                            imgAlt={product.skuCode}
                        />
                    ))}
                </div>
            </div>
        </>
    );
};