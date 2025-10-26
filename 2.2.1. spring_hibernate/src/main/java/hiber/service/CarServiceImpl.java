package hiber.service;

import hiber.dao.CarDao;
import hiber.model.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarDao carDao;

    @Transactional
    @Override
    public void addCar(Car car) {
        carDao.addCar(car);
    }

    @Transactional
    @Override
    public List<Car> listCars() {
        return carDao.listCars();
    }
    @Override
    public Optional<Car> getCarById(Long id) {
        return Optional.empty();
    }

    @Transactional
    @Override
    public void updateCar(Car car) {
        carDao.updateCar(car);
    }

    @Transactional
    @Override
    public List<Car> getCarsByModel(String model) {
        return carDao.getCarsByModel(model);
    }
}
