import React, { useState, useEffect } from 'react';
import axios from 'axios';

const Menu = ({ onAddToCart }) => {
    const [dishes, setDishes] = useState([]);
    const [categories, setCategories] = useState([]);
    const [selectedCategory, setSelectedCategory] = useState('');

    // Fetch dishes and categories when the component is mounted
    useEffect(() => {
        const fetchDishes = async () => {
            try {
                // Retrieve the token from sessionStorage
                const token = sessionStorage.getItem('token');

                // Configure the Authorization header with the token
                const config = {
                    headers: { Authorization: `Bearer ${token}` }
                };

                // Make the Axios request with the JWT token
                const response = await axios.get('http://localhost:8080/dish/all', config);
                setDishes(response.data);
                setCategories([...new Set(response.data.map(dish => dish.category.description))]);
            } catch (error) {
                console.error('Error fetching dishes:', error);
            }
        };
        fetchDishes();
    }, []);

    // Handle category selection
    const handleCategoryClick = (category) => {
        if (selectedCategory === category) {
            setSelectedCategory(''); // Deselect the category if it is already selected
        } else {
            setSelectedCategory(category);
        }
    };

    return (
        <div>
            <div className="text-center mb-3">
                <button
                    className="btn btn-primary"
                    onClick={() => setSelectedCategory('')}
                >
                    View Full Menu
                </button>
            </div>

            {selectedCategory ? (
                <div>
                    <h3 className="text-center">Category: {selectedCategory}</h3>
                    <div className="row">
                        {dishes.filter(dish => dish.category.description === selectedCategory).map(dish => (
                            <div className="col-md-4 mb-3" key={dish.id}>
                                <div className="card">
                                    <div className="card-body">
                                        <h5 className="card-title">{dish.name}</h5>
                                        <p className="card-text">{dish.description}</p>
                                        <p className="card-text"><strong>€{dish.price}</strong></p>
                                        <button
                                            className="btn btn-success"
                                            onClick={() => onAddToCart(dish)}
                                        >
                                            Add to Cart
                                        </button>
                                    </div>
                                </div>
                            </div>
                        ))}
                    </div>
                </div>
            ) : (
                <div>
                    <h3 className="text-center">Select a Category</h3>
                    <div className="list-group">
                        {categories.map(category => (
                            <button
                                key={category}
                                className={`list-group-item list-group-item-action ${selectedCategory === category ? 'active' : ''}`}
                                onClick={() => handleCategoryClick(category)}
                            >
                                {category}
                            </button>
                        ))}
                    </div>
                </div>
            )}
        </div>
    );
};

export default Menu;
