package com.dave.SwaggerConfig;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(info = @Info(contact = @Contact(name = "Peter David", email = "Princewill016@gmail.com", url = "princewill016@gmail.com"), description = "This is an Api Documentation  for online book Inventory", title = "Book Inventory System", version = "1.0", license = @License(name = "Peter David", url = "https://princewill016@gmail.com"), termsOfService = "Free for All"), servers = {
        @Server(description = "test env", url = "https://princewill016@gmail.com")
}

)
public class SwaggerConfiguration {

}
