package sn.esp.ManSSI.Entities;
import sn.esp.ManSSI.Encryption.RSA;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;

public class Entity {
    private String cni, numTel, id, nom, prenom, imageProfileLink;
    private boolean canVote;
    private RSA rsa;

    public Entity(String cni, String numTel, String id, String nom, String prenom, String imageProfileLink, boolean canVote) throws Exception {
        this.cni = cni;
        this.numTel = numTel;
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.imageProfileLink = imageProfileLink;
        this.canVote = canVote;
        rsa = new RSA();
    }

    private String encodePublicKey(PublicKey publicKey) {
        byte[] publicKeyBytes = publicKey.getEncoded();
        return Base64.getEncoder().encodeToString(publicKeyBytes);
    }

    private PublicKey decodePublicKey(String encodedPublicKey) throws Exception {
        byte[] decodedKeyBytes = Base64.getDecoder().decode(encodedPublicKey);
        X509EncodedKeySpec spec = new X509EncodedKeySpec(decodedKeyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA");
        return keyFactory.generatePublic(spec);
    }

    public String getCni() {
        return cni;
    }

    public String getNumTel() {
        return numTel;
    }

    public String getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getImageProfileLink() {
        return imageProfileLink;
    }

    public boolean isCanVote() {
        return canVote;
    }

    public RSA getRsa() {
        return rsa;
    }
}

