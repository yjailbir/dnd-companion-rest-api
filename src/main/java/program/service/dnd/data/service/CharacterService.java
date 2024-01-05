package program.service.dnd.data.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import program.service.dnd.data.entity.Character;
import program.service.dnd.data.repository.CharacterRepository;

@Component
public class CharacterService {
    private final CharacterRepository characterRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository){
        this.characterRepository = characterRepository;
    }

    public Character findCharacterById(Long id){
        return characterRepository.findById(id).get();
    }
}
