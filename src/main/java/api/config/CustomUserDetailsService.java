package api.config;

import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    CustomUserDetailRepository customUserDetailRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return customUserDetailRepository.findByUsername(userName);
    }
    public List<CustomUserDetails> getAllAppointer()
    {
        return customUserDetailRepository.getAllAppointer();
    }

    public List<CustomUserDetails> getAppointerByJob(String job)
    {
        return customUserDetailRepository.getAppointerByJob(job);
    }

    public CustomUserDetails getAppointerBySurname(String surname)
    {
        return customUserDetailRepository.getAppointerBySurname(surname);
    }
    public CustomUserDetails getAppointerById(Long id)
    {
        return customUserDetailRepository.getAppointerById(id);
    }
    public CustomUserDetails getCurrentUser()
    {
        Authentication authentication= SecurityContextHolder.getContext().getAuthentication();
        CustomUserDetails userDetail=null;
        if( !(authentication instanceof AnonymousAuthenticationToken))
        {
            String currentUserName=authentication.getName();
            if(Strings.isNotEmpty(currentUserName))
            {
                userDetail= customUserDetailRepository.findByUsername(currentUserName);
            }
        }
        return userDetail;
    }

}
