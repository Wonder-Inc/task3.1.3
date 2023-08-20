package ru.itm.restapp.controller;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.itm.restapp.model.User;
import ru.itm.restapp.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    
    public AdminController(UserService userService) {
        this.userService = userService;
    }
    
    @GetMapping
    public String showAdminInfo(ModelMap modelMap, Principal principal) {
        modelMap.addAttribute("user", userService.loadUserByUsername(principal.getName()));
        return "admin";
    }
    
    @GetMapping("/allUsers")
    public String listUsers(ModelMap modelMap) {
        modelMap.addAttribute("users", userService.listUsers());
        return "admin/allUsers";
    }
    
    @GetMapping("/new")
    public String getForm(@ModelAttribute("user") User user) {
        return "admin/new";
    }
    
    @PostMapping
    public String create(@ModelAttribute("user") @Valid User user,
                         @RequestParam("allRoles") List<Long> ids) {
        userService.create(user, ids);
        return "redirect:/admin/allUsers";
    }
    
    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        userService.delete(id);
        return "redirect:/admin/allUsers";
    }
    
    @GetMapping("/{id}/edit")
    public String updateForm(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.showUser(id));
        return "admin/edit";
    }
    
    @PutMapping("/{id}")
    public String edit(@Valid User user, @RequestParam("rolesIds") List<Long> rolesIds,
                       @PathVariable("id") Long userId) {
        userService.update(user, rolesIds, userId);
        return "redirect:/admin/allUsers";
    }
    
    @GetMapping("/{id}")
    public String showUserInfo(@PathVariable("id") Long id, ModelMap modelMap) {
        modelMap.addAttribute("user", userService.showUser(id));
        return "admin/showUser";
    }
}
