package ru.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import ru.model.Role;
import ru.model.User;
import ru.service.UserService;

import java.util.Set;


@Controller
@RequestMapping("/admin")
public class AdminController {

    private final UserService userService;

    @Autowired
    public AdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String index(@RequestParam(value = "id", required = false) String idStr, Model model) {
        if (idStr == null) {
            model.addAttribute("users", userService.index());
            return "admin/index";
        } else {
            int id = Integer.parseInt(idStr);
            model.addAttribute("user", userService.show(id));
            return "admin/show";
        }
    }


    @GetMapping("/new")
    public String newPerson(Model model){
        Set<Role> roles = userService.getRoles();
        model.addAttribute("roles", roles);
        User user = new User();
        model.addAttribute("user", user);
        return "admin/new";
    }

    @PostMapping("/new")
    public String creat(@ModelAttribute("user") User user
            , @RequestParam(value = "rolesSelected", required = false) String[] roles){
        for (String role : roles) {
            Role i = userService.getRole(Integer.parseInt(role));
            user.addRole(i);
        }
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/edit")
    public String edit(Model model, @RequestParam(value = "id", required = false) String ids){
        if(ids == null) System.out.println("Cavabanga");
        int id = Integer.parseInt(ids);
        model.addAttribute("user", userService.show(id));
        Set<Role> roles = userService.getRoles();
        model.addAttribute("roles", roles);
        return "admin/edit";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute("user") User user
            , @RequestParam(value = "id", required = false) Integer id
            , @RequestParam(value = "rolesSelected", required = false) String[] roles){
        for (String role : roles) {
            Role i = userService.getRole(Integer.parseInt(role));
            user.addRole(i);
        }
        userService.update(id, user);
        return "redirect:/admin";
    }
    @PostMapping("/delete")
    public String delete(@RequestParam(value = "id", required = false) Integer id){
        userService.delete(id);
        return "redirect:/admin";
    }

}
