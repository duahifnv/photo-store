import { useState, useEffect } from 'react';
import { useAuth } from '../context/AuthProvider.tsx';
import '../../styles/item/item.css';

export const Item = (props) => {
    const { isAuthenticated } = useAuth();
    const [quantity, setQuantity] = useState(0);

    useEffect(() => {
        // Проверяем есть ли товар в корзине при загрузке компонента
        const cart = JSON.parse(localStorage.getItem('cart')) || [];
        const cartItem = cart.find(item => item.skuCode === props.imgAlt); // imgAlt содержит skuCode

        if (cartItem) {
            setQuantity(cartItem.quantity);
        }
    }, [props.imgAlt]);

    const updateCart = (newQuantity) => {
        const cart = JSON.parse(localStorage.getItem('cart')) || [];
        let updatedCart;

        if (newQuantity <= 0) {
            // Удаляем товар из корзины
            updatedCart = cart.filter(item => item.skuCode !== props.imgAlt);
        } else {
            // Ищем товар в корзине
            const existingItemIndex = cart.findIndex(item => item.skuCode === props.imgAlt);

            if (existingItemIndex >= 0) {
                // Обновляем количество
                updatedCart = [...cart];
                updatedCart[existingItemIndex].quantity = newQuantity;
            } else {
                // Добавляем новый товар
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
        // Триггерим событие для обновления других компонентов
        window.dispatchEvent(new Event('storage'));
    };

    const handleAddToCart = () => {
        if (!isAuthenticated) return;
        updateCart(1);
    };

    const handleIncrease = () => {
        updateCart(quantity + 1);
    };

    const handleDecrease = () => {
        updateCart(quantity - 1);
    };

    return (
        <div id="item-wrapper">
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