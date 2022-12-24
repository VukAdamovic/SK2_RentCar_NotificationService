package com.example.SK_Project2.NotificationService.service.impl;

import com.example.SK_Project2.NotificationService.domain.Notification;
import com.example.SK_Project2.NotificationService.domain.Parametar;
import com.example.SK_Project2.NotificationService.dto.notification.NotificationDto;
import com.example.SK_Project2.NotificationService.dto.parametar.ChangedPasswordDto;
import com.example.SK_Project2.NotificationService.mapper.NotificationMapper;
import com.example.SK_Project2.NotificationService.mapper.ParametarMapper;
import com.example.SK_Project2.NotificationService.repository.NotificationRepository;
import com.example.SK_Project2.NotificationService.repository.ParametarRepository;
import com.example.SK_Project2.NotificationService.service.ChangedPasswordNotificationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ChangedPasswordNotificationServiceImpl implements ChangedPasswordNotificationService {

    private NotificationRepository notificationRepository;
    private NotificationMapper notificationMapper;
    private ParametarRepository parametarRepository;
    private ParametarMapper parametarMapper;

    public ChangedPasswordNotificationServiceImpl(NotificationRepository notificationRepository, NotificationMapper notificationMapper,
                                                  ParametarRepository parametarRepository, ParametarMapper parametarMapper) {
        this.notificationRepository = notificationRepository;
        this.notificationMapper = notificationMapper;
        this.parametarRepository = parametarRepository;
        this.parametarMapper = parametarMapper;
    }

    @Override
    public NotificationDto add(ChangedPasswordDto changedPasswordDto) {
        Notification notification = notificationMapper.changedPasswordDtoToNotification(changedPasswordDto);
        Parametar parametar = parametarMapper.changedPasswordDtoToParametar(changedPasswordDto);

        notificationRepository.save(notification);
        parametarRepository.save(parametar);

        return notificationMapper.notificationToNotificationDto(notification);
    }
}
