import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './styles/PastOrders.css';

function PastOrders({ username }) {
    const [orders, setOrders] = useState([]);

    useEffect(() => {
        const fetchOrders = async () => {
            try {
                const token = sessionStorage.getItem('token');
                const response = await axios.get('http://localhost:8080/order/all-by-username', {
                    params: { username },
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });
                setOrders(response.data);
            } catch (error) {
                console.error('Error during retrieving orders', error);
            }
        };

        fetchOrders();
    }, [username]);

    return (
        <div className="past-orders-container">
            <h2></h2>
            {orders.length > 0 ? (
                orders.map((order, index) => (
                    <div key={index} className="order-card">
                        <h3 className="order-date">Date: {new Date(order.date).toLocaleDateString()}</h3>
                        <ul className="order-items">
                            {order.dishes.map((dish, idx) => (
                                <li key={idx} className="order-item">
                                    <span className="dish-name">{dish.name}</span>
                                    <span className="dish-quantity">Quantity: {dish.quantity}</span>
                                    <span className="dish-price">Price: {dish.price} €</span>
                                </li>
                            ))}
                        </ul>
                        <h4 className="order-total">Total amount: {order.amount} €</h4>
                    </div>
                ))
            ) : (
                <p>You have no past orders.</p>
            )}
        </div>
    );
}

export default PastOrders;
