package ro.mpp2025.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ro.mpp2025.Domain.Spectacol;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class SpectacolDbRepoHibernate implements ISpectacolRepo {

    private static final Logger logger = LogManager.getLogger(SpectacolDbRepoHibernate.class);

    public SpectacolDbRepoHibernate() {
        logger.info("SpectacolDbRepoHibernate initialized using HibernateUtil");
    }

    @Override
    public void add(Spectacol spectacol) {
        logger.trace("Inserting {}", spectacol);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.persist(spectacol);
            tx.commit();
            logger.info("Inserted spectacol: {}", spectacol);
        } catch (Exception e) {
            logger.error("Error inserting spectacol", e);
            throw new RuntimeException("Eroare la adăugare spectacol", e);
        }
    }

    @Override
    public void update(Spectacol spectacol) {
        logger.trace("Updating {}", spectacol);
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction tx = session.beginTransaction();
            session.merge(spectacol);
            tx.commit();
            logger.info("Updated spectacol: {}", spectacol);
        } catch (Exception e) {
            logger.error("Error updating spectacol", e);
            throw new RuntimeException("Eroare la actualizare spectacol", e);
        }
    }

    @Override
    public void remove(Integer id) {
        logger.warn("Spectacol removal is not allowed");
        throw new RuntimeException("Spectacol removal is not allowed");
    }

    @Override
    public Spectacol getById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Spectacol.class, id);
        }
    }

    @Override
    public Iterable<Spectacol> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Spectacol", Spectacol.class).list();
        } catch (Exception e) {
            System.err.println("Eroare la getAll() Spectacol: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public Iterable<Spectacol> getBetweenDates(LocalDateTime start, LocalDateTime end) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            String prefix = start.toLocalDate().toString();

            List<Spectacol> result = session.createQuery(
                            "FROM Spectacol s WHERE cast(s.data as string) LIKE :prefix", Spectacol.class)
                    .setParameter("prefix", prefix + "%")
                    .list();

            System.out.println("Rezultate pe " + prefix + ": " + result.size());
            return result;

        } catch (Exception e) {
            System.err.println("Eroare: " + e.getMessage());
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
    @Override
    public Spectacol getByParams(String artist, LocalDateTime data, String locatie,Integer disponibile, Integer vandute) throws EntityRepoException {
        return null;
    }


}
