import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import Register from './components/Register';
import Order from './components/Order';
import Reservation from './components/Reservation';
import Login from './components/Login';
import Navbar from './components/Navbar';
import Logout from './components/Logout';
import PastOrders from './components/PastOrders';
import PastReservations from './components/PastReservations'; // Importa il nuovo componente

function App() {
    const [isAuthenticated, setIsAuthenticated] = useState(false);
    const [username, setUsername] = useState('');

    useEffect(() => {
        const token = sessionStorage.getItem('token');
        const savedUsername = sessionStorage.getItem('username');
        if (token && savedUsername) {
            setIsAuthenticated(true);
            setUsername(savedUsername);
        }
    }, []);

    const handleLogin = () => {
        const savedUsername = sessionStorage.getItem('username');
        setIsAuthenticated(true);
        setUsername(savedUsername);
    };

    const handleRegister = () => {};

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
                            <Route path="/past-orders" element={<PastOrders username={username} />} />
                            <Route path="/past-reservations" element={<PastReservations username={username} />} />
                        </>
                    )}
                </Routes>
            </div>
        </Router>
    );
}

export default App;
