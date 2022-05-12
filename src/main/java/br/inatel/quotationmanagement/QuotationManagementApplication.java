package br.inatel.quotationmanagement;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@OpenAPIDefinition(
        info = @Info(
                title = "Stock quote manager",
                description = "Inatel Technical Evaluation 1",
                contact = @Contact(
                        name = "Gabriel Juliao",
                        url = "https://gabrieljuliao.com"
                ),
                license = @License(
                        name = "GNU General Public License v3.0",
                        url = "https://github.com/GabrielJuliao/quotation-management/blob/main/LICENSE"))
)
@SpringBootApplication
@EnableCaching
public class QuotationManagementApplication {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(QuotationManagementApplication.class, args);
    }

}
