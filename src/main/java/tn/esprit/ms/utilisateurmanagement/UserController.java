package tn.esprit.ms.utilisateurmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/UtilisateurManagement")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    // User registration
    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        // Check if the email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(400).body("Email already exists");
        }

        // Generate OTP
        String otp = otpService.generateOtp(user.getEmail());
        // Send OTP to email
        emailService.sendOtp(user.getEmail(), otp);

        // Save user temporarily for verification
        user.setPassword(passwordEncoder.encode(user.getPassword())); // Ensure the password is encoded
        user.setVerified(false); // Assuming you have a 'verified' field
        userRepository.save(user); // Save user to database

        return ResponseEntity.ok("Registration successful. Please verify your email with the OTP sent.");
    }

    // Email verification
    @PostMapping("/verify/email")
    public ResponseEntity<String> verifyEmail(@RequestParam String email, @RequestParam String otp) {
        boolean isValid = otpService.validateOtp(email, otp);
        if (isValid) {
            User user = userRepository.findByEmail(email);
            if (user != null) {
                user.setVerified(true); // Mark as verified
                userRepository.save(user); // Update user in database
                return ResponseEntity.ok("Email verified successfully.");
            }
        }
        return ResponseEntity.badRequest().body("Invalid OTP. Please try again.");
    }
    @PostMapping("/signup")
    public ResponseEntity<LoginMessage> createUser(@RequestBody User user) {
        System.out.println("Creating user: " + user.getEmail());

        if (userRepository.findByEmail(user.getEmail()) != null) {
            return ResponseEntity.status(400).body(new LoginMessage("Email already exists", false));
        }
        if (user.getPassword() == null || user.getPassword().isEmpty()) {
            return ResponseEntity.status(400).body(new LoginMessage("Password is required", false));
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(user);
        return ResponseEntity.ok(new LoginMessage("User created successfully", true));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginMessage> loginEmployee(@RequestBody LoginDTO loginDTO) {
        User user = userRepository.findByEmail(loginDTO.getEmail());
        if (user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return ResponseEntity.ok(new LoginMessage("Login Successful", true));
        } else {
            return ResponseEntity.status(401).body(new LoginMessage("Invalid email or password", false));
        }
    }

    // Lire tous les utilisateurs
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Lire un utilisateur par ID
    @GetMapping("/getbyID/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Integer id) {
        Optional<User> user = userRepository.findById(id);
        return user.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Mettre à jour un utilisateur
    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User userDetails) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            User existingUser = user.get();
            existingUser.setNom(userDetails.getNom());
            existingUser.setPrenom(userDetails.getPrenom());
            existingUser.setEmail(userDetails.getEmail());
            existingUser.setPassword(userDetails.getPassword());
            userRepository.save(existingUser);
            return ResponseEntity.ok(existingUser);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Supprimer un utilisateur
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}