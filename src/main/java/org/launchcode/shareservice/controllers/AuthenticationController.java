package org.launchcode.shareservice.controllers;

import org.launchcode.shareservice.data.UserRepository;
import org.launchcode.shareservice.models.User;
import org.launchcode.shareservice.models.dto.LoginFormDTO;
import org.launchcode.shareservice.models.dto.RegisterFormDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.Optional;

@Controller
public class AuthenticationController {

    @Autowired
    private UserRepository userRepository;

    //    It is the key to store user IDs.
    private static final String userSessionKey = "user";

    //    To store key/value pair.
    private static void setUserInSession(HttpSession session, User user) {
        session.setAttribute(userSessionKey, user.getId());
    }

    //    To look for data with the key-user in the user's session.
    public User getUserFromSession(HttpSession session) {
        Integer userId = (Integer) session.getAttribute(userSessionKey);
        if (userId == null) {
            return null;
        }

        Optional<User> user = userRepository.findById(userId);

        if (user.isEmpty()) {
            return null;
        }

        return user.get();
    }


//    The handler will be available at the route /register, and will respond to GET requests.
    @GetMapping("/register")
    public String displayRegistrationForm(Model model) {
        model.addAttribute("registerFormDTO", new RegisterFormDTO());
        model.addAttribute("title", "Register");
//        Returns the name of the template containing the form.
        return "/register/register";
    }

    @PostMapping("/register")
    public String processRegistrationForm(@ModelAttribute @Valid RegisterFormDTO registerFormDTO,
                                          Errors errors, HttpServletRequest request,
                                          Model model) {
//        Return the user to the form if an validation errors occur.
        if (errors.hasErrors()) {
            model.addAttribute("title", "Register");
            return "/register/register";
        }

//        Retrieve the user with the given username from the database.
        User existingUser = userRepository.findByName(registerFormDTO.getName());

        if (existingUser != null) {
            errors.rejectValue("name", "name.alreadyexists", "A user with that username already exists");
            model.addAttribute("title", "Register");
            return "/register/register";
        }

//        Compare the two passwords submitted.
        String password = registerFormDTO.getPassword();
        String verifyPassword = registerFormDTO.getVerifyPassword();
        if (!password.equals(verifyPassword)) {
            errors.rejectValue("password", "passwords.mismatch", "Passwords do not match");
            model.addAttribute("title", "Register");
            return "/register/register";
        }

//At this point, we know that a user with the given username does NOT already exist,
// and the rest of the form data is valid.
// So we create a new user object, store it in the database,
// and then create a new session for the user.
        User newUser = new User(registerFormDTO.getName(), registerFormDTO.getPassword());
        userRepository.save(newUser);
        setUserInSession(request.getSession(), newUser);

//        Redirect the user to the main page
        return "redirect:";
    }

    @GetMapping("/login")
    public String displayLoginForm(Model model) {
        model.addAttribute("loginFormDTO", new LoginFormDTO());
        model.addAttribute("title", "Log In");
        return "/login/login";
    }

    @PostMapping("/login")
    public String processLoginForm(@ModelAttribute @Valid LoginFormDTO loginFormDTO,
                                   Errors errors, HttpServletRequest request,
                                   Model model) {

        if (errors.hasErrors()) {
            model.addAttribute("title", "Log In");
            return "/login/login";
        }

//        Retrieves the User object with the given password from the database.
        User theUser = userRepository.findByName(loginFormDTO.getName());

//        If no such user exists, register a custom error and return to the form.
        if (theUser == null) {
            errors.rejectValue("name", "user.invalid", "The given username does not exist");
            model.addAttribute("title", "Log In");
            return "/login/login";
        }

//        Retrieves the submitted password from the form DTO.
        String password = loginFormDTO.getPassword();

//        If the password is incorrect, register a custom error and return to the form.
        if (!theUser.isMatchingPassword(password)) {
            errors.rejectValue("password", "password.invalid", "Invalid password");
            model.addAttribute("title", "Log In");
            return "/login/login";
        }

//        At this point, we know the given user exists and that the submitted password is correct.
//        So we create a new session for the user.
        model.addAttribute("title", "Success|Welcome: " + loginFormDTO.getName());

        setUserInSession(request.getSession(), theUser);

//        Direct the user to the welcome page.
        return "/login/welcome";
    }

//    Invalidate the session associated with the given user.
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) {
        request.getSession().invalidate();
        return "redirect:/login";
    }

}
