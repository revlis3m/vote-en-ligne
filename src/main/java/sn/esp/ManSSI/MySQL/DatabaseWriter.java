package sn.esp.ManSSI.MySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import sn.esp.ManSSI.Entities.Entity;

public class DatabaseWriter {
    private final String url;
    private final String username;
    private final String password;

    public DatabaseWriter(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public void insertEntity(Entity entity) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement insertEntityStmt = connection.prepareStatement("INSERT INTO entities (cni, num_tel, id, nom, prenom, image_profile_link, can_vote) VALUES (?, ?, ?, ?, ?, ?, ?)");
            insertEntityStmt.setString(1, entity.getCni());
            insertEntityStmt.setString(2, entity.getNumTel());
            insertEntityStmt.setString(3, entity.getId());
            insertEntityStmt.setString(4, entity.getNom());
            insertEntityStmt.setString(5, entity.getPrenom());
            insertEntityStmt.setString(6, entity.getImageProfileLink());
            insertEntityStmt.setBoolean(7, entity.isCanVote());
            insertEntityStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting entity into database: " + e.getMessage());
        }
    }

    public void insertRSAKey(String entityId, String publicKey) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement insertRSAStmt = connection.prepareStatement("INSERT INTO rsa_keys (entity_id, public_key) VALUES (?, ?)");
            insertRSAStmt.setString(1, entityId);
            insertRSAStmt.setString(2, publicKey);
            insertRSAStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting RSA key into database: " + e.getMessage());
        }
    }

    public void insertTestMessage(String message) {
        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            PreparedStatement insertTestStmt = connection.prepareStatement("INSERT INTO test (message) VALUES (?)");
            insertTestStmt.setString(1, message);
            insertTestStmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error inserting message into test table: " + e.getMessage());
        }
    }
}
