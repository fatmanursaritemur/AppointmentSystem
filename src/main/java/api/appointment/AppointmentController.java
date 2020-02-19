package api.appointment;

import api.config.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api/v1/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    public AppointmentController() {
    }

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    @GetMapping("/findAppointmentById/{appointmentId}")
    public Optional<Appointment> findById(@PathVariable Long appointmentId) {
        return appointmentService.findById(appointmentId);
    }
    @GetMapping("/waitforapproval")
    public List<Appointment> getWaitforapprovalAppointment() {
        return appointmentService.getAllWaitForApprovalAppointment();
    }

    @GetMapping("/denied")
    public List<Appointment> getAllDeniedAppointment() {
        return appointmentService.getAllDeniedAppointment();
    }

    @GetMapping("/approval")
    public List<Appointment> getAllApprovalAppointment() {
        return appointmentService.getAllApprovalAppointment();
    }

    @GetMapping(path = "findAllAppointment/appointmentId")
    List<Appointment> findAll() {
        return appointmentService.findAll();
    }


    @PostMapping("/saveappointment/{appointmentId}")
    public Appointment saveAppointmentByAppointer(@PathVariable Long appointmentId) {
        return appointmentService.saveAppointmentbyAppoiter(appointmentId);
    }

    @GetMapping(path ="/denidappointment/{appointmentId}")
    public Appointment deniedAppointmentByAppointer(@PathVariable Long appointmentId) {
        return appointmentService.deniedAppointmentbyAppoiter(appointmentId);
    }


    @GetMapping(path ="/requestAppointmentbyCustomer/{appointmentId}")
    public Appointment requestAppointmentbyCustomer(@PathVariable Long appointmentId ) {
        return appointmentService.requestAppointmentbyCustomer(appointmentId);
    }

    @GetMapping(path ="/getAppointmentByBetweenTwoDate//{appointer}")
    public List<Appointment> getAppointmentByBetweenTwoDate(@PathVariable("appointer") String appointerid) {
        return appointmentService.getAppointmentByBetweenTwoDate(appointerid);
    }
        @GetMapping(path ="/getLocalDateByBetweenTwoDate")
        public List<LocalDate> getLocalDateByBetweenTwoDate() {
            return appointmentService.getLocalDateByBetweenTwoDate();
        }

          /*  @GetMapping(path ="/getAppointmentByAppointmentDate/{localdate}")
            public List<Appointment> getAppointmentByAppointmentDate(@PathVariable LocalDate localdate) {
        return appointmentService.getAppointmentByAppointmentDate(localdate);
    }
    @GetMapping(path ="/getPeriodByBAppointer/{appointer}/{localdate}")
    public  List<String> getPeriodByBAppointer(@PathVariable("appointer") String appointerid, @PathVariable("localdate") String localdate ) {
        return appointmentService.getPeriodByBAppointer(appointerid,localdate);
    }
*/
    @GetMapping(path ="/getAppointmentByBAppointer/{appointerid}/{localdate}")
    public  List<Appointment> getAppointmentByBAppointer(@PathVariable String apointerid,@PathVariable Calendar localdate ) {
        return appointmentService.getAppointmentByBAppointer(apointerid,localdate); // calendar+ period silindi
    }
    @GetMapping(path="/getappointmentCandidateList/{starttime}")
    public List<AppointmentCandidate> getappointmentCandidateList(@PathVariable Calendar starttime)
    {
        return appointmentService.generateAvaibleSchedule("1",starttime);
    }
}