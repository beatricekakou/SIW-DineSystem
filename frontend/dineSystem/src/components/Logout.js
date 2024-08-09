import React from 'react';

const Logout = ({ onLogout }) => {
    const handleLogoutClick = () => {
        onLogout();
    };

    return (
        <button className="btn btn-outline-danger ml-2" onClick={handleLogoutClick}>
            Logout
        </button>
    );
};

export default Logout;
