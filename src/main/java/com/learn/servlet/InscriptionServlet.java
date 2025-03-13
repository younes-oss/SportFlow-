package com.learn.servlet;

import com.learn.Dao.UtilisateurDAO;
import com.learn.models.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class InscriptionServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("password");
        String role = request.getParameter("role"); // "membre" ou "entraineur"

        // Vérification des champs (optionnel : ajouter d'autres validations)
        if (nom == null || email == null || motDePasse == null || role == null) {
            response.sendRedirect("inscription.jsp?error=missingFields");
            return;
        }

        Utilisateur utilisateur = new Utilisateur(0, nom, email, motDePasse, role);
        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        utilisateurDAO.inscriptionUtilisateur(utilisateur);

        // Redirection vers la page de connexion
        response.sendRedirect("login.jsp?success=registered");
    }
}
