package br.inatel.quotationmanagement.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NotificationDTO {
    private String host;
    private int port;

    public NotificationDTO() {
    }
}
