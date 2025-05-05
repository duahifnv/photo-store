import { Routes, Route } from 'react-router-dom';
import { AuthProvider } from './context/AuthProvider.tsx';
import { Header } from './page/Header';
import { Footer } from './page/Footer';
import { MainPage } from './page/MainPage';
import { CategoryPage } from './page/CategoryPage';
import { CompanyPage } from './page/CompanyPage';
import { AuthPage } from './page/AuthPage';

import '../styles/app.css';
import {CatalogPage} from "./page/CatalogPage.tsx";
// import { ProfilePage } from './page/ProfilePage';
// import { CheckoutPage } from './page/CheckoutPage';
// import { RequireAuth } from './page/RequireAuth';

export const App = () => {
    return (
        <AuthProvider>
            <Header />
            <Routes>
                <Route path='/' element={<MainPage />} />
                <Route path='/auth' element={<AuthPage />} />
                <Route path='/catalog' element={<CatalogPage />} />
                <Route path='/cameras' element={<CategoryPage label={'Фотоаппараты'} categoryCode={'CAM'}/>} />
                <Route path='/lens' element={<CategoryPage label={'Объективы'} categoryCode={'LEN'}/>} />
                <Route path='/flashes' element={<CategoryPage label={'Вспышки'} categoryCode={'FLS'}/>} />
                <Route path='/optical' element={<CategoryPage label={'Оптические приборы'} categoryCode={'OPT'}/>} />
                <Route path='/company' element={<CompanyPage />} />

                {/*/!* Защищенные маршруты *!/*/}
                {/*<Route path='/profile' element={*/}
                {/*    <RequireAuth>*/}
                {/*        <ProfilePage />*/}
                {/*    </RequireAuth>*/}
                {/*} />*/}

                {/*<Route path='/checkout' element={*/}
                {/*    <RequireAuth>*/}
                {/*        <CheckoutPage />*/}
                {/*    </RequireAuth>*/}
                {/*} />*/}
            </Routes>
            <Footer />
        </AuthProvider>
    );
}