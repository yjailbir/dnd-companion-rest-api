package program.service.dnd.data.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import program.service.dnd.data.entity.Character;
import program.service.dnd.data.repository.CharacterRepository;
import program.service.dnd.data.repository.ModifiersRepository;

import java.util.List;
import java.util.Optional;

@Component
public class CharacterService {
    private final CharacterRepository characterRepository;
    private final ModifiersRepository modifiersRepository;

    @Autowired
    public CharacterService(CharacterRepository characterRepository, ModifiersRepository modifiersRepository){
        this.characterRepository = characterRepository;
        this.modifiersRepository = modifiersRepository;
    }

    public List<Character> getAllUserCharacters(Integer id){
        return characterRepository.getAllByUserId(id);
    }

    public Optional<Character> getCharacterById(Integer id){
        return characterRepository.findById(id);
    }

    @Transactional
    public void save(Character character){
        modifiersRepository.save(character.getModifiers());
        characterRepository.save(character);
    }
}
