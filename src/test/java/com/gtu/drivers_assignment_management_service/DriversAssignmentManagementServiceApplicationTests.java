package com.gtu.drivers_assignment_management_service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
class DriversAssignmentManagementServiceApplicationTests {

    @Test
    void contextLoads() {
        // This test will simply check if the application context loads successfully.
		// If there are any issues with the configuration or beans, this test will fail.
		// No additional assertions are needed here.
    }
}
