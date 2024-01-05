package program.service.dnd.contorller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import program.service.dnd.data.entity.Character;
import program.service.dnd.data.service.CharacterService;

@RestController
@RequestMapping("/api/test")
public class TestController {
    private final CharacterService characterService;

    @Autowired
    public TestController(CharacterService characterService){
        this.characterService = characterService;
    }

    @GetMapping("/getDude")
    public Character getDude(){
        return characterService.findCharacterById(2L);
    }
}
