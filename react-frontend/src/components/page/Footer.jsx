import "../../styles/page/footer.css"
import vk_logo from "../../assets/logos/social/vk_logo-48.png"
import github_logo from "../../assets/logos/social/github_logo.png"
import discord_logo from "../../assets/logos/social/discord_logo.png"
import copyright_logo from "../../assets/logos/copyright_logo.png"
import mir_logo from "../../assets/logos/payment/mir_logo.png"
import qiwi_logo from "../../assets/logos/payment/qiwi_logo.png"
import {Link} from "react-router-dom";

export const Footer = () => {
    return (
        <div id="page__footer">
            <div id="footer_container">
                <div id="icons">
                    <span>photoStore</span>
                    <div id="social-networks">
                        <a href="https://vk.com/fizalise">
                            <img src={vk_logo} alt="vk"/>
                        </a>
                        <a href="https://github.com/duahifnv">
                            <img src={github_logo} alt="github"/>
                        </a>
                        <a href="https://discord.com/users/363629160981200907/">
                            <img src={discord_logo} alt="discord"/>
                        </a>
                        <a href="mailto:mfomincev11@gmail.com"><b>Gmail</b></a>
                    </div>
                </div>
                <div id="information">
                    <h1>Информация</h1>
                    <Link to={"/company"}></Link>
                    <Link to={"/company"}>О компании</Link>
                    <Link to={"/company"}>Вакансии компании</Link>
                    <Link to={"/company"}>Соглашение об обработке персональных данных</Link>
                    <Link to={"/company"}>FAQ</Link>
                    <Link to={"/company"}>Контакты</Link>
                    <Link to={"/company"}>Обратная связь</Link>
                </div>
                <div id="payments">
                    <Link to={"/"}>
                        <img src={mir_logo} alt="mir"/>
                    </Link>
                    <Link to={"/"}>
                        <img src={qiwi_logo} alt="qiwi"/>
                    </Link>
                </div>
                <div id="copyright">
                    <img src={copyright_logo} alt="copyright-logo"/>
                        <p>Все материалы сайта photoStore.ru,
                            любая текстовая, графическая или видео информация,
                            а также структура сайта и оформление страниц,
                            защищены российским и международным законодательством
                            в соответствии с соглашением об охране авторских прав,
                            смежных прав и интеллектуальной собственности.</p>
                </div>
            </div>
        </div>
    )
}