package hiber.dao;

import hiber.model.User;
import java.util.List;
import java.util.Optional;

public interface UserDao {
   void add(User user);

   List<User> listUsers();

   void update(User user);

   Optional<User> getUserById(Long id);

   Optional<User> getUserFromCarModel(String model, int series);
}
