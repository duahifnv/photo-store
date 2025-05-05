import { Link } from "react-router-dom";
import { useState, useEffect } from 'react';

import navDropdownIcon from "../../assets/icons/camera-icon.png";
import profileIcon from "../../assets/icons/profile-icon.png";
import cartIcon from "../../assets/icons/cart-icon.png";

import logo from "../../assets/logos/photostore_logo.png";
import "../../styles/page/header.css";

import {useAuth} from "../context/AuthProvider.tsx";

export const Header = () => {
    const { isAuthenticated } = useAuth();
    const [cartItemsCount, setCartItemsCount] = useState(0);

    useEffect(() => {
        // Загрузка корзины из localStorage
        const cart = JSON.parse(localStorage.getItem('cart')) || [];
        setCartItemsCount(cart.length);

        // Подписка на изменения корзины
        const handleStorageChange = () => {
            const updatedCart = JSON.parse(localStorage.getItem('cart')) || [];
            setCartItemsCount(updatedCart.length);
        };

        window.addEventListener('storage', handleStorageChange);
        return () => window.removeEventListener('storage', handleStorageChange);
    }, []);

    return (
        <div id="page__header">
            <div id="logo__inner">
                <div id="dropdown">
                    <button>
                        <img src={navDropdownIcon} alt="Меню"/>
                    </button>
                    <div id="nav-content">
                        <ul id="nav-list">
                            <li><Link to={"/"}>Главная страница</Link></li>
                            <li><Link to={"/catalog"}>Каталог</Link>
                                <ul>
                                    <li><Link to={"/cameras"}>Фотоаппараты</Link></li>
                                    <li><Link to={"/lens"}>Объективы</Link></li>
                                    <li><Link to={"/flashes"}>Вспышки</Link></li>
                                    <li><Link to={"/optical"}>Оптические приборы</Link></li>
                                </ul>
                            </li>
                            <li><Link to={"/about"}>Об авторе</Link></li>
                            <li><Link to={"/company"}>О фирме</Link></li>
                        </ul>
                        <div id="nav-blackout"></div>
                    </div>
                </div>
                <div id="logo">
                    <img src={logo} alt={"photoStore"}/>
                </div>
            </div>
            <form id="search-bar" action="" method="get">
                <input id="search" type="text" name="text" placeholder="Выбрать товар" />
                <input id="submit" type="submit" name="submit" value="Поиск"/>
            </form>
            <div id="nav">
                {isAuthenticated && (
                    <div className="cart-icon-container">
                        <Link to="/checkout">
                            <img src={cartIcon} alt="Корзина" className="nav-icon"/>
                            {cartItemsCount > 0 && (
                                <span className="cart-badge">{cartItemsCount}</span>
                            )}
                        </Link>
                    </div>
                )}
                <Link to={isAuthenticated ? "/profile" : "/auth"} className="auth-link">
                    {isAuthenticated ? (
                        <img src={profileIcon} alt="Профиль" className="nav-icon"/>
                    ) : (
                        "Войти"
                    )}
                </Link>
            </div>
        </div>
    )
}