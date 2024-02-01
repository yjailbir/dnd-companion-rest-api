package program.service.dnd.data.dto;

import lombok.Data;

import java.util.HashMap;

@Data
public class UpdateCharacterInfoDto {
    Integer characterId;
    HashMap<String, Object> newValues;
}
