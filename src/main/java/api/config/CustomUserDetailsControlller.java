package api.config;

import api.appointment.Appointment;
import api.appointment.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CustomUserDetailsControlller {
    @Autowired
    private CustomUserDetailsService customUserDetailsService;
    public CustomUserDetailsControlller() {
    }

    public CustomUserDetailsControlller(CustomUserDetailsService customUserDetailsService) {
        this.customUserDetailsService = customUserDetailsService;
    }
    @GetMapping("/getAllAppointer")
    public List<CustomUserDetails> getAllAppointer() {
        return customUserDetailsService.getAllAppointer();
    }

    @GetMapping("/getAppointerByJob/{job}")
    public CustomUserDetails getAppointerBySurname(@PathVariable String surname) {
        return customUserDetailsService.getAppointerBySurname(surname);
    }
    @GetMapping("/getAppointerById/{id}")
    public CustomUserDetails getAppointerById(@PathVariable Long id) {
        return customUserDetailsService.getAppointerById(id);
    }

    @GetMapping("/getCurrentUser")
    public CustomUserDetails getCurrentUser() {
        return customUserDetailsService.getCurrentUser();
    }
}
