import React, { useState, useEffect } from 'react';
import { Item } from '../item/Item';
import '../../styles/page/category-page.css';

export const FavoritesPage = () => {
    const [favorites, setFavorites] = useState([]);
    const [loading, setLoading] = useState(true);

    useEffect(() => {
        const loadFavorites = () => {
            const favoritesData = JSON.parse(localStorage.getItem('favorites')) || [];
            setFavorites(favoritesData);
            setLoading(false);
        };

        loadFavorites();

        const handleStorageChange = () => {
            loadFavorites();
        };

        window.addEventListener('favorites-updated', handleStorageChange);
        return () => window.removeEventListener('favorites-updated', handleStorageChange);
    }, []);

    if (loading) return <div>Загрузка...</div>;
    if (favorites.length === 0) return <div>Нет товаров в избранном</div>;

    return (
        <>
            <div id="welcome-screen">
                <p id="welcome-text">Избранные товары</p>
            </div>
            <div id="main-content">
                <div id="catalog-wrapper">
                    {favorites.map(product => (
                        <Item
                            key={product.skuCode}
                            label={product.name}
                            price={product.price}
                            itemProps={product.properties}
                            imgSrc={product.imgSrc}
                            imgAlt={product.skuCode}
                        />
                    ))}
                </div>
            </div>
        </>
    );
};