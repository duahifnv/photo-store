import "../../styles/page/header.css"
import dropdown_button from "../../assets/ui/nav_dropdown_button.png"
import logo from "../../assets/logos/photostore_logo.png"
import {Link} from "react-router-dom";

export const Header = () => {
    return (
        <div id="page__header">
            <div id="logo__inner">
                <div id="dropdown">
                    <button>
                        <img src={dropdown_button} alt="Меню"/>
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
                <Link to={"/cart"}>Корзина</Link>
                <Link to={"/login"}>Войти</Link>
                <Link to={"/register"}>Регистрация</Link>
            </div>
        </div>
    )
}