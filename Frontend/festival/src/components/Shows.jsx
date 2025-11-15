import React, { useState, useEffect } from 'react';
import ShowForm from './ShowForm';
import { fetchShows, updateShow, deleteShow, addShow } from './showService';
import './CSS/Shows.css';

const Shows = ({ onLogout }) => {
    const [shows, setShows] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const [isEditing, setIsEditing] = useState(false);
    const [currentShow, setCurrentShow] = useState(null);
    const [formData, setFormData] = useState({
        locatie: '',
        date: '',
        time: '',
        nrLocuriDisponibile: '',
        nrLocuriVandute: '',
        artist: ''
    });

    const getShows = async () => {
        try {
            const data = await fetchShows();
            setShows(data);
            setLoading(false);
        } catch (error) {
            setError(error.message);
            setLoading(false);
        }
    };

    useEffect(() => {
        getShows();
    }, []);

    const handleEdit = (show) => {
        const [rawDate, rawTime] = show.data.split(' ');
        const [day, month, year] = rawDate.split('-');
        const formattedDate = `${day}-${month}-${year}`;
        const timeWithoutMillis = rawTime.split('.')[0];

        setCurrentShow(show);
        setFormData({
            locatie: show.locatie || '',
            date: formattedDate,
            time: timeWithoutMillis,
            nrLocuriDisponibile: show.nrLocuriDisponibile || '',
            nrLocuriVandute: show.nrLocuriVandute || '',
            artist: show.artist || ''
        });
        setIsEditing(true);
    };

    const handleDelete = async (id) => {
        await deleteShow(id);
        await getShows();
    };

    const handleFormChange = (e) => {
        const { name, value } = e.target;
        setFormData({ ...formData, [name]: value });
    };

    const handleFormSubmit = async (e) => {
        e.preventDefault();

        const [year, month, day] = formData.date.split('-');
        const formattedDate = isEditing
            ? `${year}-${month}-${day} ${formData.time}.000`
            : `${year}-${month}-${day} ${formData.time}:00.000`;

        const updatedShow = {
            locatie: formData.locatie,
            data: formattedDate,
            nrLocuriDisponibile: formData.nrLocuriDisponibile,
            nrLocuriVandute: formData.nrLocuriVandute,
            artist: formData.artist,
            id: currentShow ? currentShow.id : null
        };

        try {
            if (isEditing) {
                await updateShow(updatedShow);
                setIsEditing(false);
                setCurrentShow(null);
            } else {
                await addShow(updatedShow);
            }

            setFormData({
                locatie: '',
                date: '',
                time: '',
                nrLocuriDisponibile: '',
                nrLocuriVandute: '',
                artist: ''
            });

            getShows();
        } catch (error) {
            console.error("Error submitting form:", error);
        }
    };

    const handleCancel = () => {
        setIsEditing(false);
        setCurrentShow(null);
        setFormData({
            locatie: '',
            date: '',
            time: '',
            nrLocuriDisponibile: '',
            nrLocuriVandute: '',
            artist: ''
        });
    };

    const handleLogout = () => {
        localStorage.removeItem('token');
        onLogout();
    };

    if (loading) return <div>Loading...</div>;
    if (error) return <div>Error: {error}</div>;

    return (
        <div>
            <div style={{ display: 'flex', justifyContent: 'space-between', alignItems: 'center' }}>
                <h1>Spectacole</h1>
                <button className="cancel-button" onClick={handleLogout}>Logout</button>
            </div>

            <ShowForm
                formData={formData}
                handleFormChange={handleFormChange}
                handleFormSubmit={handleFormSubmit}
                isEditing={isEditing}
                handleCancel={handleCancel}
            />

            <table>
                <thead>
                <tr>
                    <th>Location</th>
                    <th>Date</th>
                    <th>Available Tickets</th>
                    <th>Sold Tickets</th>
                    <th>Artist</th>
                    <th>Actions</th>
                </tr>
                </thead>
                <tbody>
                {shows.map((show) => (
                    <tr key={show.id}>
                        <td>{show.locatie}</td>
                        <td>{show.data}</td>
                        <td>{show.nrLocuriDisponibile}</td>
                        <td>{show.nrLocuriVandute}</td>
                        <td>{show.artist}</td>
                        <td>
                            <button type="button" onClick={() => handleEdit(show)}>Update</button>
                            <button type="button" onClick={() => handleDelete(show.id)}>Delete</button>
                        </td>
                    </tr>
                ))}
                </tbody>
            </table>
        </div>
    );
};

export default Shows;
