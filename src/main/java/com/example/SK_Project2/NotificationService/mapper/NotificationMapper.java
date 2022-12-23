package com.example.SK_Project2.NotificationService.mapper;

import com.example.SK_Project2.NotificationService.domain.Notification;
import com.example.SK_Project2.NotificationService.dto.notification.NotificationDto;
import com.example.SK_Project2.NotificationService.dto.parametar.ActivateEmailDto;
import com.example.SK_Project2.NotificationService.dto.parametar.ChangedPasswordDto;
import com.example.SK_Project2.NotificationService.exception.NotFoundException;
import com.example.SK_Project2.NotificationService.repository.NotificationRepository;
import com.example.SK_Project2.NotificationService.repository.NotificationTypeRepository;
import com.example.SK_Project2.NotificationService.repository.ParametarRepository;
import org.springframework.stereotype.Component;

@Component
public class NotificationMapper {

    private NotificationTypeRepository notificationTypeRepository;
    private ParametarRepository parametarRepository;
    private NotificationRepository notificationRepository;
    private ParametarMapper parametarMapper;

    public NotificationMapper(NotificationTypeRepository notificationTypeRepository, ParametarRepository parametarRepository,
                              NotificationRepository notificationRepository, ParametarMapper parametarMapper) {
        this.notificationTypeRepository = notificationTypeRepository;
        this.parametarRepository = parametarRepository;
        this.notificationRepository = notificationRepository;
        this.parametarMapper = parametarMapper;
    }

    public Notification activateEmailDtoToNotification(ActivateEmailDto activateEmailDto){
        Notification notification = new Notification();

        notification.setNotificationType(notificationTypeRepository.findNotificationTypeByName("ACTIVATE_EMAIL").get());
        notification.setParametar(parametarMapper.activateEmailDtoToParametar(activateEmailDto));

        parametarRepository.save(notification.getParametar());

        //SetTekst
        String firstName = notification.getParametar().getFirstName();
        String lastName = notification.getParametar().getLastName();
        String link = notification.getParametar().getLink();

        notification.setText("Pozdrav " + firstName + " " + lastName + " , verifikujte email na sledecem linku " + link);

        return notification;
    }

    public Notification changedPasswordDtoToNotification(ChangedPasswordDto changedPasswordDto){
        Notification notification = new Notification();

        notification.setNotificationType(notificationTypeRepository.findNotificationTypeByName("CHANGED_PASSWORD").get());
        notification.setParametar(parametarMapper.changedPasswordDtoToParametar(changedPasswordDto));

        parametarRepository.save(notification.getParametar());


        //SetTekst
        String oldPassword = notification.getParametar().getOldPassword();
        String newPassword = notification.getParametar().getNewPassword();

        notification.setText("Lozinka je promenjena, stara lozinka:" +  oldPassword + " , nova lozinka:" + newPassword);

        return notification;
    }


    public NotificationDto  notificationToNotificationDto (Notification notification){
        NotificationDto notificationDto = new NotificationDto();

        notificationDto.setId(notification.getId());
        notificationDto.setText(notification.getText());

        return notificationDto;
    }

    public Notification notificationDtoToNotification(NotificationDto notificationDto){
        Notification notification = notificationRepository.findNotificationById(notificationDto.getId())
                .orElseThrow(() -> new NotFoundException(String.format("Notification with id: %d does not exists.", notificationDto.getId())));

        return notification;
    }

}
