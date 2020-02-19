package api;

import api.config.CustomUserDetailRepository;
import api.config.CustomUserDetails;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    CustomUserDetailRepository customUserDetailRepository;

  /*  public CustomUserDetails getCurrentUser()
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
    }*/
}
