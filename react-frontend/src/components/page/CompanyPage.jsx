import {Link} from "react-router-dom";
import photostore_logo from '../../assets/logos/photostore_logo.png';
import '../../styles/page/company-page.css';

export const CompanyPage = () => {
    return (
        <>
            <div id="welcome-screen">
                <p id="welcome-text">О фирме</p>
            </div>
            <div id="information-wrap">
                <div id="logo-wrap">
                    <Link to={"/"}>
                        <img src={photostore_logo} alt="photoStore"/>
                    </Link>
                </div>
                <div id="text-wrap">
                    <div id="info-wrap">
                        <div id="info-text">
                            <h1>О нашей компании</h1>
                            <p>Добро пожаловать в <b>photoStore</b>,
                                лидера на рынке продаж <b>фотоаппаратов</b> в России!
                            </p>
                            <p><b>photoStore</b> - команда профессионалов, которые с гордостью
                                предлагают широкий ассортимент фотоаппаратов от
                                ведущих мировых производителей. <br/>Наша цель -
                                помочь вам запечатлеть самые ценные моменты
                                вашей жизни, предоставляя вам качественное
                                оборудование, которое отвечает самым
                                высоким стандартам.
                            </p>
                        </div>
                        <div id="contact-list">
                            <p>Команда <b>photoStore</b>
                                ждет ваших вопросов, предложений и пожеланий:
                            </p>
                            <ul>
                                <li>В нашей группе Вконтакте: <b>https://vk.com/photostore</b></li>
                                <li>По телефону: <b>+79040123321</b></li>
                                <li>По электронной почте: <b>order@photostore.ru</b></li>
                                <li>По адресу: г.Ростов-на-Дону, улица Текучёва, д.141А</li>
                            </ul>
                        </div>

                    </div>
                </div>
                <div id="location-wrap">
                    <h1>Мы на карте:</h1>
                    <div id="active-map">
                        <a id="map-city"
                           href="https://yandex.ru/maps/39/rostov-na-donu/?utm_medium=mapframe&utm_source=maps">Ростов‑на‑Дону</a>
                        <a id="map-address"
                           href="https://yandex.ru/maps/39/rostov-na-donu/?azimuth=3.6475277415024374&ll=39.708028%2C47.235448&mode=routes&rtext=47.236422%2C39.713777~47.235559%2C39.708183&rtt=auto&ruri=~&utm_medium=mapframe&utm_source=maps&z=18.01">Яндекс
                            Карты</a>
                        <iframe
                            src="https://yandex.ru/map-widget/v1/?azimuth=3.6475277415024374&ll=39.708028%2C47.235448&mode=routes&rtext=47.236422%2C39.713777~47.235559%2C39.708183&rtt=auto&ruri=~&z=18.01"
                            frameBorder="1" allowFullScreen={true}></iframe>
                    </div>
                </div>
                <div id="feedback-wrap">
                    <form id="feedback">
                        <h1>Обратная связь</h1>
                        <div className="input-wrap">
                            <input type="text" name="username" placeholder="Ваше имя"/>
                        </div>
                        <div className="input-wrap">
                            <input type="email" name="email" placeholder="Ваша почта"/>
                        </div>
                        <div className="input-wrap">
                            <input type="text" name="order-id" placeholder="Номер заказа"/>
                        </div>
                        <div className="input-wrap" id="message-box">
                            <input type="text" name="username" placeholder="Ваше сообщение"/>
                        </div>
                        <div className="input-wrap">
                            <input id="submit-btn" type="submit" name="submit" value="Отправить"/>
                        </div>
                    </form>
                </div>
            </div>
        </>
    )
}