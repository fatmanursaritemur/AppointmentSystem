package api.appointment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentDateRepository extends JpaRepository<AppointmentDate, Long> {
    @Query("Select a from Appointment a where a.reservationtatus='waitforapproval'")
    List<Appointment> getwaitingappointment();


}
