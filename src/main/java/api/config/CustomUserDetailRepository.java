package api.config;

import api.config.CustomUserDetails;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface CustomUserDetailRepository extends JpaRepository<CustomUserDetails, Long> {

   @Query("select c from CustomUserDetails c where username= ?1")
    CustomUserDetails findByUsername(String currentUserName);

   @Query("select a from CustomUserDetails  a where a.type='APPOINTER' AND a.job = ?1")
    List<CustomUserDetails> getAppointerByJob(String job);


    @Query("select a from CustomUserDetails  a where a.type='APPOINTER'")
    List<CustomUserDetails> getAllAppointer();

    @Query("select a from CustomUserDetails  a where  a.type='APPOINTER' AND a.surname=?1 ")
    CustomUserDetails getAppointerBySurname(String surname);

    @Query("select a from CustomUserDetails  a where  a.type='APPOINTER' AND a.userId=?1 ")
    CustomUserDetails getAppointerById(Long id);
}
