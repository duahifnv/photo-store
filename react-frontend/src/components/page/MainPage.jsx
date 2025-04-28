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
                             imgSrc={binocular_icon} imgAlt={"flash"}/>
                    <Popular linkTo={"/optical"} label={"Оптические приборы"}
                             imgSrc={flash_icon} imgAlt={"binocular"}/>
                </div>
            </div>
            <div id="main-content">
                <div id="main-table">
                    <div id="item__inner">
                        <p id="group-name">Хиты продаж</p>
                        <div id="item-list">
                            {/*<Item label={'Зеркальный фотоаппарат Canon EOS 2000D Kit 18-55 II'}/>*/}
                            {/*<Item label={'Зеркальный фотоаппарат Canon EOS 6D Mark II Main'}/>*/}
                            {/*<Item label={'Моментальная фотокамера Fujifilm Instax Mini 12 Blue'}/>*/}
                        </div>
                    </div>
                    <div id="item__inner">
                        <p id="group-name">Новинки</p>
                        <div id="item-list">
                            {/*<Item label={'Фотоаппарат Fujifilm X-T5 body черный'}/>*/}
                            {/*<Item label={'Б/зеркальный фотоаппарат Canon EOS M50'}/>*/}
                            {/*<Item label={'Объектив Canon RF 16mm f/2.8 STM'}/>*/}
                            {/*<Item label={'Фотоаппарат Sony ZV-E10 kit 16-50mm черный'}/>*/}
                        </div>
                    </div>
                    <div id="item__inner">
                        <p id="group-name">Выгодные скидки</p>
                        <div id="item-list">
                            {/*<Item label={'Светодиодный осветитель Raylab RL-LED09RGB 2500-9900К 2600mAh'}/>*/}
                            {/*<Item label={'Октобокс Falcon Eyes Fea-Ob20 BW 200 см'}/>*/}
                            {/*<Item label={'Штатив Hama Delta Duo 170'}/>*/}
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}