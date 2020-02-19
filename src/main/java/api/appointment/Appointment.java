package api.appointment;

import api.config.CustomUserDetails;
import lombok.Data;

import javax.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
@Entity

public class Appointment {
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) Long appointmentId;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="appointer_id",nullable = true)
    private CustomUserDetails appointer;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="customer_id")
    private CustomUserDetails customer=null;

   @ManyToOne(cascade = CascadeType.ALL)
   @JoinColumn(name="time")
    private AppointmentDate appointmentDate;

   @Enumerated(EnumType.STRING)

    private ReservationStatus reservationtatus = ReservationStatus.waitForApproval;
    @Enumerated(EnumType.STRING)
    private AppointmentStatus appointmentstatus = AppointmentStatus.Available;
    private BigDecimal price;


    public ReservationStatus getReservationtatus() {
        return reservationtatus;
    }

    public void setReservationtatus(ReservationStatus reservationtatus) {
        this.reservationtatus = reservationtatus;
    }

    public AppointmentStatus getAppointmentstatus() {
        return  appointmentstatus;
    }

    public void setAppointmentstatus(AppointmentStatus appointmentstatus) { this.appointmentstatus = appointmentstatus;}
}