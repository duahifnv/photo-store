import {Header} from "./page/Header";
import {MainPage} from "./page/MainPage";
import {Footer} from "./page/Footer";
import {Route, Routes} from "react-router-dom";
import {CategoryPage} from "./page/CategoryPage";
import '../styles/app.css';
import {CompanyPage} from "./page/CompanyPage";

export const App = () => {
  return (
      <>
          <Header />
          <Routes>
              <Route path='/' element={<MainPage />} />
              <Route path='/cameras' element={<CategoryPage label={'Фотоаппараты'} categoryCode={'CAM'}/>} />
              <Route path='/lens' element={<CategoryPage label={'Объективы'} categoryCode={'LEN'}/>} />
              <Route path='/flashes' element={<CategoryPage label={'Вспышки'} categoryCode={'FLS'}/>} />
              <Route path='/optical' element={<CategoryPage label={'Оптические приборы'} categoryCode={'OPT'}/>} />
              <Route path='/company' element={<CompanyPage />} />
          </Routes>
          <Footer />
      </>
  );
}