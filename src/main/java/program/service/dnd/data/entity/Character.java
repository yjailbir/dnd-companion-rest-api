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

    public void updateStat(String statName, Object statValue){
        switch (statName){
            case "characterClass" -> setCharacterClass(statValue.toString());
            case "name" -> setName(statValue.toString());
            case "lvl" -> setLvl((Integer) statValue);
            case "experience" -> setExperience((Integer) statValue);
            case "health" -> setHealth((Integer) statValue);
            case "strength" -> setStrength((Integer) statValue);
            case "physique" -> setPhysique((Integer) statValue);
            case "dexterity" -> setDexterity((Integer) statValue);
            case "wisdom" -> setWisdom((Integer) statValue);
            case "intelligence" -> setIntelligence((Integer) statValue);
            case "charisma" -> setCharisma((Integer) statValue);
            case "notes" -> setNotes(statValue.toString());
            case "athletics" -> getModifiers().setAthletics((Integer) statValue);
            case "acrobatics" -> getModifiers().setAcrobatics((Integer) statValue);
            case "sleightOfHand" -> getModifiers().setSleightOfHand((Integer) statValue);
            case "stealth" -> getModifiers().setStealth((Integer) statValue);
            case "perception" -> getModifiers().setPerception((Integer) statValue);
            case "survival" -> getModifiers().setSurvival((Integer) statValue);
            case "medicine" -> getModifiers().setMedicine((Integer) statValue);
            case "insight" -> getModifiers().setInsight((Integer) statValue);
            case "animalCare" -> getModifiers().setAnimalCare((Integer) statValue);
            case "analysis" -> getModifiers().setAnalysis((Integer) statValue);
            case "history" -> getModifiers().setHistory((Integer) statValue);
            case "magic" -> getModifiers().setMagic((Integer) statValue);
            case "nature" -> getModifiers().setNature((Integer) statValue);
            case "religion" -> getModifiers().setReligion((Integer) statValue);
            case "performance" -> getModifiers().setPerformance((Integer) statValue);
            case "intimidation" -> getModifiers().setIntimidation((Integer) statValue);
            case "fraud" -> getModifiers().setFraud((Integer) statValue);
            case "conviction" -> getModifiers().setConviction((Integer) statValue);
        }
    }
}
