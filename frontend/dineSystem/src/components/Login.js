import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const Login = ({ onLogin }) => {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('');

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            // Send login request to the backend
            const response = await axios.post('http://localhost:8080/auth/login', {
                username: username,
                password: password
            });

            if (response.data) {
                // Save JWT token and username in sessionStorage
                sessionStorage.setItem('token', response.data); // JWT token
                sessionStorage.setItem('username', username); // Username

                // Call onLogin callback if login is successful
                onLogin();
                setError(''); // Clear any previous errors
            } else {
                setError('Incorrect username or password');
            }
        } catch (error) {
            setError('Error during login');
            console.error('Error during login:', error);
        }
    };

    return (
        <div className="container mt-5">
            <h2>Login</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>Username:</label>
                    <input
                        type="text"
                        value={username}
                        onChange={(e) => setUsername(e.target.value)}
                        className="form-control"
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input
                        type="password"
                        value={password}
                        onChange={(e) => setPassword(e.target.value)}
                        className="form-control"
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary">Login</button>
            </form>
        </div>
    );
};

export default Login;
