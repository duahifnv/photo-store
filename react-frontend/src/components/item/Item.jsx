import { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthProvider.tsx';
import '../../styles/item/item.css';

export const Item = (props) => {
    const { isAuthenticated } = useAuth();
    const [quantity, setQuantity] = useState(0);
    const [isFavorite, setIsFavorite] = useState(false);

    // Загрузка состояния корзины и избранного
    useEffect(() => {
        // Проверка корзины
        const cart = JSON.parse(localStorage.getItem('cart')) || [];
        const cartItem = cart.find(item => item.skuCode === props.imgAlt);
        if (cartItem) setQuantity(cartItem.quantity);

        // Проверка избранного
        const favorites = JSON.parse(localStorage.getItem('favorites')) || [];
        setIsFavorite(favorites.some(item => item.skuCode === props.imgAlt));
    }, [props.imgAlt]);

    // Функции для работы с корзиной
    const updateCart = (newQuantity) => {
        const cart = JSON.parse(localStorage.getItem('cart')) || [];
        let updatedCart;

        if (newQuantity <= 0) {
            updatedCart = cart.filter(item => item.skuCode !== props.imgAlt);
        } else {
            const existingItemIndex = cart.findIndex(item => item.skuCode === props.imgAlt);

            if (existingItemIndex >= 0) {
                updatedCart = [...cart];
                updatedCart[existingItemIndex].quantity = newQuantity;
            } else {
                updatedCart = [
                    ...cart,
                    {
                        skuCode: props.imgAlt,
                        name: props.label,
                        price: props.price,
                        quantity: newQuantity,
                        imgSrc: props.imgSrc
                    }
                ];
            }
        }

        localStorage.setItem('cart', JSON.stringify(updatedCart));
        setQuantity(newQuantity);
        window.dispatchEvent(new Event('storage'));
    };

    const handleAddToCart = () => {
        if (!isAuthenticated) return;
        updateCart(1);
    };

    const handleIncrease = () => updateCart(quantity + 1);
    const handleDecrease = () => updateCart(quantity - 1);

    // Функции для работы с избранным
    const toggleFavorite = () => {
        const favorites = JSON.parse(localStorage.getItem('favorites')) || [];

        if (isFavorite) {
            const updatedFavorites = favorites.filter(item => item.skuCode !== props.imgAlt);
            localStorage.setItem('favorites', JSON.stringify(updatedFavorites));
        } else {
            const newFavorite = {
                skuCode: props.imgAlt,
                name: props.label,
                price: props.price,
                imgSrc: props.imgSrc,
                properties: props.itemProps
            };
            localStorage.setItem('favorites', JSON.stringify([...favorites, newFavorite]));
        }

        setIsFavorite(!isFavorite);
        window.dispatchEvent(new Event('favorites-updated'));
    };

    return (
        <div id="item-wrapper">
            <button
                className={`favorite-btn ${isFavorite ? 'favorite-active' : ''}`}
                onClick={toggleFavorite}
                aria-label={isFavorite ? "Удалить из избранного" : "Добавить в избранное"}
            >
                ♥
            </button>

            <div id="item-img">
                <img src={props.imgSrc} alt={props.imgAlt}/>
            </div>

            <div id="item-info">
                <p id="item-name">{props.label}</p>
                <div id="item-props">
                    {Object.entries(props.itemProps).map(([propKey, propValue]) => (
                        <p key={propKey}><b>{propKey}:</b> <code>{propValue}</code></p>
                    ))}
                </div>

                <div id="item-price">
                    <p id="current-price">{props.price}</p>
                    {quantity === 0 ? (
                        <button
                            onClick={handleAddToCart}
                            className="add-to-cart-btn"
                            disabled={!isAuthenticated}
                            title={!isAuthenticated ? "Для добавления в корзину необходимо авторизоваться" : ""}
                        >
                            Добавить в корзину
                        </button>
                    ) : (
                        <div className="quantity-controls">
                            <button onClick={handleDecrease}>-</button>
                            <span>{quantity}</span>
                            <button onClick={handleIncrease}>+</button>
                        </div>
                    )}
                </div>
            </div>
        </div>
    );
};