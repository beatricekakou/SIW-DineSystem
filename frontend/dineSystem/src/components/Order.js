import React, { useState } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import Menu from './Menu';

const Order = () => {
    const [cart, setCart] = useState([]);

    // Function to add a dish to the cart
    const addToCart = (dish) => {
        const existingDish = cart.find(item => item.id === dish.id);
        if (existingDish) {
            // If the dish already exists in the cart, increase its quantity
            setCart(cart.map(item =>
                item.id === dish.id ? { ...item, quantity: item.quantity + 1 } : item
            ));
        } else {
            // If the dish is not in the cart, add it with a quantity of 1
            setCart([...cart, { ...dish, quantity: 1 }]);
        }
    };

    // Function to remove a dish from the cart
    const removeFromCart = (dish) => {
        const existingDish = cart.find(item => item.id === dish.id);
        if (existingDish.quantity === 1) {
            // If the quantity is 1, remove the dish from the cart
            setCart(cart.filter(item => item.id !== dish.id));
        } else {
            // If the quantity is more than 1, decrease its quantity
            setCart(cart.map(item =>
                item.id === dish.id ? { ...item, quantity: item.quantity - 1 } : item
            ));
        }
    };

    // Calculate the total price of the items in the cart
    const getTotal = () => {
        return cart.reduce((total, dish) => total + dish.price * dish.quantity, 0).toFixed(2);
    };

    // Handle payment by sending the order to the backend
    const handlePayment = async () => {
        try {
            // Retrieve the token from sessionStorage
            const token = sessionStorage.getItem('token');

            // Configure the Authorization header with the token
            const config = {
                headers: { Authorization: `Bearer ${token}` }
            };

            // Build the order DTO with the price field included
            const orderDTO = {
                userId: sessionStorage.getItem('username'), // Assuming the user ID is stored in sessionStorage
                orderDetails: cart.map(dish => ({
                    dishId: dish.id,
                    quantity: dish.quantity,
                    price: dish.price // Include the price for each dish
                })),
            };

            // Make the POST request to the server to create the order
            const response = await axios.post('http://localhost:8080/order/new', orderDTO, config);

            if (response.status === 200) {
                alert('Order completed successfully!');
                setCart([]); // Reset the cart after payment
            } else {
                alert('Error completing the order. Please try again.');
            }
        } catch (error) {
            console.error('Error completing the order:', error);
            alert('Error completing the order. Please try again.');
        }
    };

    return (
        <div className="container mt-5">
            <h2 className="text-center">Order</h2>
            <p className="text-center">You can place your order here.</p>

            {/* Render the Menu component and pass the addToCart function as a prop */}
            <Menu onAddToCart={addToCart} />

            {cart.length > 0 && (
                <div className="mt-5">
                    <h3>Cart</h3>
                    <ul className="list-group mb-3">
                        {cart.map((dish, index) => (
                            <li key={index} className="list-group-item d-flex justify-content-between align-items-center">
                                <span>{dish.name} - €{dish.price} x {dish.quantity}</span>
                                <div>
                                    {/* Button to increase the quantity of the dish */}
                                    <button
                                        className="btn btn-success btn-sm"
                                        onClick={() => addToCart(dish)}
                                    >
                                        +
                                    </button>
                                    {/* Button to decrease the quantity of the dish */}
                                    <button
                                        className="btn btn-danger btn-sm ml-2"
                                        onClick={() => removeFromCart(dish)}
                                    >
                                        -
                                    </button>
                                </div>
                            </li>
                        ))}
                    </ul>
                    {/* Display the total amount */}
                    <h4 className="text-right">Total: €{getTotal()}</h4>
                    <div className="text-right">
                        {/* Button to initiate the payment process */}
                        <button className="btn btn-primary" onClick={handlePayment}>Pay</button>
                    </div>
                </div>
            )}
        </div>
    );
};

export default Order;
