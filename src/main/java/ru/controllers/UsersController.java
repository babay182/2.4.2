package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import ru.dao.UserDao;
import ru.model.User;
import ru.service.UserService;


@Controller
@RequestMapping("/users")
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(@RequestParam(value = "id", required = false) String idStr, Model model) {
        if (idStr == null) {
            model.addAttribute("users", userService.index());
            return "users/index";
        } else {
            int id = Integer.parseInt(idStr);
            model.addAttribute("user", userService.show(id));
            return "users/show";
        }
    }


    @GetMapping("/new")
    public String newPerson(Model model){
        model.addAttribute("user", new User());
        return "users/new";
    }

    @PostMapping
    public String creat(@ModelAttribute("user") User user){
        userService.save(user);
        return "redirect:/users";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(value = "id", required = false) String ids){
        if(ids == null) System.out.println("Cavabanga");
        int id = Integer.parseInt(ids);
        model.addAttribute("user", userService.show(id));
        return "users/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user, @RequestParam(value = "id", required = false) Integer id){
        userService.update(id, user);
        return "redirect:/users";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id", required = false) Integer id){
        userService.delete(id);
        return "redirect:/users";
    }
}
