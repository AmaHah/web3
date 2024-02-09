package controller;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Named;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import models.Point;

import java.util.List;

@Named
@ApplicationScoped
public class PointCheckStorageController {
    @PersistenceContext(unitName = "Eclipselink_JPA")
    private EntityManager entityManager;

    @Transactional
    public void savePointCheck(Point point) {
        // Добавить точку в БД
        try {
            entityManager.getTransaction().begin();
            entityManager.merge(point);
            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            System.out.println(e);
        }
    }

    public List<Point> getCheckPointList() {
        return entityManager.createQuery("SELECT p FROM Point p", Point.class).getResultList();
    }
}

