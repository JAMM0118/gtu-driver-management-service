package com.gtu.drivers_assignment_management_service.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;




class OpenAPIConfigTest {

    @Test
    void customOpenAPI_shouldReturnOpenAPIWithBearerJwtSecurityScheme() {
        OpenAPIConfig config = new OpenAPIConfig();
        OpenAPI openAPI = config.customOpenAPI();

        assertNotNull(openAPI);

        Components components = openAPI.getComponents();
        assertNotNull(components);

        SecurityScheme securityScheme = components.getSecuritySchemes().get("bearer-jwt");
        assertNotNull(securityScheme);
        assertEquals(SecurityScheme.Type.HTTP, securityScheme.getType());
        assertEquals("bearer", securityScheme.getScheme());
        assertEquals("JWT", securityScheme.getBearerFormat());

        assertNotNull(openAPI.getSecurity());
        assertFalse(openAPI.getSecurity().isEmpty());

        SecurityRequirement securityRequirement = openAPI.getSecurity().get(0);
        assertTrue(securityRequirement.containsKey("bearer-jwt"));
    }
}