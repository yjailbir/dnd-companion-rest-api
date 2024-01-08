package program.service.dnd.data.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import program.service.dnd.data.entity.Modifiers;
import program.service.dnd.data.repository.ModifiersRepository;

@Service
public class ModifiersService {
    private final ModifiersRepository modifiersRepository;

    @Autowired
    public ModifiersService(ModifiersRepository modifiersRepository){
        this.modifiersRepository = modifiersRepository;
    }

    @Transactional
    public void save(Modifiers modifiers){
        modifiersRepository.save(modifiers);
    }
}
