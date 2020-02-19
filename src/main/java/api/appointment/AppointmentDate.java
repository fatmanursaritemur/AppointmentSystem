package api.appointment;

import lombok.Data;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;


@Data
@Entity
@Table(name="appointerdate")
public class AppointmentDate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long appointmentdateId;
    @Column(name = "time")
    private Calendar localDate;



}