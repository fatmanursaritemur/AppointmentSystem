package api.appointment;

import api.UserService;
import api.config.CustomUserDetailRepository;
import api.config.CustomUserDetails;
import api.config.CustomUserDetailsService;
import api.login.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Component("appointmentService")
public class AppointmentServiceImplementation implements AppointmentService {
    static final long ONE_MINUTE_IN_MILLIS=60000;//millisecs
    @Autowired
    AppointmentRepository appointmentRepository;

    @Autowired
    AppointmentDateRepository appointmentDateRepository;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    CustomUserDetailRepository customUserDetailRepository;
    @Autowired
    UserService userService;

    @Override
    public List<Appointment> getAllWaitForApprovalAppointment() {
        return appointmentRepository.getwaitingappointment(customUserDetailsService.getCurrentUser());
    }

    @Override
    public List<Appointment> getAllDeniedAppointment() {
        return appointmentRepository.getdeniedappointment(customUserDetailsService.getCurrentUser());
    }

    @Override
    public List<Appointment> getAllApprovalAppointment() {
        return appointmentRepository.getapprovaledappointment(customUserDetailsService.getCurrentUser());
    }

    @Override
    public Appointment create(CustomUserDetails customUserDetails) // sikintili
    {
       LocalDate timee;
        Calendar calendar = Calendar.getInstance();
        //String period = "";
        ZoneId z = ZoneId.of("America/Montreal");
        YearMonth ym = YearMonth.now(z);
        LocalDate firstOfMonth = ym.atDay(1);
        for (int i = 0; i < 30; i++) {
            LocalDate dayAfter = firstOfMonth.plusDays(i);
           timee=dayAfter;
            calendar.set(timee.getYear(), timee.getMonthValue()-1, timee.getDayOfMonth());
            for (int j = 8; j < 20; j++) {
                   AppointmentDate appointmentDate = new AppointmentDate();
                   appointmentDate.setLocalDate(calendar);
                   Appointment appointment = new Appointment();
                   appointment.setAppointer(customUserDetails);
                  // period = j + ".00";
                  // appointmentDate.setPeriod(period);
                   appointment.setAppointmentDate(appointmentDate);
                   appointmentDateRepository.save(appointmentDate);
                   appointmentRepository.save(appointment);
                //period =j + ".30";
                AppointmentDate appointmentDate1=new AppointmentDate();
                appointmentDate1.setLocalDate(calendar);
                Appointment appointment1=new Appointment();
                appointment1.setAppointer(customUserDetails);
               // appointmentDate1.setPeriod(period);
                appointment1.setAppointmentDate(appointmentDate1);
                appointmentDateRepository.save(appointmentDate1);
                appointmentRepository.save(appointment1);
            }
        }
            return null;
    }


    @Override
    public List<Appointment> getAppointmentByBetweenTwoDate(String AppointerId) {
        ZoneId z = ZoneId.of("America/Montreal");
        YearMonth ym = YearMonth.now(z);
        LocalDate startdlocalate = ym.atDay(1);
        LocalDate endlocaldate= ym.atDay(10);
     return appointmentRepository.getAppointmentByBetweenTwoDate(customUserDetailRepository.getAppointerById(Long.valueOf(AppointerId)),startdlocalate,endlocaldate);
    }

    @Override
    public List<LocalDate> getLocalDateByBetweenTwoDate() {
        ZoneId z = ZoneId.of("America/Montreal");
        YearMonth ym = YearMonth.now(z);
        Calendar cal = Calendar.getInstance();
        int startday=cal.get(Calendar.DATE);
        LocalDate startdlocalate = ym.atDay(startday);
        LocalDate endlocaldate= startdlocalate.plusDays(10);
        return appointmentRepository.getLocalDateByBetweenTwoDate(startdlocalate,endlocaldate);
    }

   /* @Override
    public List<Appointment> getAppointmentByAppointmentDate(LocalDate appoiLocalDate) {
        return appointmentRepository.getAppointmentByAppointmentDate(appoiLocalDate);
    }

    @Override
    public List<String> getPeriodByBAppointer(String appointerid, String localdate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate localDate = LocalDate.parse(localdate, formatter);
        return appointmentRepository.getAppointmentByBAppointer(customUserDetailRepository.getAppointerById(Long.valueOf(appointerid).longValue()),localDate);

    }
*/
    @Override
    public List<Appointment> getAppointmentByBAppointer(String appointerid, Calendar localdate) {
        return  appointmentRepository.getAppointmentByBAppointer(customUserDetailRepository.getAppointerById(Long.valueOf(appointerid).longValue()),localdate);
    }

    public Appointment requestAppointmentbyCustomer(Long appointmentId) {
        Appointment appointment=appointmentRepository.getAppointmentById(appointmentId);
        if (appointment.getAppointmentstatus()==AppointmentStatus.Available && appointment.getCustomer()==null) {
            appointment.setCustomer(customUserDetailsService.getCurrentUser());
            appointment.setReservationtatus(ReservationStatus.waitForApproval);
            // appointment.getAppointmentTime().setSuitable(false);
            return appointmentRepository.save(appointment);
        }
        return null;
    }
    @Override
    public Appointment saveAppointmentbyAppoiter(Long appointmentId) {
        Appointment appointment=appointmentRepository.getAppointmentById(appointmentId);
        if (appointmentRepository.getwaitingappointment(customUserDetailsService.getCurrentUser()).contains(appointment)) {
            appointment.setReservationtatus(ReservationStatus.approvaled);
            appointment.setAppointmentstatus(AppointmentStatus.Booked);
           // appointment.getAppointmentTime().setSuitable(false);
            return appointmentRepository.save(appointment);
        }
        return null;
    }

    @Override
    public Appointment deniedAppointmentbyAppoiter(Long appointmentId) {
      Appointment appointment=appointmentRepository.getAppointmentById(appointmentId);
        if (appointmentRepository.getwaitingappointment(customUserDetailsService.getCurrentUser()).contains(appointment)) {
            appointment.setReservationtatus(ReservationStatus.denied);
            appointment.setCustomer(null);
            return appointmentRepository.save(appointment);
        }
        return null;
    }
    @Override
    public Optional<Appointment> findById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId);
    }

    @Override
    public List<Appointment> findAll() {
        return appointmentRepository.findAll();
    }
    @Override
    public List<AppointmentCandidate> generateAvaibleSchedule(String appointerid,Calendar starttime)
    {
        int minute=30;
        List<AppointmentCandidate> appointmentCandidates=new ArrayList<>();
        //Calendar startDate = Calendar.getInstance();
       // startDate.setTime(starttime);
        Calendar startDate =starttime;
        int count = 24*60/minute;
        for(int i =0; i<count;i++){
           // long t= startDate.getTimeInMillis();
            AppointmentCandidate ac = new AppointmentCandidate();
            ac.setId(new BigDecimal(i));
            ac.setStartdate(startDate.getTime());
          //  Date afterAddingTenMins=new Date(t + (minute * ONE_MINUTE_IN_MILLIS));
            startDate.add(Calendar.MINUTE,minute);
            Calendar endDate=startDate;
            if(isavaiblestarttime(appointerid,endDate))
                break;

            ac.setEnddate(endDate.getTime());
            startDate.setTime(endDate.getTime());
            appointmentCandidates.add(ac);
        }
        return appointmentCandidates;
    }

    @Override
    public Boolean isavaiblestarttime(String appointerid,Calendar starttime) {
        List<Calendar> getAllStartTime= appointmentRepository.getAllocalDateByBAppointer(customUserDetailRepository.getAppointerById(Long.valueOf(appointerid).longValue()));
        for (Calendar startime : getAllStartTime)
        {
            if(startime==starttime)
            {
                return true;
            }
        }
        return false;
    }
  /*  @Override
    public List<Appointment> findByDateRangeSortedByPrice(LocalDate startDate, LocalDate endDate) {
        return appointmentRepository.findAllByAppointmentDateBetweenOrderByPriceAsc(startDate, endDate);
    }*/

}