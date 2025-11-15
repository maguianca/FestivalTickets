package ro.mpp2025.Repository;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ro.mpp2025.Domain.Bilet;
import ro.mpp2025.Domain.Spectacol;
import java.util.List;

public class BiletDbRepoHibernate implements IBiletRepo {
    private static final Logger logger = LogManager.getLogger(BiletDbRepoHibernate.class);

    public BiletDbRepoHibernate() {
        logger.info("Initialized BiletDbRepoHibernate using HibernateUtil");
    }

    @Override
    public Iterable<Bilet> getBySpectacol(Spectacol spectacol) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Bilet WHERE spectacol = :spectacol", Bilet.class)
                    .setParameter("spectacol", spectacol)
                    .list();
        }
    }

    @Override
    public void add(Bilet bilet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.persist(bilet);
            tx.commit();
            logger.info("Inserted bilet: {}", bilet);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Eroare la adăugare bilet", e);
            throw new RuntimeException("Eroare la adăugare bilet", e);
        }
    }

    @Override
    public void update(Bilet bilet) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            session.merge(bilet);
            tx.commit();
            logger.info("Updated bilet: {}", bilet);
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Eroare la update bilet", e);
            throw new RuntimeException("Eroare la update bilet", e);
        }
    }

    @Override
    public void remove(Integer id) {
        Transaction tx = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            tx = session.beginTransaction();
            Bilet bilet = session.get(Bilet.class, id);
            if (bilet != null) {
                session.remove(bilet);
                tx.commit();
                logger.info("Deleted bilet cu id={}", id);
            }
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            logger.error("Eroare la ștergere bilet", e);
            throw new RuntimeException("Eroare la ștergere bilet", e);
        }
    }

    @Override
    public Bilet getById(Integer id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Bilet.class, id);
        }
    }

    @Override
    public Iterable<Bilet> getAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Bilet", Bilet.class).list();
        }
    }
}
