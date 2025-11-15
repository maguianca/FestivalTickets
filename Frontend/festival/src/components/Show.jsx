// Show.jsx
// eslint-disable-next-line react/prop-types
const Show = ({ show, onEdit, onDelete}) => {
    return (
        <div style={{display: 'flex', gap: '10px', alignItems: 'center', marginBottom: '10px'}}>
            <span>{show.locatie}</span>
            <span>{show.data}</span>
            <span>{show.nrLocuriDisponibile}</span>
            <span>{show.nrLocuriVandute}</span>
            <span>{show.artist}</span>
            <button type="button" onClick={() => onEdit(show)}>Update</button>
            <button type="button" onClick={() => onDelete(show.id)}>Delete</button>
        </div>
    );
};

export default Show;
