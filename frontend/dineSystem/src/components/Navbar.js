import React from 'react';
import { Link } from 'react-router-dom';
import 'bootstrap/dist/css/bootstrap.min.css';
import Logout from './Logout';

const Navbar = ({ isAuthenticated, onLogout }) => {
    return (
        <nav className="navbar navbar-expand-lg navbar-light bg-light">
            <div className="container">
                <Link className="navbar-brand" to="/">DineSystem</Link>
                <div className="collapse navbar-collapse">
                    <ul className="navbar-nav mr-auto">
                        <li className="nav-item">
                            <Link className="nav-link" to="/">Registrati</Link>
                        </li>
                        {!isAuthenticated && (
                            <li className="nav-item">
                                <Link className="nav-link" to="/login">Accedi</Link>
                            </li>
                        )}
                        {isAuthenticated && (
                            <>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/order">Ordina</Link>
                                </li>
                                <li className="nav-item">
                                    <Link className="nav-link" to="/reservation">Prenota un tavolo</Link>
                                </li>
                                <li className="nav-item">
                                    <Logout onLogout={onLogout} />
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
