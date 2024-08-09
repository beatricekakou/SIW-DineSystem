import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Register from './components/Register';
import Order from './components/Order';
import Reservation from './components/Reservation';
import Login from './components/Login';
import Navbar from './components/Navbar';
import Logout from './components/Logout';

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [username, setUsername] = useState('');

    // Verifica l'autenticazione al caricamento dell'app
    useEffect(() => {
        const token = sessionStorage.getItem('token');
        const savedUsername = sessionStorage.getItem('username');
        if (token && savedUsername) {
            setIsAuthenticated(true);
            setUsername(savedUsername);
        }
    }, []);

    // Handle login
    const handleLogin = () => {
        const savedUsername = sessionStorage.getItem('username'); // Recupera lo username dal sessionStorage
        setIsAuthenticated(true);
        setUsername(savedUsername);
    };

    // Handle register
    const handleRegister = () => {
        // Logica aggiuntiva dopo la registrazione, se necessario
    };

    // Handle logout
    const handleLogout = () => {
        sessionStorage.removeItem('token');
        sessionStorage.removeItem('username');
        setIsAuthenticated(false);
        setUsername('');
    };

    return (
        <Router>
            <div className="App">
                <Navbar isAuthenticated={isAuthenticated} onLogout={handleLogout} />
                <Routes>
                    <Route path="/" element={<Register onRegister={handleRegister} />} />
                    <Route path="/login" element={<Login onLogin={handleLogin} />} />
                    {isAuthenticated && (
                        <>
                            <Route path="/order" element={<Order username={username} />} />
                            <Route path="/reservation" element={<Reservation username={username} />} />
                            <Route path="/logout" element={<Logout />} />
                        </>
                    )}
                </Routes>
            </div>
        </Router>
    );
}

export default App;
