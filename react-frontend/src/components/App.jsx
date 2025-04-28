import {Header} from "./page/Header";
import {MainPage} from "./page/MainPage";
import {Footer} from "./page/Footer";
import {Route, Routes} from "react-router-dom";
import {CategoryPage} from "./page/CategoryPage";
import '../styles/app.css';

export const App = () => {
  return (
      <>
          <Header />
          <Routes>
              <Route path='/' element={<MainPage />} />
              <Route path='/cameras' element={<CategoryPage label={'Фотоаппараты'} categoryCode={'CAM'}/>} />
          </Routes>
          <Footer />
      </>
  );
}