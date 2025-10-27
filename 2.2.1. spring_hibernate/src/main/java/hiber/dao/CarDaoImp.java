package hiber.dao;

import hiber.model.Car;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Repository
public class CarDaoImp implements CarDao {

    @Autowired
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
    public Optional<Car> getCarsByModel(String model) {
        String hql = "from Car where model = :model";
        TypedQuery<Car> query = sessionFactory.getCurrentSession().createQuery(hql, Car.class);
        query.setParameter("model", model);
        List<Car> cars = query.getResultList();
        return cars.stream().findFirst();
    }
}
