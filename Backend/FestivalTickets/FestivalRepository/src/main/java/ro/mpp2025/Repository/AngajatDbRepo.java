package ro.mpp2025.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;
import ro.mpp2025.Domain.Angajat;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;


public class AngajatDbRepo extends DatabaseRepoUtils<Integer, Angajat> implements IAngajatRepo {
    private static final Logger logger = LogManager.getLogger(AngajatDbRepo.class);
    private JdbcUtils dbUtils;
    public AngajatDbRepo(Properties props) {
        super(props);
        logger.info("Initializing AngajatDbRepo with properties: {} ", props);
        dbUtils=new JdbcUtils(props);
    }

    @Override
    public Angajat decodeResult(ResultSet result) throws SQLException {
        var id = result.getInt("id");
        var username = result.getString("username");
        var password = result.getString("password");
        var email = result.getString("email");
        var angajat = new Angajat(username, password, email);
        angajat.setId(id);
        return angajat;
    }

    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedHash = digest.digest(password.getBytes());
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedHash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("SHA-256 hashing error", e);
        }
    }

    @Override
    public Angajat findByCredentials(String username, String password) throws EntityRepoException {
        String hashedPassword = hashPassword(password);
        return selectFirst("SELECT * FROM Angajat WHERE username = ? AND password = ?", username, hashedPassword);
    }

    @Override
    public void add(Angajat angajat) throws EntityRepoException {
        logger.trace("Inserting {}", angajat);

        executeNonQuery("INSERT INTO Angajat (username, password, email) VALUES (?, ?, ?)",
                angajat.getUsername(),
                angajat.getPassword(),
                angajat.getEmail());

        logger.info("Inserted successfully");
        logger.traceExit();
    }

    @Override
    public void update(Angajat angajat) throws EntityRepoException {
        throw new EntityRepoException("Angajat update is not allowed");
    }

    @Override
    public void remove(Integer id) throws EntityRepoException {
        throw new EntityRepoException("Angajat removal is not allowed");
    }

    @Override
    public Angajat getById(Integer id) throws EntityRepoException {
        return selectFirst("SELECT * FROM Angajat WHERE id = ?", id);
    }

    @Override
    public Iterable<Angajat> getAll() throws EntityRepoException {
        return select("SELECT * FROM Angajat");
    }
}
