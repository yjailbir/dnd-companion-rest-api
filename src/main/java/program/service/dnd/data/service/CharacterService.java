package program.service.dnd.data.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import program.service.dnd.data.entity.Character;
import program.service.dnd.data.repository.CharacterRepository;
import program.service.dnd.data.repository.ModifiersRepository;

@Component
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final ModifiersRepository modifiersRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository, ModifiersRepository modifiersRepository){
        this.characterRepository = characterRepository;
        this.modifiersRepository = modifiersRepository;
    }

    public Character findCharacterById(Integer id){
        return characterRepository.findById(id).get();
    }

    @Transactional
    public void save(Character character){
        modifiersRepository.save(character.getModifiers());
        characterRepository.save(character);
    }
}
