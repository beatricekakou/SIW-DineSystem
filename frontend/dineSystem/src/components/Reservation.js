import React, { useState, useEffect } from 'react';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import Calendar from 'react-calendar';
import 'react-calendar/dist/Calendar.css';

const Reservation = () => {
    const [selectedCapacity, setSelectedCapacity] = useState(null);
    const [availableDates, setAvailableDates] = useState([]);
    const [selectedDate, setSelectedDate] = useState(new Date());
    const [availableShifts, setAvailableShifts] = useState([]);
    const [selectedShift, setSelectedShift] = useState(null);
    const [dateToShiftsMap, setDateToShiftsMap] = useState({});
    const [activeStartDate, setActiveStartDate] = useState(new Date());

    const capacities = [2, 4, 6, 8, 12];

    // Retrieve the JWT token from sessionStorage
    const token = sessionStorage.getItem('token');

    // Generate all dates of the given month
    const generateDatesOfMonth = (date) => {
        const dates = [];
        const year = date.getFullYear();
        const month = date.getMonth();
        const daysInMonth = new Date(year, month + 1, 0).getDate();

        for (let day = 1; day <= daysInMonth; day++) {
            const localDate = new Date(year, month, day);
            localDate.setHours(0, 0, 0, 0);
            dates.push(localDate);
        }
        return dates;
    };

    // Fetch available dates for the selected capacity
    const fetchAvailableDates = async (capacity, date) => {
        const dates = generateDatesOfMonth(date);
        const datesWithSlots = {};
        const availableDatesArray = [];

        for (let date of dates) {
            try {
                const localDate = new Date(date);
                localDate.setHours(0, 0, 0, 0);

                const response = await axios.get('http://localhost:8080/reservation/available-slots', {
                    params: {
                        reservationDate: localDate.toISOString().split('T')[0], // Use toISOString().split('T')[0] to get YYYY-MM-DD format
                        numberOfPeople: capacity
                    }, 
                    headers: {
                        Authorization: `Bearer ${token}`
                    }
                });

                // If there are available slots, add the date to the available dates array
                if (response.data.length > 0) {
                    datesWithSlots[localDate.toISOString().split('T')[0]] = response.data;
                    availableDatesArray.push(localDate.toISOString().split('T')[0]);
                }
            } catch (error) {
                console.error('Error fetching available dates:', error);
            }
        }
        setDateToShiftsMap(datesWithSlots);
        setAvailableDates(availableDatesArray);
    };

    // Handle selection of capacity
    const handleCapacitySelect = (capacity) => {
        setSelectedCapacity(capacity);
        setAvailableShifts([]); // Reset available shifts when capacity changes
        fetchAvailableDates(capacity, activeStartDate); // Fetch available dates for the selected capacity
    };

    // Handle change of selected date
    const handleDateChange = (date) => {
        const localDate = new Date(date);
        localDate.setHours(0, 0, 0, 0);

        setSelectedDate(localDate);
        setAvailableShifts(dateToShiftsMap[localDate.toISOString().split('T')[0]] || []);
        setSelectedShift(null); // Reset selected shift when date changes
    };

    // Handle selection of shift
    const handleShiftSelect = (shift) => {
        setSelectedShift(shift);
    };

    // Disable tiles in the calendar that are not available
    const tileDisabled = ({ date, view }) => {
        if (view === 'month') {
            const dateStr = new Date(date).toISOString().split('T')[0];
            // Disable dates not present in the list of available dates
            return !availableDates.includes(dateStr);
        }
        return false;
    };

    // Handle reservation submission
    const handleReservation = async () => {
        const reservationDTO = {
            userId: sessionStorage.getItem('username'), // This should be dynamic based on the authenticated user
            date: selectedDate.toISOString().split('T')[0], // Format the date as YYYY-MM-DD
            slotTime: selectedShift,
            numberOfPeople: selectedCapacity
        };

        try {
            const response = await axios.post('http://localhost:8080/reservation/new',
                reservationDTO,  // Data payload as the second argument directly
                {
                    headers: {
                        Authorization: `Bearer ${token}`  // Correct placement of headers in the config object
                    }
                }
            );

            if (response.status === 200) {
                alert('Reservation made successfully!');
            } else {
                alert('Error during reservation.');
            }
        } catch (error) {
            console.error('Error during reservation:', error);
            alert('Error during reservation.');
        }
    };

    // Fetch available dates when capacity or active start date changes
    useEffect(() => {
        if (selectedCapacity) {
            fetchAvailableDates(selectedCapacity, activeStartDate);
        }
    }, [selectedCapacity, activeStartDate]); // Monitor changes to capacity and active start date

    // Fetch available dates initially when the component mounts
    useEffect(() => {
        if (selectedCapacity) {
            fetchAvailableDates(selectedCapacity, activeStartDate);
        }
    }, []); // Only on the first mount of the component

    return (
        <div className="container mt-5">
            <h2>Book a Table</h2>
            <div className="btn-group" role="group" aria-label="Select Capacity">
                {capacities.map(capacity => (
                    <button
                        key={capacity}
                        type="button"
                        className={`btn btn-outline-primary ${selectedCapacity === capacity ? 'active' : ''}`}
                        onClick={() => handleCapacitySelect(capacity)}
                    >
                        {capacity}
                    </button>
                ))}
            </div>

            {selectedCapacity && (
                <div className="mt-5">
                    <h3>Select a Date</h3>
                    <Calendar
                        value={selectedDate}
                        onActiveStartDateChange={({ activeStartDate }) => {
                            setActiveStartDate(activeStartDate);
                            fetchAvailableDates(selectedCapacity, activeStartDate);
                        }}
                        onChange={handleDateChange}
                        tileDisabled={tileDisabled}
                    />
                </div>
            )}

            {availableShifts.length > 0 && (
                <div className="mt-5">
                    <h3>Select a Shift</h3>
                    <div className="btn-group" role="group" aria-label="Select your slot">
                        {availableShifts.map(shift => (
                            <button
                                key={shift}
                                type="button"
                                className={`btn btn-outline-primary ${selectedShift === shift ? 'active' : ''}`}
                                onClick={() => handleShiftSelect(shift)}
                            >
                                {shift}
                            </button>
                        ))}
                    </div>
                </div>
            )}

            {selectedShift && (
                <div className="mt-5">
                    <button className="btn btn-success" onClick={handleReservation}>
                        Book
                    </button>
                </div>
            )}
        </div>
    );
};

export default Reservation;
