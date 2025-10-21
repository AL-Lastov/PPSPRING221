package hiber.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   private Long id;

   @Column(name = "name")
   private String firstName;

   @Column(name = "last_name")
   private String lastName;

   @Column(name = "email")
   private String email;

//   @OneToOne(cascade = CascadeType.ALL)
//   @JoinColumn(name = "car_id")
//   private Car car;
@ElementCollection // Указывает, что это коллекция встраиваемых объектов :cite[10]
@CollectionTable( // Создает отдельную таблицу для хранения машин
        name = "user_cars",
        joinColumns = @JoinColumn(name = "user_id") // Внешний ключ к users
)
private List<Car> cars = new ArrayList<>(); // У каждого пользователя список машин

   public User() {}
   
   public User(String firstName, String lastName, String email) {
      this.firstName = firstName;
      this.lastName = lastName;
      this.email = email;
   }

   // Метод для добавления машины пользователю
   public void addCar(Car car) {
      cars.add(car);
   }

   public Long getId() {
      return id;
   }

   public void setId(Long id) {
      this.id = id;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }

   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public List<Car> getCars() {
      return cars;
   }

   public void setCars(List<Car> cars) {
      this.cars = cars;
   }
}
