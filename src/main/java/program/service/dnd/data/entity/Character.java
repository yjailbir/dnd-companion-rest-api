package program.service.dnd.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "characters")
@NoArgsConstructor
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "user_id")
    private Integer userId;
    @Column(name = "name")
    private String name;
    @Column(name = "class")
    private String characterClass;
    @Column(name = "race")
    private String race;
    @Column(name = "image_link")
    private String imageLink;
    @Column(name = "lvl")
    private Integer lvl;
    @Column(name = "experience")
    private Integer experience;
    @Column(name = "health")
    private Integer health;
    @Column(name = "strength")
    private Integer strength;
    @Column(name = "physique")
    private Integer physique;
    @Column(name = "dexterity")
    private Integer dexterity;
    @Column(name = "wisdom")
    private Integer wisdom;
    @Column(name = "intelligence")
    private Integer intelligence;
    @Column(name = "charisma")
    private Integer charisma;
    @Column(name = "notes")
    private String notes;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id")
    private Modifiers modifiers;

    public Character(Integer userId, String name, String characterClass, String race, String imageLink) {
        this.userId = userId;
        this.name = name;
        this.characterClass = characterClass;
        this.race = race;
        this.imageLink = imageLink;

        this.lvl = 1;
        this.experience = 0;

        switch (characterClass) {
            case "Бард", "Жрец", "Друид", "Монах", "Плут", "Колдун" -> this.health = 8;
            case "Варвар" -> this.health = 12;
            case "Воин", "Следопыт", "Паладин" -> this.health = 10;
            case "Чародей", "Волшебник" -> this.health = 6;

        }

        this.strength = 10;
        this.physique = 10;
        this.dexterity = 10;
        this.wisdom = 10;
        this.intelligence = 10;
        this.charisma = 10;
        this.notes = "";
        this.modifiers = new Modifiers();
        this.modifiers.setDefaults();
    }
}
