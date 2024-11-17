package wilp.bits.pilani.book_exchange_platform.services;


import wilp.bits.pilani.book_exchange_platform.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import wilp.bits.pilani.book_exchange_platform.repositories.UserRepository;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        System.out.println("\n\n\ntest login\n\n"+ user.get().getName());

        if (user.isPresent() && password.equals(user.get().getPassword())) {
            System.out.println("\n\n\n\n"+user.get().getEmail());
            return user;
        }
        return Optional.empty();
    }

    public User register(User user) {
        user.setEmail(user.getEmail());
        user.setPassword(user.getPassword());
        return userRepository.save(user);
    }

    public User getUserDetails(String username) {
        Optional<User> user = userRepository.findByName(username);
        if (user.isPresent()) {
            System.out.println("\n\ngetUserDetails\n\n"+user.get().getEmail());
            return user.get();
        }
        return null;
    }

    public boolean updatePassword(String username, String newPassword) {
        Optional<User> userOptional = userRepository.findByName(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPassword(newPassword);
            userRepository.save(user);
            return true;
        }
        return false;
    }
}