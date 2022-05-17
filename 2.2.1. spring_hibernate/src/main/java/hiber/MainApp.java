package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.persistence.NoResultException;
import java.util.Arrays;
import java.util.List;

public class MainApp {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(AppConfig.class);

        UserService userService = context.getBean(UserService.class);

        Car car1 = new Car("Mercedes", 1);
        Car car2 = new Car("Maybah", 2);
        Car car3 = new Car("Ford", 3);
        Car car4 = new Car("Audi", 4);

        User user1 = new User("Igor", "Kuznetsov", "kuznetsov@mail.ru");
        User user2 = new User("Anzhelika", "Volodina", "Volodina@mail.ru");
        User user3 = new User("Petr", "Pupkin", "pupkin@mail.ru");
        User user4 = new User("Elena", "Batkovna", "batka1981@mail.ru");

        userService.add(user1.setCar(car1).setUser(user1));
        userService.add(user2.setCar(car2).setUser(user2));
        userService.add(user3.setCar(car3).setUser(user3));
        userService.add(user4.setCar(car4).setUser(user4));

        List<User> users = userService.listUsers();
        System.out.println("Пользователи и их машины: ");
        for (User user : users) {
            System.out.println("Id = " + user.getId());
            System.out.println("First Name = " + user.getFirstName());
            System.out.println("Last Name = " + user.getLastName());
            System.out.println("Email = " + user.getEmail());
            System.out.println("Car = " + user.getCar());
            System.out.println();
        }
        System.out.println("************************************************");

        System.out.println("Машины:");
        for (User user : userService.listUsers()) {
            System.out.println("user = " + user.getCar());
        }
        System.out.println("************************************************");

        System.out.println("Пользователь с машиной: ");
        System.out.println(userService.getUserByCar("Mercedes", 1));
        System.out.println("************************************************");

        try {
            User notFoundUser = userService.getUserByCar("Tesla", 5);
        } catch (NoResultException e) {
            System.out.println("Пользователь не найден!");
            System.out.println("************************************************");
        }

        context.close();
    }
}
