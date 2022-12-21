package org.example.Web2.controllers;

import org.example.Web2.domain.Role;
import org.example.Web2.domain.User;
import org.example.Web2.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Set;

@Controller
public class AdminController {
    @Autowired
    private UserRepo userRepo;

    @GetMapping("/admin")
    public String admin(Model model, @AuthenticationPrincipal User user) {
        if (user.isAdmin()) {
            Iterable<User> users = userRepo.findAll();
            model.addAttribute("users", users);
            return "admin";
        }
        else
            return "error/error-403";
    }

    @GetMapping("/admin/ban/{id}")
    public String ban(Model model, @PathVariable ("id") Long userId) {
        User user = userRepo.findByUserId(userId);
        user.setRoles(Role.BANNED);
        userRepo.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/admin/unban/{id}")
    public String unban(Model model, @PathVariable ("id") Long userId) {
        User user = userRepo.findByUserId(userId);
        Set<Role> roles = user.getRoles();
        roles.remove(Role.BANNED);
        user.setRoles(roles);
        userRepo.save(user);
        return "redirect:/admin";
    }
}
