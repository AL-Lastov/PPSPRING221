package hiber.service;

import hiber.model.Car;
import java.util.List;
import java.util.Optional;

public interface CarService {
    void addCar(Car car);
    List<Car> listCars();
    void updateCar(Car car);
    Optional<Car> getCarById(Long id);
    Optional<Car> getCarsByModel(String model);
}
