import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { ordersApi } from '../../api/orders.ts';
import '../../styles/page/checkout-page.css';

export const CheckoutPage = () => {
    const [cartItems, setCartItems] = useState([]);
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState('');
    const navigate = useNavigate();

    useEffect(() => {
        // Загрузка корзины из localStorage
        const cart = JSON.parse(localStorage.getItem('cart')) || [];
        setCartItems(cart);
    }, []);

    const formatPrice = (priceString) => {
        return parseFloat(priceString.replace(/\s/g, '').replace('₽', ''));
    };

    const calculateTotal = () => {
        return cartItems.reduce((total, item) => {
            return total + (formatPrice(item.price) * item.quantity);
        }, 0);
    };

    const handlePlaceOrder = async () => {
        setLoading(true);
        setError('');

        try {
            // Подготовка данных для заказа
            const orderItems = cartItems.map(item => ({
                skuCode: item.skuCode,
                price: formatPrice(item.price),
                quantity: item.quantity
            }));

            // Отправка заказа
            const response = await ordersApi.placeOrder(orderItems);

            if (response.status === 201) {
                // Очищаем корзину
                localStorage.removeItem('cart');
                // Триггерим событие для обновления других компонентов
                window.dispatchEvent(new Event('storage'));

                // Показываем сообщение и редиректим
                alert('Заказ успешно оформлен!');
                navigate('/');
            } else {
                throw new Error('Ошибка при оформлении заказа');
            }
        } catch (err) {
            setError(err.message || 'Произошла ошибка при оформлении заказа');
        } finally {
            setLoading(false);
        }
    };

    if (cartItems.length === 0) {
        return (
            <div className="checkout-container">
                <h2>Оформление заказа</h2>
                <div className="empty-cart-message">
                    Ваша корзина пуста
                </div>
            </div>
        );
    }

    return (
        <>
            <div id="page__content">
                <div id="welcome-screen">
                    <div className="checkout-container">
                        <h2>Оформление заказа</h2>
                        <div className="cart-items">
                            {cartItems.map(item => (
                                <div key={item.skuCode} className="cart-item">
                                    <div className="item-image">
                                        <img src={item.imgSrc} alt={item.skuCode} />
                                    </div>
                                    <div className="item-details">
                                        <h3>{item.name}</h3>
                                        <p>Цена: <code>{formatPrice(item.price)} ₽</code></p>
                                        <p>Количество: <code>{item.quantity}</code></p>
                                        <p>Сумма: <b><code>{formatPrice(item.price) * item.quantity} ₽</code></b></p>
                                    </div>
                                </div>
                            ))}
                        </div>

                        <div className="order-summary">
                            <h2>Итого: <b><i>{calculateTotal()} ₽</i></b></h2>
                            <button
                                onClick={handlePlaceOrder}
                                disabled={loading}
                                className="place-order-btn"
                            >
                                {loading ? 'Оформление...' : 'Оформить заказ'}
                            </button>
                            {error && <div className="error-message">{error}</div>}
                        </div>
                    </div>
                </div>
            </div>

        </>
    );
};