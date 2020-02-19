package api.appointment;


import api.config.CustomUserDetails;
import api.login.UserDto;

import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface AppointmentService {

     Appointment saveAppointmentbyAppoiter(Long appointmentid);

    Appointment deniedAppointmentbyAppoiter(Long appointmentid);
    Optional<Appointment> findById(Long appointmentId);
    List<Appointment> findAll();
    List<Appointment> getAllWaitForApprovalAppointment();
     List<Appointment> getAllDeniedAppointment();
     List<Appointment> getAllApprovalAppointment();
    Appointment requestAppointmentbyCustomer(Long appointmentId);
    Appointment create(CustomUserDetails customUserDetails);
    List<Appointment>  getAppointmentByBetweenTwoDate(String Appointerid);
    List<LocalDate>  getLocalDateByBetweenTwoDate();
    //List<Appointment>  getAppointmentByAppointmentDate(LocalDate appoiLocalDate);
    // List<String> getPeriodByBAppointer(String appointerid, String localdate);
    List<Appointment> getAppointmentByBAppointer(String appointerid, Calendar localdate);// period sildim, Calendar oldu
     List<AppointmentCandidate> generateAvaibleSchedule(String appointerid,Calendar starttime);
   // List<Date>  getAllStartTimeByBAppointer(CustomUserDetails appointer, Calendar localdate);
    Boolean isavaiblestarttime(String appointerid, Calendar localdate);
}
