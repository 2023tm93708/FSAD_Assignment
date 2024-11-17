package wilp.bits.pilani.book_exchange_platform.controllers;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import wilp.bits.pilani.book_exchange_platform.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import wilp.bits.pilani.book_exchange_platform.services.UserService;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api/user")
public class LoginController {

    @Autowired
    private UserService userService;


    @PostMapping("/login")
    public String login(@RequestBody User user) {
        Optional<User> authenticatedUser = userService.login(user.getEmail(), user.getPassword());
        System.out.println("\n\n\n"+user.getName()+"\n\n\n");
        System.out.println("\n\n\n"+authenticatedUser.get().getName()+"\n\n\n");
        return authenticatedUser.isPresent() ? "Login successful "+authenticatedUser.get().getName()  : "Invalid credentials of "+user.getEmail();
    }

    @PostMapping("/register")
    public String register(@RequestBody User user) {
        userService.register(user);
        return "Registration successful";
    }

    @GetMapping("/id")
    public ResponseEntity<?> getUserByUsername(@RequestParam String username) {
        User user = userService.getUserDetails(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
    @PutMapping("/update-password")
    public ResponseEntity<?> updatePassword(@RequestParam String username, @RequestParam String newPassword) {
        System.out.println("\n\n/update-password\n\nusername\t"+username+"\t\tnew password\t"+newPassword+"\n\n");
        boolean isUpdated = userService.updatePassword(username, newPassword);
        if (isUpdated) {
            return ResponseEntity.ok("Password updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }
}