package web.config;

import org.springframework.stereotype.Component;
import web.model.User;
import web.service.UserService;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DataInitializer {
    private final UserService userService;

    public DataInitializer(UserService userService) {
        this.userService = userService;
    }

    @PostConstruct
    public void init() {
        List<User> users = userService.listUsers();
        if (users.isEmpty()) { // Проверяем, есть ли уже пользователи
            userService.addUser(new User("Alex", "Petrov"));
            userService.addUser(new User("Yura", "Borisov"));
            userService.addUser(new User("Lev", "Tolstoy"));
            System.out.println("Тестовые пользователи добавлены!");
        } else {
            System.out.println("Пользователи уже есть в базе.");
        }
    }
}
