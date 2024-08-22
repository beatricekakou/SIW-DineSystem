import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Logout from './Logout';
import './styles/Navbar.css';

const Navbar = ({ isAuthenticated, onLogout }) => {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container">
                <Link className="navbar-brand" to="/">DineSystem</Link>
                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <Link className="nav-link" to="/">Sign Up</Link>
                        </li>
                        {!isAuthenticated && (
                            <li className="nav-item">
                                <Link className="nav-link" to="/login">Log In</Link>
                            </li>
                        )}
                        {isAuthenticated && (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/order">Order</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/reservation">Make a Reservation</Link>
                                </li>
                                <li className="nav-item">
                                    <Logout onLogout={onLogout} />
                                </li>
                                <li className="nav-item profile-dropdown">
                                    <img src="/profile-icon.png" alt="Profile" className="profile-icon nav-link" />
                                    <div className="dropdown-content">
                                        <Link className="nav-link" to="/past-orders">Order History</Link>
                                        <Link className="nav-link" to="/past-reservations">Reservation History</Link>
                                    </div>
                                </li>
                            </>
                        )}
                    </ul>
                </div>
            </div>
        </nav>
    );
};

export default Navbar;
