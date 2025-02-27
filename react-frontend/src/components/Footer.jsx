import "../styles/footer.css"

export default function Footer() {
    return (
        <div id="page__footer">
            <div id="footer_container">
                <div id="icons">
                    <span>photoStore</span>
                    <div id="social-networks">
                        <a href="https://vk.com/fizalise">
                            <img src="/img/social_icons/vk-48.png" alt="vk"/>
                        </a>
                        <a href="https://github.com/duahifnv">
                            <img src="/img/social_icons/github.png" alt="github"/>
                        </a>
                        <a href="https://discord.com/users/363629160981200907/">
                            <img src="/img/social_icons/discord.png" alt="discord"/>
                        </a>
                        <a href="mailto:mfomincev11@gmail.com"><b>Gmail</b></a>
                    </div>
                </div>
                <div id="information">
                    <h1>Информация</h1>
                    <a href="/html/company.html">О компании</a>
                    <a href="/html/company.html">Вакансии компании</a>
                    <a href="/html/company.html">Соглашение об обработке персональных данных</a>
                    <a href="/html/company.html">FAQ</a>
                    <a href="/html/company.html">Контакты</a>
                    <a href="/html/company.html">Обратная связь</a>
                </div>
                <div id="payments">
                    <a href="#">
                        <img src="/img/payment_icons/visa.png" alt="visa"/>
                    </a>
                    <a href="#">
                        <img src="/img/payment_icons/Mir.png" alt="mir"/>
                    </a>
                    <a href="#">
                        <img src="/img/payment_icons/QIWI.png" alt="qiwi"/>
                    </a>
                </div>
                <div id="copyright">
                    <img src="/img/copyright.png" alt="copyright-logo"/>
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