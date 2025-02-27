import "../styles/header.css"
import dropdown_button from "../assets/ui/nav_dropdown_button.png"
import logo from "../assets/logo.png"

export default function Header() {
    return (
        <div id="page__header">
            <div id="logo__inner">
                <div id="dropdown">
                    <button>
                        <img src={dropdown_button} alt="Меню"/>
                    </button>
                    <div id="nav-content">
                        <ul id="nav-list">
                            <li><a href="/html/index.html">Главная страница</a></li>
                            <li><a href="/html/catalog.html">Каталог</a>
                                <ul>
                                    <li><a href="/html/cameras.html">Фотоаппараты</a></li>
                                    <li><a href="/html/objectives.html">Объективы</a></li>
                                    <li><a href="/html/flashlights.html">Вспышки</a></li>
                                    <li><a href="/html/optical.html">Оптические приборы</a></li>
                                </ul>
                            </li>
                            <li><a href="/html/about me.html">Об авторе</a></li>
                            <li><a href="/html/company.html">О фирме</a></li>
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
                <a href="#" id="cart">Корзина</a>
                <a href="#" id="login">Войти</a>
                <a href="#" id="register">Регистрация</a>
            </div>
        </div>
    )
}