package program.service.dnd.data.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class AllCharactersResponseDto {
    List<CharacterShortInfoDto> characters;
}
