package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.CarService;
import hiber.service.UserService;
import org.hibernate.SessionFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.List;

public class MainApp {
   public static void main(String[] args) throws SQLException {
      AnnotationConfigApplicationContext context = 
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      CarService carService = context.getBean(CarService.class);

      Car car1 = new Car("VAZ", 2109);
      Car car2 = new Car("MERCEDES", 5);
      Car car3 = new Car("KOPEIKA", 2101);
      Car car4 = new Car("GAZEL", 90);

      carService.add(car1);
      carService.add(car2);
      carService.add(car3);
      carService.add(car4);

      User user1 = new User("Petr", "Ivanov", "yandex@mail.ru", car1);
      User user2 = new User("Kolya", "Sidorov", "yahoo@mail.ru", car2);
      User user3 = new User("Stas", "Petrov", "vkontakte@mail.ru", car3);
      User user4 = new User("Igor", "Koshei", "odnoklassniki@mail.ru", car4);

      user1.setCar(car1);
      user2.setCar(car2);
      user3.setCar(car3);
      user4.setCar(car4);

      userService.add(user1);
      userService.add(user2);
      userService.add(user3);
      userService.add(user4);

      List<User> users = userService.listUsers();
      for (User user : users) {
         System.out.println("Id = "+user.getId());
         System.out.println("First Name = "+user.getFirstName());
         System.out.println("Last Name = "+user.getLastName());
         System.out.println("Email = "+user.getEmail());
         System.out.println("Car model = " + user.getCar().getModel());
         System.out.println("Car series = " + user.getCar().getSeries());
         System.out.println();
      }

      System.out.println(carService.getUserFromModelAndSeries("VAZ", 2109));

      context.close();
   }
}
