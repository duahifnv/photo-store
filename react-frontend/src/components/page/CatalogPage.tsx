// @ts-ignore
import React, { useState, useEffect } from 'react';
import { Item } from '../item/Item';
// @ts-ignore
import { productsApi } from '../../api/products.ts';
// @ts-ignore
import { imagesApi } from '../../api/images.ts';
import '../../styles/page/category-page.css';
import '../../styles/page/catalog-page.css';

export const CatalogPage = () => {
    const [allProducts, setAllProducts] = useState([]);
    const [filteredProducts, setFilteredProducts] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [images, setImages] = useState({});

    // Состояния для фильтров
    const [minPrice, setMinPrice] = useState('');
    const [maxPrice, setMaxPrice] = useState('');
    const [sortOrder, setSortOrder] = useState<'asc' | 'desc'>('asc');

    useEffect(() => {
        const loadProducts = async () => {
            try {
                const productsData = await productsApi.getAll(); // Используем метод получения всех товаров
                setAllProducts(productsData);
                setFilteredProducts(productsData);
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
    }, []);

    // Применение фильтров и сортировки
    useEffect(() => {
        let result = [...allProducts];

        // Фильтрация по цене
        if (minPrice) {
            result = result.filter(product => product.price >= Number(minPrice));
        }
        if (maxPrice) {
            result = result.filter(product => product.price <= Number(maxPrice));
        }

        // Сортировка
        result.sort((a, b) => {
            return sortOrder === 'asc' ? a.price - b.price : b.price - a.price;
        });

        setFilteredProducts(result);
    }, [allProducts, minPrice, maxPrice, sortOrder]);

    const formatPrice = (price) => {
        return new Intl.NumberFormat('ru-RU', {
            style: 'currency',
            currency: 'RUB',
            maximumFractionDigits: 0
        }).format(price).replace('₽', '₽');
    };

    const handleSortChange = (order: 'asc' | 'desc') => {
        setSortOrder(order);
    };

    if (loading) return <div>Загрузка...</div>;
    if (error) return <div>Ошибка: {error}</div>;

    return (
        <>
            <div id="welcome-screen">
                <p id="welcome-text">Каталог товаров</p>
            </div>

            <div id="filters-container">
                <div className="price-filter">
                    <label>
                        Минимальная цена:
                        <input
                            type="number"
                            value={minPrice}
                            onChange={(e) => setMinPrice(e.target.value)}
                            placeholder="От"
                            min="0"
                        />
                    </label>

                    <label>
                        Максимальная цена:
                        <input
                            type="number"
                            value={maxPrice}
                            onChange={(e) => setMaxPrice(e.target.value)}
                            placeholder="До"
                            min="0"
                        />
                    </label>
                </div>

                <div className="sort-options">
                    <span>Сортировка: </span>
                    <button
                        className={sortOrder === 'asc' ? 'active' : ''}
                        onClick={() => handleSortChange('asc')}
                    >
                        По возрастанию цены
                    </button>
                    <button
                        className={sortOrder === 'desc' ? 'active' : ''}
                        onClick={() => handleSortChange('desc')}
                    >
                        По убыванию цены
                    </button>
                </div>
            </div>

            <div id="main-content">
                <div id="catalog-wrapper">
                    {filteredProducts.map(product => (
                        <Item
                            key={product.skuCode}
                            label={product.name}
                            releaseDate={product.releaseDate}
                            price={formatPrice(product.price)}
                            itemProps={product.properties}
                            imgSrc={images[product.skuCode]}
                            imgAlt={product.skuCode}
                        />
                    ))}
                </div>
            </div>
        </>
    );
};