package hiber.dao;

import hiber.model.Car;

import java.util.List;
import java.util.Optional;

public interface CarDao {
    void addCar(Car car);
    List<Car> listCars();
    void updateCar(Car car);
    Optional<Car> getCarById(Long id);
    List<Car> getCarsByModel(String model);
}
