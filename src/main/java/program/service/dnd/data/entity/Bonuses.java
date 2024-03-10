package program.service.dnd.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "bonuses")
@NoArgsConstructor
@JsonIgnoreProperties("modifiers")
public class Bonuses {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "athletics")
    private Integer athleticsBonus;
    @Column(name = "acrobatics")
    private Integer acrobaticsBonus;
    @Column(name = "sleight_of_hand")
    private Integer sleightOfHandBonus;
    @Column(name = "stealth")
    private Integer stealthBonus;
    @Column(name = "perception")
    private Integer perceptionBonus;
    @Column(name = "survival")
    private Integer survivalBonus;
    @Column(name = "medicine")
    private Integer medicineBonus;
    @Column(name = "insight")
    private Integer insightBonus;
    @Column(name = "animal_care")
    private Integer animalCareBonus;
    @Column(name = "analysis")
    private Integer analysisBonus;
    @Column(name = "history")
    private Integer historyBonus;
    @Column(name = "magic")
    private Integer magicBonus;
    @Column(name = "nature")
    private Integer natureBonus;
    @Column(name = "religion")
    private Integer religionBonus;
    @Column(name = "performance")
    private Integer performanceBonus;
    @Column(name = "intimidation")
    private Integer intimidationBonus;
    @Column(name = "fraud")
    private Integer fraudBonus;
    @Column(name = "conviction")
    private Integer convictionBonus;

    @OneToOne(mappedBy = "bonuses")
    private Modifiers modifiers;

    public void setDefaults(){
        this.athleticsBonus = 0;
        this.acrobaticsBonus = 0;
        this.sleightOfHandBonus = 0;
        this.stealthBonus = 0;
        this.perceptionBonus = 0;
        this.survivalBonus = 0;
        this.medicineBonus = 0;
        this.insightBonus = 0;
        this.animalCareBonus = 0;
        this.analysisBonus = 0;
        this.historyBonus = 0;
        this.magicBonus = 0;
        this.natureBonus = 0;
        this.religionBonus = 0;
        this.performanceBonus = 0;
        this.intimidationBonus = 0;
        this.fraudBonus = 0;
        this.convictionBonus = 0;
    }
}
