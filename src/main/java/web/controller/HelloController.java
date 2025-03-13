package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import web.model.User;
import web.service.UserService;

import java.util.List;

@Controller
public class HelloController {

	private UserService userService;

	public HelloController(web.service.UserService userService) {
		this.userService = userService;
	}

    @GetMapping(value = "/")
	public String printUsersList(ModelMap model) {
		List<User> userList = userService.listUsers();
		model.addAttribute("allUsers", userList);
		return "users";
	}

	@GetMapping("/add")
	public String showAddUserForm(Model model) {
		model.addAttribute("user", new User());  // Пустой объект User для формы
		return "user-form";  // Страница с формой добавления
	}

	@PostMapping("/add")
	public String addUser(@ModelAttribute User user) {
		userService.addUser(user);  // Сохраняем пользователя
		return "redirect:/";  // Перенаправляем на страницу со списком пользователей
	}

	@GetMapping("/edit")
	public String showEditUserForm(@RequestParam("id") int id, Model model) {
		User user = userService.getUser(id);  // Получаем пользователя по ID
		model.addAttribute("user", user);
		return "user-form";  // Страница с формой редактирования
	}

	@PostMapping("/edit")
	public String updateUser(@RequestParam("id") int id, @ModelAttribute User user) {
		user.setId(id);  // Устанавливаем ID пользователя для обновления
		userService.addUser(user);  // Сохраняем обновлённого пользователя
		return "redirect:/";  // Перенаправляем на страницу со списком пользователей
	}

	@GetMapping("/delete")
	public String deleteUser(@RequestParam("id") int id) {
		userService.deleteUser(id);  // Удаляем пользователя
		return "redirect:/";  // Перенаправляем на страницу со списком пользователей
	}

}