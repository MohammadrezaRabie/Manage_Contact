package com.example.contact.controller;

import com.example.contact.business.UserBusiness;
import com.example.contact.business.UserValidation;
import com.example.contact.entity.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Objects;


@Controller
@RequestMapping("/contact")
public class UserController {

    private final UserBusiness userBusiness;
    private final UserValidation userValidation;

    public UserController(UserBusiness userBusiness, UserValidation userValidation) {
        this.userBusiness = userBusiness;
        this.userValidation = userValidation;
    }

    @GetMapping
    public String home() {
        return "home";
    }

    @GetMapping("/signup")
    public String signup() {
        return "signup";
    }

    @GetMapping("/change-password")
    public String forgetPassword() {
        return "change_password";
    }

    @GetMapping("/delete-account")
    public String deleteAccount() {
        return "delete_account";
    }


    @PostMapping("/adduser")
    public String addUser(User user, ModelMap model, RedirectAttributes redirectAttributes) {

        user.setUserName(user.getUserName().trim());
        user.setPassword(user.getPassword().trim());

        if (!userValidation.userExist(user.getUserName())) {
            userBusiness.saveUser(user);
            redirectAttributes.addFlashAttribute("signupOk", "Signup Successfully");
            return "redirect:/contact";
        } else {
            model.put("userError", "Error ! Username : " + "' " + user.getUserName() + " '" + " has exist ! Please choose another Username ");
            return "signup";
        }
    }

    @PostMapping("/contactpage")
    public String loginUser(User user, HttpSession session, ModelMap model) {

        user.setUserName(user.getUserName().trim());
        user.setPassword(user.getPassword().trim());

        User user1 = userValidation.checkPassword(user.getPassword(), user.getUserName());

        if (Objects.isNull(user1)) {
            model.put("userPassError", "Error ! Username or Password is Incorrect ! Please try again ");
            return "home";
        } else {
            session.setAttribute("userSession", user1.getUserName());
            session.setAttribute("user", user1);
            return "redirect:/user-contacts";
        }

    }

    @PostMapping("/delete-account")
    public String deleteAccountUser(User user, ModelMap model, RedirectAttributes redirectAttributes) {

        User user1 = userValidation.checkPassword(user.getPassword(), user.getUserName());

        if (Objects.isNull(user1)) {
            model.put("userPassError", "Error ! Username or Password is Incorrect ! Please try again ");
            return "delete_account";
        } else {
            userBusiness.deleteUser(user1);
            redirectAttributes.addFlashAttribute("userDeleteOk", "Delete User Successfully");
            return "redirect:/contact";
        }
    }

    @PostMapping("/change-password")
    public String changePassword(HttpServletRequest request, User user, ModelMap model,
                                 RedirectAttributes redirectAttributes) {

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String newPassword = request.getParameter("new_password");
        user.setUserName(username.trim());
        user.setPassword(password.trim());

        User user1 = userValidation.checkPassword(password, username);

        if (Objects.isNull(user1)) {
            model.put("userPassError", "Error ! Username or Password is Incorrect ! Please try again ");
            return "change_password";
        } else {
            User user2 = userBusiness.findUserByUserName(username);
            user2.setPassword(newPassword);
            userBusiness.saveUser(user2);
            redirectAttributes.addFlashAttribute("changePassOk", "Change Password Successfully");
            return "redirect:/contact";
        }

    }
}
