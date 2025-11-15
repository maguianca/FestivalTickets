// src/components/Login.jsx
import { useState } from 'react';
import './CSS/Login.css';

export default function Login({ onLoginSuccess }) {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const handleLogin = async () => {
        try {
            const response = await fetch('http://localhost:8080/login', {
                method: 'POST',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify({ username, password })
            });

            if (!response.ok) throw new Error('Login failed');

            const data = await response.json();
            localStorage.setItem('token', data.token);
            onLoginSuccess();
        } catch (err) {
            alert('Autentificare esuata!');
        }
    };

    return (
        <div className="formContainer">
            <form className="formCustomization" onSubmit={e => e.preventDefault()}>
                <h2>Authentication</h2>

                <label htmlFor="username">Username</label>
                <input
                    id="username"
                    type="text"
                    value={username}
                    onChange={e => setUsername(e.target.value)}
                    placeholder="Introduceti username-ul"
                />

                <label htmlFor="password">Password</label>
                <input
                    id="password"
                    type="password"
                    value={password}
                    onChange={e => setPassword(e.target.value)}
                    placeholder="Introduceti parola"
                />

                <button className="submit-button" onClick={handleLogin}>Login</button>
            </form>
        </div>
    );
}
