import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { useAuth } from '../context/AuthProvider.tsx';
import '../../styles/page/auth-page.css';

export const AuthPage = () => {
    const [isLogin, setIsLogin] = useState(true);
    const [formData, setFormData] = useState({
        email: '',
        password: '',
        firstName: '',
        lastName: ''
    });
    const [error, setError] = useState('');
    const [isLoading, setIsLoading] = useState(false);
    const { login } = useAuth();
    const navigate = useNavigate();

    const handleChange = (e) => {
        setFormData({
            ...formData,
            [e.target.name]: e.target.value
        });
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setError('');
        setIsLoading(true);

        try {
            if (isLogin) {
                // Логика входа
                if (!formData.login || !formData.password) {
                    setError('Заполните все поля');
                    return;
                }

                const response = await fetch('http://localhost:9000/api/v1/auth/login', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: formData.login,
                        password: formData.password
                    })
                });

                const data = await response.json();

                if (!response.ok) {
                    throw new Error(data.message || 'Ошибка входа');
                }

                login(data.token);
                navigate('/');
            } else {
                // Логика регистрации
                if (!formData.email || !formData.password || !formData.firstName || !formData.lastName) {
                    setError('Заполните все поля');
                    return;
                }

                const response = await fetch('http://localhost:9000/api/v1/auth/register', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify({
                        username: formData.email,
                        password: formData.password,
                        email: formData.email,
                        name: formData.firstName,
                        surname: formData.lastName
                    })
                });

                const data = await response.json();

                if (!response.ok) {
                    throw new Error(data.message || 'Ошибка регистрации');
                }

                login(data.token);
                navigate('/');
            }
        } catch (err) {
            setError(err.message || 'Произошла ошибка');
        } finally {
            setIsLoading(false);
        }
    };

    return (
        <div className="auth-container">
            <h2>{isLogin ? 'Вход' : 'Регистрация'}</h2>
            {error && <div className="error-message">{error}</div>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Логин:</label>
                    <input
                        type="login"
                        name="login"
                        value={formData.login}
                        onChange={handleChange}
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Пароль:</label>
                    <input
                        type="password"
                        name="password"
                        value={formData.password}
                        onChange={handleChange}
                        required
                    />
                </div>
                {!isLogin && (
                    <>
                        <div className="form-group">
                            <label>Имя:</label>
                            <input
                                type="text"
                                name="firstName"
                                value={formData.firstName}
                                onChange={handleChange}
                                required
                            />
                        </div>
                        <div className="form-group">
                            <label>Фамилия:</label>
                            <input
                                type="text"
                                name="lastName"
                                value={formData.lastName}
                                onChange={handleChange}
                                required
                            />
                        </div>
                    </>
                )}
                <button
                    type="submit"
                    className="auth-button"
                    disabled={isLoading}
                >
                    {isLoading ? 'Загрузка...' : (isLogin ? 'Войти' : 'Зарегистрироваться')}
                </button>
            </form>
            <div className="auth-switch">
                {isLogin ? (
                    <span>Нет аккаунта? <button onClick={() => setIsLogin(false)}>Зарегистрироваться</button></span>
                ) : (
                    <span>Уже есть аккаунт? <button onClick={() => setIsLogin(true)}>Войти</button></span>
                )}
            </div>
        </div>
    );
};