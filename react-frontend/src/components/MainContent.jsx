import "../styles/main-content.css"

export default function MainContent() {
    return (
        <div id="page__content">
            <div id="welcome-screen">
                <p id="welcome-text">Всё ради момента.</p>
            </div>
            <div id="categories">
                <p>Популярные категории</p>
                <div id="categories-list">
                    <a href="/html/cameras.html">
                        <img src="/img/category_icons/camera.png" alt="camera"/>
                            <span>Фотоаппараты</span>
                    </a>
                    <a href="/html/objectives.html">
                        <img src="/img/category_icons/lens.png" alt="lens"/>
                            <span>Объективы</span>
                    </a>
                    <a href="/html/flashlights.html">
                        <img src="/img/category_icons/camera-flash.png" alt="flash"/>
                            <span>Вспышки</span>
                    </a>
                    <a href="/html/optical.html">
                        <img src="/img/category_icons/binocular.png" alt="binocular"/>
                            <span>Оптические приборы</span>
                    </a>
                </div>
            </div>
            <div id="main-content">
                <div id="main-table">
                    <div id="item__inner">
                        <p id="group-name">Хиты продаж</p>
                        <div id="item-list">
                            <a href="/html/catalog.html#">
                                <img src="/img/item_img/Canon EOS 2000D.jpg"/>
                                <div id="item-info">
                                    <p>Зеркальный фотоаппарат Canon EOS 2000D Kit 18-55 II</p>
                                    <div id="item-price">
                                        <p id="previous-price"><s>43 758₽</s></p>
                                        <p id="current-price">42 900₽</p>
                                    </div>
                                </div>
                            </a>
                            <a href="/html/catalog.html">
                                <img src="/img/item_img/Canon EOS 6D Mark II Body.jpg"/>
                                <div id="item-info">
                                    <p>Зеркальный фотоаппарат Canon EOS 6D Mark II Body</p>
                                    <div id="item-price">
                                        <p id="previous-price"><s>130 140₽</s></p>
                                        <p id="current-price">120 500₽</p>
                                    </div>
                                </div>
                            </a>
                            <a href="/html/catalog.html">
                                <img src="/img/item_img/Fujifilm Instax Mini 12 Blue.jpg"/>
                                <div id="item-info">
                                    <p>Моментальная фотокамера Fujifilm Instax Mini 12 Blue</p>
                                    <div id="item-price">
                                        <p id="previous-price"><s>15 100₽</s></p>
                                        <p id="current-price">11 900₽</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div id="item__inner">
                        <p id="group-name">Новинки</p>
                        <div id="item-list">
                            <a href="/html/catalog.html">
                                <img src="/img/item_img/Fujifilm X-T5 body.jpeg"/>
                                <div id="item-info">
                                    <p>Фотоаппарат Fujifilm X-T5 body черный</p>
                                    <div id="item-price">
                                        <p id="previous-price"><s>203 289 ₽</s></p>
                                        <p id="current-price">189 990₽</p>
                                    </div>
                                </div>
                            </a>
                            <a href="/html/catalog.html">
                                <img src="/img/item_img/Canon EOS M50 Mark II kit EF-M 15-45mm.jpg"/>
                                <div id="item-info">
                                    <p>Б/зеркальный фотоаппарат Canon EOS M50</p>
                                    <p>Mark II kit EF-M 15-45mm f/3.5-6.3 IS</p>
                                    <div id="item-price">
                                        <p id="previous-price"><s>83 556 ₽</s></p>
                                        <p id="current-price">78 090 ₽</p>
                                    </div>
                                </div>
                            </a>
                            <a href="/html/catalog.html">
                                <img src="/img/item_img/Canon RF 16mm f2.8 STM.jpg"/>
                                <div id="item-info">
                                    <p>Объектив Canon RF 16mm f/2.8 STM</p>
                                    <div id="item-price">
                                        <p id="previous-price"><s>33 591 ₽</s></p>
                                        <p id="current-price">29 990 ₽</p>
                                    </div>
                                </div>
                            </a>
                            <a href="/html/catalog.html">
                                <img src="/img/item_img/Sony ZV-E10 kit 16-50mmjpg.jpg"/>
                                <div id="item-info">
                                    <p>Фотоаппарат Sony ZV-E10 kit 16-50mm черный</p>
                                    <div id="item-price">
                                        <p id="previous-price"><s>93 965 ₽</s></p>
                                        <p id="current-price">89 490 ₽</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                    <div id="item__inner">
                        <p id="group-name">Выгодные скидки</p>
                        <div id="item-list">
                            <a href="/html/catalog.html">
                                <img src="/img/item_img/Raylab RL-LED09RGB 2500-9900К 2600mAh.jpg"/>
                                <div id="item-info">
                                    <p>Светодиодный осветитель Raylab RL-LED09RGB 2500-9900К 2600mAh</p>
                                    <div id="item-price">
                                        <p id="previous-price"><s>5 490 ₽</s></p>
                                        <p id="current-price">3 500 ₽</p>
                                    </div>
                                </div>
                            </a>
                            <a href="/html/catalog.html">
                                <img src="/img/item_img/Falcon Eyes Fea-Ob20 BW 200.png"/>
                                <div id="item-info">
                                    <p>Октобокс Falcon Eyes Fea-Ob20 BW 200 см</p>
                                    <div id="item-price">
                                        <p id="previous-price"><s>13 275 ₽</s></p>
                                        <p id="current-price">10 620 ₽</p>
                                    </div>
                                </div>
                            </a>
                            <a href="/html/catalog.html">
                                <img src="/img/item_img/Hama Delta Duo 170.jpg" />
                                <div id="item-info">
                                    <p>Штатив Hama Delta Duo 170</p>
                                    <div id="item-price">
                                        <p id="previous-price"><s>4 832 ₽</s></p>
                                        <p id="current-price">4 202 ₽</p>
                                    </div>
                                </div>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    )
}