package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;
@Repository
public class CarDaoImp implements CarDao {
    private SessionFactory sessionFactory;


    @Override
    public void addCar(Car car) {
        sessionFactory.getCurrentSession().save(car);
    }

    @Override
    public List<Car> listCars() {
        String hql = "from Car";
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(hql, Car.class);
        return query.getResultList();
    }

    @Override
    public void updateCar(Car car) {
        sessionFactory.getCurrentSession().update(car);

    }

    @Override
    public Optional<Car> getCarById(Long id) {
        try {
            Car car = sessionFactory.getCurrentSession().get(Car.class, id);
            return Optional.ofNullable(car);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    @Override
    public List<Car> getCarsByModel(String model) {
        String hql = "from Car where model = :model";
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(hql, Car.class);
        query.setParameter("model", model);
        return query.getResultList();
    }
}
