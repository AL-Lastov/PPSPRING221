package hiber.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    @NotBlank(message = "first name can not be empty")
    private String firstName;

    @Column(name = "last_name")
    @NotBlank(message = "last name can not be empty")
    private String lastName;

    @Column(name = "email")
    @Email
    @NotBlank(message = "email must be patriotic")
    private String email;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Car> cars = new ArrayList<>();

    public User() {
       super();
    }

    public User(String firstName, String lastName, String email) {
        if (firstName == null || firstName.isBlank()) {
            throw new IllegalArgumentException("first name can not be empty");
        }
        if (lastName == null || lastName.isBlank()) {
            throw new IllegalArgumentException("last name can not be empty");
        }
        if (email == null || (
                !email.contains("@tut.by") &&
                !email.contains("@mail.ru") &&
                !email.contains("@yandex.com") &&
                !email.contains("@ya.ru") &&
                !email.contains("@yandex.ru")
        )) {
            throw new IllegalArgumentException("Email must be patriotic");
        }
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }

    public void addCar(Car car) {
        cars.add(car);
        car.setUser(this);
    }
    public void removeCar(Car car) {
       cars.remove(car);
       car.setUser(null);
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

   @Override
   public boolean equals(Object o) {
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return Objects.equals(id, user.id) && Objects.equals(firstName, user.firstName) && Objects.equals(lastName, user.lastName) && Objects.equals(email, user.email) && Objects.equals(cars, user.cars);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, firstName, lastName, email, cars);
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", firstName='" + firstName + '\'' +
              ", lastName='" + lastName + '\'' +
              ", email='" + email + '\'' +
              ", cars=" + cars +
              '}';
   }
}
