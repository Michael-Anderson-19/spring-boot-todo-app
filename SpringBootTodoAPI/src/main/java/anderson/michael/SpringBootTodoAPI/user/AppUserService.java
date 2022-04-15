package anderson.michael.SpringBootTodoAPI.user;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

//TODO implements roles, return roles in reponse;

@Service
public class AppUserService implements UserDetailsService {

    private final AppUserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    public AppUserService(AppUserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public AppUser findByEmail(String email)
    {
        return this.userRepository.findByEmail(email).orElseThrow( ()-> new IllegalStateException(String.format("user with email address %s not found", email)) );
    }

    public AppUser findById(Long userId)
    {
        return this.userRepository.findById(userId).orElseThrow( ()-> new IllegalStateException(String.format("user with ID %d not found", userId)) );
    }

    //will only be used by an admin app?
    public List<AppUser> getAllUsers()
    {
        return this.userRepository.findAll();
    }

    public AppUser createNewItem(AppUser newUser)
    {
        String password = this.passwordEncoder.encode(newUser.getPassword());
        return this.userRepository.save(newUser);
    }

    //TODO update separate methods to change email address and password requiring re-confirming old passwords and authorization
    public AppUser updateUser(Long userId, AppUser newUser)
    {
        return this.userRepository.findById(userId).map( existingUser -> {
            existingUser.setFirstName(newUser.getFirstName());
            existingUser.setLastName(newUser.getLastName());
            //existingUser.setEmail(newUser.getEmail());
            //existingUser.setFirstName(newUser.getFirstName());
            return this.userRepository.save(existingUser);
        }).orElseThrow( ()-> new IllegalStateException("user not found"));
    }

    //TODO implement confirmations before allowing deletion of an account
    public void deleteUser(Long userId)
    {
        AppUser user = this.findById(userId);
        this.userRepository.delete(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        AppUser currentUser = this.userRepository.findByEmail(email).orElseThrow( () -> new UsernameNotFoundException( String.format("user with email address %s not found", email)) );

        //do the things here to do the thingggg g gg g ggg g gg ggg ggggg g ggg gg

        return User.withUsername(currentUser.getEmail()).password(currentUser.getPassword()).build();
    }
}
