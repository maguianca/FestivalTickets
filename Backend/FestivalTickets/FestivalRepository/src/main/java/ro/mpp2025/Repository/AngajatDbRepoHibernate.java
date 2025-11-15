package ro.mpp2025.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import ro.mpp2025.Domain.Angajat;
import java.util.List;

public class AngajatDbRepoHibernate implements IAngajatRepo {
    private static final Logger logger = LogManager.getLogger(AngajatDbRepoHibernate.class);

    public AngajatDbRepoHibernate() {
        logger.info("Initialized AngajatDbRepoHibernate using HibernateUtil");
    }

    @Override
    public Angajat findByCredentials(String username, String password) throws EntityRepoException {
        logger.traceEntry("Searching for Angajat with username={} and password=***", username);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Angajat> query = session.createQuery(
                    "FROM Angajat WHERE username = :username AND password = :password", Angajat.class);
            query.setParameter("username", username);
            query.setParameter("password", password);
            Angajat result = query.uniqueResult();
            return logger.traceExit(result);
        } catch (Exception e) {
            logger.error("Error in findByCredentials", e);
            throw new EntityRepoException("Error finding Angajat by credentials");
        }
    }

    @Override
    public void add(Angajat angajat) throws EntityRepoException {
        logger.traceEntry("Adding {}", angajat);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(angajat);
            tx.commit();
            logger.info("Angajat added successfully");
        } catch (Exception e) {
            logger.error("Error adding Angajat", e);
            throw new EntityRepoException("Error adding Angajat");
        }
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
        logger.traceEntry("Getting Angajat by ID: {}", id);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Angajat angajat = session.get(Angajat.class, id);
            return logger.traceExit(angajat);
        } catch (Exception e) {
            logger.error("Error getting Angajat by ID", e);
            throw new EntityRepoException("Error getting Angajat by ID");
        }
    }

    @Override
    public Iterable<Angajat> getAll() throws EntityRepoException {
        logger.traceEntry();
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Angajat> angajati = session.createQuery("FROM Angajat", Angajat.class).list();
            return logger.traceExit(angajati);
        } catch (Exception e) {
            logger.error("Error getting all Angajati", e);
            throw new EntityRepoException("Error getting all Angajati");
        }
    }
}
