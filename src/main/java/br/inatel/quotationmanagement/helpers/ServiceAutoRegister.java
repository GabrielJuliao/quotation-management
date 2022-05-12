package br.inatel.quotationmanagement.helpers;

import br.inatel.quotationmanagement.dto.NotificationDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;

@Component
public class ServiceAutoRegister {
    private final RestTemplate restTemplate;
    private final Logger logger = LoggerFactory.getLogger(ServiceAutoRegister.class);

    @Value("${app.stock-manager.register-url}")
    private String registerUrl;
    @Value("${app.stock-manager.register-host}")
    private String registerHost;
    @Value("${app.stock-manager.register-port}")
    private int registerPort;

    public ServiceAutoRegister(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @PostConstruct
    void start() {
        NotificationDTO notificationDTO = new NotificationDTO(registerHost, registerPort);
        try {
            restTemplate.postForEntity(registerUrl, notificationDTO, NotificationDTO[].class);
            logger.info("Service registered sucessfully at stock-manager service with: " + registerHost + ":" + registerPort);
        } catch (Exception e) {
            logger.warn("Could not register service at stock-manager, check if the service is running and restart the application or register the service manually.");
            logger.error(e.getMessage());
        }
    }
}
