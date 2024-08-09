import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';

const Register = ({ onRegister }) => {
    const [user, setUser] = useState({
        firstName: '',
        lastName: '',
        email: '',
        cellphone: '',
        credentials: {
            username: '',
            password: '',
            role: ''
        }
    });

    const [error, setError] = useState('');
    const [success, setSuccess] = useState('');

    // Handle input changes
    const handleChange = (e) => {
        const { name, value } = e.target;
        if (name === 'username' || name === 'password') {
            setUser(prevUser => ({
                ...prevUser,
                credentials: {
                    ...prevUser.credentials,
                    [name]: value
                }
            }));
        } else {
            setUser({ ...user, [name]: value });
        }
    };

    // Handle form submission
    const handleSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8080/auth/register', user); // Correct endpoint
            setSuccess('Registration successful!');
            setError('');
            setUser({
                firstName: '',
                lastName: '',
                email: '',
                cellphone: '',
                credentials: {
                    username: '',
                    password: '',
                    role: ''
                }
            });
            onRegister(); // Callback after successful registration
        } catch (err) {
            setError('An error occurred during registration.');
            setSuccess('');
        }
    };

    return (
        <div className="container mt-5">
            <h2>Register</h2>
            {error && <p style={{ color: 'red' }}>{error}</p>}
            {success && <p style={{ color: 'green' }}>{success}</p>}
            <form onSubmit={handleSubmit}>
                <div className="form-group">
                    <label>First Name:</label>
                    <input
                        type="text"
                        name="firstName"
                        value={user.firstName}
                        onChange={handleChange}
                        className="form-control"
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Last Name:</label>
                    <input
                        type="text"
                        name="lastName"
                        value={user.lastName}
                        onChange={handleChange}
                        className="form-control"
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Email:</label>
                    <input
                        type="email"
                        name="email"
                        value={user.email}
                        onChange={handleChange}
                        className="form-control"
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Cellphone:</label>
                    <input
                        type="text"
                        name="cellphone"
                        value={user.cellphone}
                        onChange={handleChange}
                        className="form-control"
                    />
                </div>
                <div className="form-group">
                    <label>Username:</label>
                    <input
                        type="text"
                        name="username"
                        value={user.credentials.username}
                        onChange={handleChange}
                        className="form-control"
                        required
                    />
                </div>
                <div className="form-group">
                    <label>Password:</label>
                    <input
                        type="password"
                        name="password"
                        value={user.credentials.password}
                        onChange={handleChange}
                        className="form-control"
                        required
                    />
                </div>
                <button type="submit" className="btn btn-primary">Register</button>
            </form>
        </div>
    );
};

export default Register;
