package tn.esprit.ms.utilisateurmanagement;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class SmsService {

    @Value("${twilio.account.sid}")
    private String accountSid;

    @Value("${twilio.auth.token}")
    private String authToken;

    @Value("${twilio.phone.number}")
    private String twilioPhoneNumber;

    public void sendOtp(String phoneNumber, String otp) {
        Twilio.init(accountSid, authToken);

        // Ensure that phoneNumber does not include the '+' sign if you're adding it in the code
        Message.creator(
                new com.twilio.type.PhoneNumber(phoneNumber), // Recipient's phone number
                new com.twilio.type.PhoneNumber(twilioPhoneNumber), // Your Twilio phone number
                "Your OTP code is: " + otp
        ).create();
    }
}