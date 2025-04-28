import {createRoot} from 'react-dom/client';
import {StrictMode} from "react";
import {App} from './components/App';
import {BrowserRouter} from "react-router-dom";

const root = createRoot(document.getElementById('root'));

root.render(
  <StrictMode>
      <BrowserRouter basename="/">
          <App />
      </BrowserRouter>
  </StrictMode>
);
