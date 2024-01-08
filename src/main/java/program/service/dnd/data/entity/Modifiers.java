package program.service.dnd.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "modifiers")
@NoArgsConstructor
public class Modifiers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "athletics")
    private Integer athletics;
    @Column(name = "acrobatics")
    private Integer acrobatics;
    @Column(name = "sleight_of_hand")
    private Integer sleightOfHand;
    @Column(name = "stealth")
    private Integer stealth;
    @Column(name = "perception")
    private Integer perception;
    @Column(name = "survival")
    private Integer survival;
    @Column(name = "medicine")
    private Integer medicine;
    @Column(name = "insight")
    private Integer insight;
    @Column(name = "animal_care")
    private Integer animalCare;
    @Column(name = "analysis")
    private Integer analysis;
    @Column(name = "history")
    private Integer history;
    @Column(name = "magic")
    private Integer magic;
    @Column(name = "nature")
    private Integer nature;
    @Column(name = "religion")
    private Integer religion;
    @Column(name = "performance")
    private Integer performance;
    @Column(name = "intimidation")
    private Integer intimidation;
    @Column(name = "fraud")
    private Integer fraud;
    @Column(name = "conviction")
    private Integer conviction;

    @OneToOne(mappedBy = "modifiers")
    private Character character;

    public void setDefaults(){
        this.athletics = 0;
        this.acrobatics = 0;
        this.sleightOfHand = 0;
        this.stealth = 0;
        this.perception = 0;
        this.survival = 0;
        this.medicine = 0;
        this.insight = 0;
        this.animalCare = 0;
        this.analysis = 0;
        this.history = 0;
        this.magic = 0;
        this.nature = 0;
        this.religion = 0;
        this.performance = 0;
        this.intimidation = 0;
        this.fraud = 0;
        this.conviction = 0;
    }
}
