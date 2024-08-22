import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles/PastReservations.css';

function PastReservations({ username }) {
    const [reservations, setReservations] = useState([]);

    useEffect(() => {
        const fetchReservations = async () => {
            try {
                const token = sessionStorage.getItem('token'); // Recupera il token dal sessionStorage
                const response = await axios.get('http://localhost:8080/reservation/all-by-username', {
                    params: { username }, // Passa lo username come parametro
                    headers: {
                        Authorization: `Bearer ${token}` // Aggiunge il token nell'header Authorization
                    }
                });
                setReservations(response.data); // Imposta le prenotazioni ricevute nello stato
            } catch (error) {
                console.error('Error during retrieving reservations:', error);
            }
        };

        fetchReservations();
    }, [username]);

    return (
        <div className="past-reservations-container">
            <h2>Reservation History</h2>
            {reservations.length > 0 ? (
                reservations.map((reservation, index) => (
                    <div key={index} className="reservation-card">
                        <h3 className="reservation-date">
                            <i className="fas fa-calendar-alt"></i> Date: {new Date(reservation.date).toLocaleDateString()}
                        </h3>
                        <p className="reservation-slot">
                            <i className="fas fa-clock"></i> Slot: {reservation.slot.time}
                        </p>
                        <p className="reservation-people">
                            <i className="fas fa-users"></i> Number of People: {reservation.numberPeople}
                        </p>
                    </div>
                ))
            ) : (
                <p>You have no past reservations.</p>
            )}
        </div>
    );
}

export default PastReservations;
