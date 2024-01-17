package program.service.dnd.data.dto;

import lombok.Data;

@Data
public class CharacterShortInfoDto {
    String characterClass;
    String race;
    String name;
    String imageLink;
    String characterLink;
}
