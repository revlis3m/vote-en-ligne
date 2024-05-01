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

        try (Connection connection = DriverManager.getConnection("jdbc:mysql://mysql-silver.alwaysdata.net:3306/silver_mssi", "silver", "H93frSeYu92NjFhcVYvb")) {
            try {
                PreparedStatement insertEntityStmt = connection.prepareStatement("INSERT INTO entities (cni, num_tel, id, nom, prenom, image_profile_link, can_vote) VALUES (?, ?, ?, ?, ?, ?, ?)");
                insertEntityStmt.setString(1, cni);
                insertEntityStmt.setString(2, numTel);
                insertEntityStmt.setString(3, id);
                insertEntityStmt.setString(4, nom);
                insertEntityStmt.setString(5, prenom);
                insertEntityStmt.setString(6, imageProfileLink);
                insertEntityStmt.setBoolean(7, canVote);
                insertEntityStmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error inserting entity into database: " + e.getMessage());
            }

            try {
                PreparedStatement insertRSAStmt = connection.prepareStatement("INSERT INTO rsa_keys (entity_id, public_key) VALUES (?, ?)");
                insertRSAStmt.setString(1, id); // Using id from the Entity object
                insertRSAStmt.setString(2, encodePublicKey(rsa.getPublicKey())); // Encoding public key
                insertRSAStmt.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error inserting RSA key into database: " + e.getMessage());
            }
        } catch (SQLException e) {
            System.err.println("Error connecting to database: " + e.getMessage());
        }
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

