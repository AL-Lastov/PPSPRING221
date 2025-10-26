package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      List<Car> cars = Arrays.asList(
              new Car("BMW",3,BigDecimal.valueOf(56230.589)),
              new Car("Mercedes",116,BigDecimal.valueOf(12589.365)),
              new Car("VOLGA",5,BigDecimal.valueOf(5625.002)),
              new Car("Honda",5,BigDecimal.valueOf(125869.2504))
              );
      List<User> users = Arrays.asList(
              new User("I", "P", "i@mail.ru"),
              new User("K", "L", "k@ya.ru"),
              new User("A", "O", "a@tut.by"),
              new User("N", "V", "n@gmail.com"),
              new User("L", "I", null)
              );

      for (Car car : cars) {
         carService.addCar(car);
      }
      for (User user : users) {
         userService.add(user);
      }

      System.out.println("Посмотрим на Юзеров и машины");
      List<User>userDB = userService.listUsers();
      for (User user : userDB) {
         System.out.println(user);
      }

      List<Car> carDB = carService.listCars();
      for (Car car : carDB) {
         System.out.println(car);
      }

      userDB.get(0).addCar(carDB.get(0));
      userDB.get(1).addCar(carDB.get(1));
      userDB.get(2).addCar(carDB.get(2));
      userDB.get(3).addCar(carDB.get(3));

      for (User user : userDB) {
         userService.update(user);
      }
      System.out.println("выведем всех");
      for(User user : userService.listUsers()) {
         System.out.println(user);
      }
      System.out.println("ищем юзера по машине");
      Optional<User> chekModelUser = userService.getUserFromCarModel("VOLGA");
      System.out.println(chekModelUser.isPresent());
      System.out.println(chekModelUser.get());

      System.out.println("ищем машину по модели");
      List<Car>chekCarModel = carService.getCarsByModel("Honda");
      System.out.println(chekCarModel);

      context.close();
   }
}
