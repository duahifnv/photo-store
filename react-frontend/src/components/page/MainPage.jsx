import camera_icon from '../../assets/icons/camera.png';
import lens_icon from '../../assets/icons/lens.png';
import binocular_icon from '../../assets/icons/binocular.png';
import flash_icon from '../../assets/icons/camera-flash.png';
import '../../styles/page/main-page.css';

import {Popular} from "../item/Popular";
import {Item} from "../item/Item";

export const MainPage = () => {
    return (
        <div id="page__content">
            <div id="welcome-screen">
                <p id="welcome-text">Всё ради момента.</p>
            </div>
            <div id="categories">
                <p>Популярные категории</p>
                <div id="categories-list">
                    <Popular linkTo={"/cameras"} label={"Фотоаппараты"}
                             imgSrc={camera_icon} imgAlt={"lens"}/>
                    <Popular linkTo={"/lens"} label={"Объективы"}
                             imgSrc={lens_icon} imgAlt={"lens"}/>
                    <Popular linkTo={"/flashes"} label={"Вспышки"}
                             imgSrc={flash_icon} imgAlt={"flash"}/>
                    <Popular linkTo={"/optical"} label={"Оптические приборы"}
                             imgSrc={binocular_icon} imgAlt={"binocular"}/>
                </div>
            </div>
            <div id="main-content">
                <div id="main-table">
                </div>
            </div>
        </div>
    )
}