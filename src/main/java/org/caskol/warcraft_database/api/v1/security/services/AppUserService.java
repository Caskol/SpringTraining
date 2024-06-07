package org.caskol.warcraft_database.api.v1.security.services;

import lombok.RequiredArgsConstructor;
import org.caskol.warcraft_database.api.v1.security.exceptions.RoleNotFoundException;
import org.caskol.warcraft_database.api.v1.security.exceptions.UserAlreadyExistsException;
import org.caskol.warcraft_database.api.v1.security.models.AppRole;
import org.caskol.warcraft_database.api.v1.security.models.AppUser;
import org.caskol.warcraft_database.api.v1.security.repositories.AppRoleRepository;
import org.caskol.warcraft_database.api.v1.security.repositories.AppUserRepository;
import org.springframework.context.MessageSource;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AppUserService implements UserDetailsManager {
    private final AppUserRepository userRepository;
    private final AppRoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageSource messageSource;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        AppUser user = userRepository.findByUsername(username)
                .orElseThrow(()->new UsernameNotFoundException(
                        messageSource.getMessage("security.username_not_found", new Object[]{username}, Locale.getDefault())
                ));
        return new User(user.getUsername(),user.getPassword(), Collections.unmodifiableSet(user.getAuthorities()));
    }

    @Override
    public void createUser(UserDetails user) {
        if (userExists(user.getUsername()))
            throw new UserAlreadyExistsException(
                    messageSource.getMessage("security.user_already_exists", new Object[]{user.getUsername()}, Locale.getDefault()));
        HashSet<AppRole> rolesForNewUser = new HashSet<>();
        rolesForNewUser.add(roleRepository.findByName("ROLE_USER")
                .orElseThrow(()->new RoleNotFoundException(
                        messageSource.getMessage("security.role_not_found", new Object[]{"USER"}, Locale.getDefault())
        )));
        AppUser newUser = new AppUser(
                null,
                user.getUsername(),
                passwordEncoder.encode(user.getPassword()),
                rolesForNewUser
        );

        userRepository.save(newUser);
    }

    @Override
    public void updateUser(UserDetails user) {
        AppUser appUser = userRepository.findByUsername(user.getUsername())
                .orElseThrow(()->new UsernameNotFoundException(
                        messageSource.getMessage("security.username_not_found", new Object[]{user.getUsername()}, Locale.getDefault())
                ));
        appUser.setPassword(passwordEncoder.encode(user.getPassword()));
    }

    @Override
    public void deleteUser(String username) {

    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {

    }

    @Override
    public boolean userExists(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}
