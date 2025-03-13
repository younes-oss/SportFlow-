package com.learn.Dao;



import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.learn.models.Entraineur;
import com.learn.utils.DatabaseConnection;

public class EntraineurDAO {
    private Connection connection;

    public EntraineurDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public void ajouterEntraineur(Entraineur entraineur) {
        String sql = "INSERT INTO utilisateurs (nom, email, motDePasse, role, specialite) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entraineur.getNom());
            stmt.setString(2, entraineur.getEmail());
            stmt.setString(3, entraineur.getMotDePasse());
            stmt.setString(4, "entraineur");
            stmt.setString(5, entraineur.getSpecialite());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void modifierEntraineur(Entraineur entraineur) {
        String sql = "UPDATE utilisateurs SET nom=?, email=?, motDePasse=?, specialite=? WHERE id=?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, entraineur.getNom());
            stmt.setString(2, entraineur.getEmail());
            stmt.setString(3, entraineur.getMotDePasse());
            stmt.setString(4, entraineur.getSpecialite());
            stmt.setInt(5, entraineur.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void supprimerEntraineur(int id) {
        String sql = "DELETE FROM utilisateurs WHERE id=? AND role='entraineur'";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Entraineur> getTousLesEntraineurs() {
        List<Entraineur> entraineurs = new ArrayList<>();
        String sql = "SELECT * FROM utilisateurs WHERE role='entraineur'";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Entraineur entraineur = new Entraineur(
                        rs.getInt("id"),
                        rs.getString("nom"),
                        rs.getString("email"),
                        rs.getString("motDePasse"),
                        rs.getString("specialite")
                );
                entraineurs.add(entraineur);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return entraineurs;
    }
}
