package com.example.SK_Project2.NotificationService.mapper;

import com.example.SK_Project2.NotificationService.domain.Parametar;
import com.example.SK_Project2.NotificationService.dto.parametar.ActivateEmailDto;
import com.example.SK_Project2.NotificationService.dto.parametar.ChangedPasswordDto;
import org.springframework.stereotype.Component;

@Component
public class ParametarMapper {

    public ParametarMapper() {
    }

    public Parametar activateEmailDtoToParametar(ActivateEmailDto activateEmailDto){
        Parametar parametar = new Parametar();

        parametar.setFirstName(activateEmailDto.getFirstName());
        parametar.setLastName(activateEmailDto.getLastName());
        parametar.setEmail(activateEmailDto.getEmail());
        parametar.setLink(activateEmailDto.getLink());

        parametar.setOldPassword(null);
        parametar.setNewPassword(null);

        return parametar;
    }

    public Parametar changedPasswordDtoToParametar(ChangedPasswordDto changedPasswordDto){
        Parametar parametar = new Parametar();

        parametar.setNewPassword(changedPasswordDto.getNewPassword());
        parametar.setOldPassword(changedPasswordDto.getOldPassword());
        parametar.setEmail(changedPasswordDto.getEmail());

        parametar.setFirstName(null);
        parametar.setLastName(null);
        parametar.setLink(null);

        return parametar;
    }
}
