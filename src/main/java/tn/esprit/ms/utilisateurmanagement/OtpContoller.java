package tn.esprit.ms.utilisateurmanagement;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/otp")
public class OtpContoller {

    @Autowired
    private OtpService otpService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private SmsService smsService;



    @PostMapping("/send/sms")
    public ResponseEntity<String> sendOtpSms(@RequestParam String phoneNumber) {
        try {
            String otp = otpService.generateOtp(phoneNumber);
            smsService.sendOtp(phoneNumber, otp);
            return ResponseEntity.ok("OTP sent to " + phoneNumber);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to send OTP: " + e.getMessage());
        }
    }

    @PostMapping("/verify")
    public String verifyOtp(@RequestParam String key, @RequestParam String otp) {
        return otpService.validateOtp(key, otp) ? "OTP Verified Successfully." : "Invalid OTP.";
    }

    @PostMapping("/send/email")
    public String sendOtpEmail(@RequestParam String email) {
        String otp = otpService.generateOtp(email);
        emailService.sendOtp(email, otp);
        return "OTP sent to your email. Please check your inbox.";
    }
}