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
    private Long id;
    @Column(name = "user_id")
    private Long user_id;
    @Column(name = "name")
    private String name;
    @Column(name = "class")
    private String characterClass;
    @Column(name = "race")
    private String race;
    @Column(name = "image_link")
    private String image_link;
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
    @OneToOne
    @JoinColumn(name = "id")
    private Modifiers modifiers;
}
