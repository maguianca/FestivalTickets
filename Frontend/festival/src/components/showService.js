const apiEndpoint = 'http://localhost:8080/festival/spectacole';

const getAuthHeaders = () => {
    const token = localStorage.getItem('token');
    return {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}`
    };
};

export const fetchShows = async () => {
    try {
        const response = await fetch(apiEndpoint, {
            headers: getAuthHeaders()
        });
        if (!response.ok) throw new Error('Network response was not ok');
        return await response.json();
    } catch (error) {
        console.error('Error fetching shows:', error);
        throw error;
    }
};

export const updateShow = async (show) => {
    try {
        const id = show.id;
        const response = await fetch(`${apiEndpoint}/${id}`, {
            method: 'PUT',
            headers: getAuthHeaders(),
            body: JSON.stringify(show)
        });
        if (!response.ok) throw new Error('Network response was not ok');
        return await response.json();
    } catch (error) {
        console.error('Error updating show:', error);
        throw error;
    }
};

export const deleteShow = async (id) => {
    try {
        const response = await fetch(`${apiEndpoint}/${id}`, {
            method: 'DELETE',
            headers: getAuthHeaders()
        });
        if (!response.ok) throw new Error('Network response was not ok');
    } catch (error) {
        console.error('Error deleting show:', error);
        throw error;
    }
};

export const addShow = async (show) => {
    try {
        const response = await fetch(apiEndpoint, {
            method: 'POST',
            headers: getAuthHeaders(),
            body: JSON.stringify(show)
        });
        if (!response.ok) throw new Error('Network response was not ok');
        return await response.json();
    } catch (error) {
        console.error('Error adding show:', error);
        throw error;
    }
};
