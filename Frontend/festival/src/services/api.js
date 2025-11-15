const API_BASE = 'http://localhost:8080/festival/spectacole';

export async function getData() {
    const res = await fetch(API_BASE);
    return res.json();
}

export async function addSpectacol(spectacol) {
    const res = await fetch(API_BASE, {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(spectacol),
    });
    return res.json();
}

export async function deleteSpectacol(id) {
    await fetch(`${API_BASE}/${id}`, { method: 'DELETE' });
}

export async function updateSpectacol(id, spectacol) {
    const res = await fetch(`${API_BASE}/${id}`, {
        method: 'PUT',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(spectacol),
    });
    return res.json();
}
