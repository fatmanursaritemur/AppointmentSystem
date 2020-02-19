package api.appointment;

import api.config.CustomUserDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    @Query("Select a from Appointment a where (a.customer=?1 OR a.appointer=?1) AND a.reservationtatus='waitForApproval'")
    List<Appointment> getwaitingappointment(CustomUserDetails user);

    @Query("Select a from Appointment a where (a.customer=?1 OR a.appointer=?1) AND a.reservationtatus='approvaled'")
    List<Appointment> getapprovaledappointment(CustomUserDetails user);

    @Query("Select a from Appointment a where (a.customer=?1 OR a.appointer=?1) AND a.reservationtatus='denied'")
    List<Appointment> getdeniedappointment(CustomUserDetails user);

    @Query("Select a from Appointment a where a.reservationtatus=?1")
    List<Appointment> getAppointmentListByType(String type);

    @Query("Select a from Appointment a where a.appointmentId=?1")
    Appointment getAppointmentById(Long id);

    @Query("Select DISTINCT a from Appointment a where a.appointer=?1 AND (a.appointmentDate.localDate BETWEEN ?2 AND ?3) ")
    List<Appointment>  getAppointmentByBetweenTwoDate(CustomUserDetails appointer,LocalDate startdate, LocalDate enddate);

    @Query("Select DISTINCT a.appointmentDate.localDate from Appointment a where  a.appointmentDate.localDate BETWEEN ?1 AND ?2 ")
    List<LocalDate>  getLocalDateByBetweenTwoDate(LocalDate startdate, LocalDate enddate);

    @Query("Select a from Appointment a where  appointmentDate.localDate=?1 ")
    List<Appointment>  getAppointmentByAppointmentDate(Calendar appointmentdate);

    /*@Query("Select  a.appointmentDate.period from Appointment a where  a.appointer=?1 AND  a.appointmentDate.localDate=?2 AND a.customer=NULL ")
    List<String>  getPeriodByBAppointer(CustomUserDetails appointer, Calendar localdate);*/

    @Query("Select  a from Appointment a where  a.appointer=?1 AND  a.appointmentDate.localDate=?2 AND  a.customer=NULL ")//AND a.appointmentDate.period=?3
    List<Appointment>  getAppointmentByBAppointer(CustomUserDetails appointer, Calendar localdate); //string period silindi, localDate calendar

    @Query("Select  a.appointmentDate.localDate from Appointment a where  a.appointer=?1 AND  a.reservationtatus='approvaled'")
    List<Calendar>  getAllocalDateByBAppointer(CustomUserDetails appointer);

    // List<Appointment> findAllByAppointmentDateBetweenOrderByPriceAsc(LocalDate startDate, LocalDate endDate);


}
