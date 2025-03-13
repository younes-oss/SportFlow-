package com.learn.Dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.learn.models.Membre;
import com.learn.utils.DatabaseConnection;

public class MembreDAO {
    private Connection connection;

    public MembreDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Ajouter un membre
    public void ajouterMembre(Membre membre) {
        String sql = "INSERT INTO utilisateur (nom, email, mot_de_passe, role, date_naissance, sport) VALUES (?, ?, ?, 'membre', ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getEmail());
            stmt.setString(3, membre.getMotDePasse());
            stmt.setDate(4,(Date) membre.getDateNaissance());
            stmt.setString(5, membre.getSport());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Récupérer tous les membres
    public List<Membre> getTousLesMembres() {
        List<Membre> membres = new ArrayList<>();
        String sql = "SELECT * FROM utilisateur WHERE role = 'membre'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Membre membre = new Membre(
                    rs.getInt("id"),
                    rs.getString("nom"),
                    rs.getString("email"),
                    rs.getString("mot_de_passe"),
                    rs.getDate("date_naissance"),
                    rs.getString("sport")
                );
                membres.add(membre);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return membres;
    }

    // Modifier un membre
    public void modifierMembre(Membre membre) {
        String sql = "UPDATE utilisateur SET nom = ?, email = ?, mot_de_passe = ?, date_naissance = ?, sport = ? WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, membre.getNom());
            stmt.setString(2, membre.getEmail());
            stmt.setString(3, membre.getMotDePasse());
            stmt.setDate(4, (Date) membre.getDateNaissance());
            stmt.setString(5, membre.getSport());
            stmt.setInt(6, membre.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Supprimer un membre
    public void supprimerMembre(int id) {
        String sql = "DELETE FROM utilisateur WHERE id = ? AND role = 'membre'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
