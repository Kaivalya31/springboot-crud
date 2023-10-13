package com.kss.springbootcrud.model;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "CWG2018")
public class Participant {
    @Id
    @Column(name = "standing")
    private long standing;

    @Column(name = "nation")
    private String nation;

    @Column(name = "gold")
    private Integer gold;

    @Column(name = "silver")
    private Integer silver;

    @Column(name = "bronze")
    private Integer bronze;

    @Column(name = "total")
    private Integer total;

    public Participant(){}

    public Participant(Long standing, String nation, Integer gold, Integer silver, Integer bronze)
    {
        this.standing = standing;
        this.nation = nation;
        this.gold = gold;
        this.silver = silver;
        this.bronze = bronze;
        this.total = gold + silver + bronze;
    }

    public void setStanding(Long standing) {
        this.standing = standing;
    }

    public Long getStanding() {
        return standing;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public String getNation() {
        return nation;
    }

    public void setGold(Integer gold) {
        this.gold = gold;
    }

    public Integer getGold() {
        return gold;
    }

    public void setSilver(Integer silver) {
        this.silver = silver;
    }

    public Integer getSilver() {
        return silver;
    }

    public void setBronze(Integer bronze) {
        this.bronze = bronze;
    }

    public Integer getBronze() {
        return bronze;
    }

    public void setTotal(Integer total) {  this.total = gold + silver + bronze; }

    public Integer getTotal() { return total; }

    @Override
    public String toString()
    {
        return "Participant [A_standing = " + standing + ", B_nation = " + nation + ", C_gold = " + gold +
                ", D_silver = " + silver + ", E_bronze = " + bronze + "]";
    }
}
