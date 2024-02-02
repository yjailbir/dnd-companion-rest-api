package program.service.dnd.contorller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import program.service.dnd.data.dto.AllCharactersResponseDto;
import program.service.dnd.data.dto.CharacterShortInfoDto;
import program.service.dnd.data.dto.UpdateCharacterInfoDto;
import program.service.dnd.data.entity.Character;
import program.service.dnd.data.entity.User;
import program.service.dnd.data.service.CharacterService;
import program.service.dnd.data.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.*;

@RestController
@RequestMapping("/api/character")
public class CharacterController {
    private final UserService userService;
    private final CharacterService characterService;

    @Autowired
    public CharacterController(UserService userService, CharacterService characterService){
        this.userService = userService;
        this.characterService = characterService;
    }

    @GetMapping("/get")
    public ResponseEntity<?> getUsersCharacters(HttpServletRequest request){
        User user = userService.getUserFromRequest(request);
        Integer userId = user.getId();

        List<Character> characters = characterService.getAllUserCharacters(userId);
        List<CharacterShortInfoDto> shortInfos = new ArrayList<>();

        for(Character character: characters){
            CharacterShortInfoDto dto = new CharacterShortInfoDto();
            dto.setCharacterClass(character.getCharacterClass());
            dto.setRace(character.getRace());
            dto.setName(character.getName());
            dto.setImageLink(character.getImageLink());
            dto.setCharacterLink("/api/character/" + character.getId().toString());

            shortInfos.add(dto);
        }

        AllCharactersResponseDto allCharactersResponseDto = new AllCharactersResponseDto(shortInfos);

        return ResponseEntity.ok(allCharactersResponseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCharacter(@PathVariable("id") Integer characterId, HttpServletRequest request){
        User user = userService.getUserFromRequest(request);
        Integer userId = user.getId();

        Optional<Character> character = characterService.getCharacterById(characterId);

        if(character.isEmpty() || !character.get().getUserId().equals(userId)){
           Map<String, String> map = new HashMap<>();
           map.put("error", "Персонаж не найден");
           return ResponseEntity.ok(map);
       }
       else return ResponseEntity.ok(character);
    }

    @PostMapping("/create")
    public ResponseEntity<Character> createCharacter(
            HttpServletRequest request,
            @RequestParam(value = "image", required = false) MultipartFile image,
            @RequestParam(value = "name") String name,
            @RequestParam(value = "class") String characterClass,
            @RequestParam(value = "race") String race
    ) throws IOException {
        User user = userService.getUserFromRequest(request);
        String imageLink = null;

        if(image != null){
            String resourcesPath = "src/main/resources/static/images";
            String filename = user.getId().toString() + name + image.getOriginalFilename();
            filename = filename.replaceAll(" ", "");
            Path filePath = Path.of(resourcesPath, filename);
            //FIXME: change host before deploy
            imageLink = "http://localhost:8080/api/image/" + filename;
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        Character character = new Character(user.getId(), name, characterClass, race, imageLink);
        characterService.save(character);

        return ResponseEntity.ok(character);
    }

    @PatchMapping("/update")
    public ResponseEntity<?> updateCharacter(
            HttpServletRequest request,
            @RequestBody UpdateCharacterInfoDto dto
            ){
        User user = userService.getUserFromRequest(request);
        Integer userId = user.getId();

        Optional<Character> characterOptional = characterService.getCharacterById(dto.getCharacterId());

        if(characterOptional.isEmpty() || !characterOptional.get().getUserId().equals(userId)){
            Map<String, String> map = new HashMap<>();
            map.put("error", "Персонаж не найден");
            return ResponseEntity.ok(map);
        }
        else {
            Character character = characterOptional.get();
            for(String stat: dto.getNewValues().keySet()){
                Object newStatValue = dto.getNewValues().get(stat);
                character.updateStat(stat, newStatValue);
            }

            characterService.save(character);

            return ResponseEntity.ok(character);
        }
    }
}
