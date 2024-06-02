package org.caskol.warcraft_database.api.v1.security.services;

import org.caskol.warcraft_database.api.v1.security.repositories.AppUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith({SpringExtension.class, MockitoExtension.class})
class AppUserServiceTest {
    @Mock
    private AppUserRepository userRepository;
    @InjectMocks
    private AppUserService service;

    @BeforeEach
    public void prepare(){
    }
    @Test
    public void loadUserByUsername() {
    }
}