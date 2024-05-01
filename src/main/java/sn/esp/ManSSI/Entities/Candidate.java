package sn.esp.ManSSI.Entities;

public class Candidate extends Entity {
    private String nomPartie, lienProgramme;

    public Candidate(String cni, String numTel, String id, String nom, String prenom, String imageProfileLink, boolean canVote, String nomPartie, String lienProgramme) throws Exception {
        super(cni, numTel, id, nom, prenom, imageProfileLink, canVote);
        this.nomPartie = nomPartie;
        this.lienProgramme = lienProgramme;
    }

    public String getNomPartie() { return nomPartie; }

    public  String getLienProgramme(){ return lienProgramme; }
}
