package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru"));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru"));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru"));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru"));

      Car car = new Car ("Mercedes", 220, BigDecimal.valueOf(256258.35));
      Car car1 = new Car ("Mercedes", 211, BigDecimal.valueOf(56235.23000));
      Car car2 = new Car ("Mercedes", 5, BigDecimal.valueOf(2658.000));
      Car car3 = new Car ("Mercedes", 3, BigDecimal.valueOf(84256.8101));
      Car car4 = new Car ("Mercedes", 7, BigDecimal.valueOf(12587.0025));


      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println();
      }

      users.get(0).addCar(car);
      users.get(0).addCar(car1);
      users.get(1).addCar(car2);
      users.get(3).addCar(car3);
      users.get(3).addCar(car4);

      for (User user : users) {
         userService.update(user);
      }
// Проверяем результат
      List<User> updatedUsers = userService.listUsers();
      for (User user : updatedUsers) {
         System.out.println("Id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("Email = " + user.getEmail());

         System.out.println("Cars: ");

         // ПРОВЕРЯЕМ ДО цикла!
         if (user.getCars() == null || user.getCars().isEmpty()) {
            System.out.println("А я по шпалам, а я по шпалам идуууу");
         } else {
            // Только если есть машины - заходим в цикл
            for (Car cars : user.getCars()) {
               System.out.println("  - " + cars.getModel() + " " + cars.getSeries() +
                       ", Price: " + cars.getPrice());
            }
         }
         System.out.println();
      }





      context.close();
   }
}
