package api.registration;
import api.appointment.AppointmentService;
import api.config.CustomUserDetails;
import api.config.CustomUserDetailRepository;
import api.config.security.UserType;
import api.login.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class RegistrationController {

    @Autowired
    CustomUserDetailRepository customUserDetailRepository;

    @Autowired
    AppointmentService appointmentService;

    @PostMapping("/registration")
    public void saveUser(@RequestBody UserDto userDto) {
        final BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        userDto.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        CustomUserDetails customUserDetails = CustomUserDetails.builder()
                .username(userDto.getUsername())
                .name(userDto.getName())
                .surname(userDto.getSurname())
                .email(userDto.getEmail())
                .type(userDto.getType())
                .password(userDto.getPassword())
                .build();
        customUserDetailRepository.save(customUserDetails);
        System.out.println("geldi");

      /* if(userDto.getType()==UserType.APPOINTER)
        {
            appointmentService.create(customUserDetails);
        }*/
    }
}