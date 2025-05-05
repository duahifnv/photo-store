import { Navigate } from 'react-router-dom';
import { useAuth } from './AuthProvider.tsx';

export const RequireAuth = ({ children }) => {
    const { isAuthenticated } = useAuth();

    if (!isAuthenticated) {
        return <Navigate to="/auth" replace />;
    }

    return children;
};