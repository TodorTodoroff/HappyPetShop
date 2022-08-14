package com.example.happypetshop.services;

import com.example.happypetshop.models.dtos.AdminCommandDTO;
import com.example.happypetshop.models.dtos.UserDetailsAdminDTO;
import com.example.happypetshop.models.dtos.UserRegisterDTO;
import com.example.happypetshop.models.entities.UserEntity;
import com.example.happypetshop.models.entities.UserRoleEntity;
import com.example.happypetshop.models.enums.UserRoleEnum;
import com.example.happypetshop.models.mapper.UserMapper;
import com.example.happypetshop.repositories.UserRepository;
import com.example.happypetshop.repositories.UserRoleRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final UserRoleRepository userRoleRepository;
    private final UserDetailsService userDetailsService;


    public UserService(UserRepository userRepository,
                       PasswordEncoder passwordEncoder,
                       UserMapper userMapper,
                       UserRoleRepository userRoleRepository,
                       UserDetailsService userDetailsService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.userMapper = userMapper;
        this.userRoleRepository = userRoleRepository;
        this.userDetailsService = userDetailsService;

    }

    public void createUserIfNotExist(String email) {

        var userOpt = this.userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            var newUser = new UserEntity().
                    setEmail(email).
                    setPassword(null).
                    setFirstName("New").
                    setLastName("User").
                    setUserRoles(List.of());
            userRepository.save(newUser);
        }
    }

    public void registerAndLogin(UserRegisterDTO userRegisterDTO) {

        UserEntity newUser = userMapper.userDtoToUserEntity(userRegisterDTO);
        newUser.setPassword(passwordEncoder.encode(userRegisterDTO.getPassword()));

        this.userRepository.save(newUser);
        login(newUser.getEmail());
    }

    public void login(String userName) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(userName);

        Authentication auth =
                new UsernamePasswordAuthenticationToken(
                        userDetails,
                        userDetails.getPassword(),
                        userDetails.getAuthorities()
                );

        SecurityContextHolder.
                getContext().
                setAuthentication(auth);
    }

    public UserEntity getUserDetails(String username) {
        //no need for further checks as the user is logged in currently
        return this.userRepository.findByEmail(username).get();
    }

    public UserEntity getUserById(Long id) {
        //no need for further checks as the user is logged in currently
        return this.userRepository.findById(id).get();
    }


    public boolean checkIfUsernamesMatch(String username, String repeatUsername) {
        return username.equals(repeatUsername);
    }

    public void updateUsername(String username, Principal user) {
        UserEntity userEntity = this.userRepository.findByEmail(user.getName()).get().setEmail(username);

        this.userRepository.save(userEntity);
    }

    public boolean checkIfUsernameIsOccupied(String username) {
        return this.userRepository.findByEmail(username).isPresent();
    }

    public UserEntity getUserByEmail(String username) {
        return this.userRepository.findByEmail(username).get();
    }

    public List<UserDetailsAdminDTO> getAll() {
        return this.userRepository.
                findAll()
                .stream()
                .map(userMapper::userDetailAdminDTOtoUserEntity)
                .collect(Collectors.toList());
    }

    public void handleAdminRights(AdminCommandDTO adminCommandDTO) {

        UserEntity user = this.userRepository.
                findByEmail(adminCommandDTO.getEmail()).
                orElseThrow(() -> new RuntimeException("User not found " + adminCommandDTO.getEmail()));

        UserRoleEntity byUserRole = this.userRoleRepository.findByUserRole(UserRoleEnum.ADMIN);

        if (adminCommandDTO.isToBeAdmin() && !user.getUserRoles().contains(byUserRole)){
            user.addRole(byUserRole);
        }else {
            user.removeRole(byUserRole);
        }


        this.userRepository.save(user);

    }

}
