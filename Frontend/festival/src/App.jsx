import Shows from './components/Shows.jsx';
import Login from './components/Login.jsx';
import './App.css';
import { useState } from 'react';

function App() {
    const [isAuthenticated, setAuthenticated] = useState(
        !!localStorage.getItem('token')
    );

    const handleLoginSuccess = () => {
        setAuthenticated(true);
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        setAuthenticated(false);
    };

    return (
        <div className="app-wrapper">
            <div className="main-content">
                {isAuthenticated ? (
                    <Shows onLogout={handleLogout} />
                ) : (
                    <Login onLoginSuccess={handleLoginSuccess} />
                )}
            </div>
        </div>
    );
}

export default App;
