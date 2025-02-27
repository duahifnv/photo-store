import { createRoot } from 'react-dom/client';
import { StrictMode } from "react";
import './styles/index.css';
import App from './components/App';

const root = createRoot(document.getElementById('root'));

root.render(
  <StrictMode>
    <App />
  </StrictMode>
);
