package program.service.dnd.contorller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import program.service.dnd.data.entity.Character;
import program.service.dnd.data.entity.User;
import program.service.dnd.data.service.CharacterService;
import program.service.dnd.data.service.UserService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

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

    @PostMapping("/create")
    public ResponseEntity<?> createCharacter(
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
            String originalFilename = image.getOriginalFilename();
            Path filePath = Path.of(resourcesPath, originalFilename);
            imageLink = filePath.toString();
            Files.copy(image.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        Character character = new Character(user.getId(), name, characterClass, race, imageLink);
        characterService.save(character);

        return ResponseEntity.ok(character);
    }
}
