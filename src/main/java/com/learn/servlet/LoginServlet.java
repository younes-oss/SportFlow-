package com.learn.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.learn.Dao.UtilisateurDAO;
import com.learn.models.Utilisateur;

import java.io.IOException;

public class LoginServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("password");

        UtilisateurDAO utilisateurDAO = new UtilisateurDAO();
        Utilisateur utilisateur = utilisateurDAO.connexionUtilisateur(email, motDePasse);

        if (utilisateur != null) {
            HttpSession session = request.getSession();
            session.setAttribute("utilisateur", utilisateur);

            if ("membre".equals(utilisateur.getRole())) {
                response.sendRedirect("membre-dashboard.jsp");
            } else if ("entraineur".equals(utilisateur.getRole())) {
                response.sendRedirect("entraineur-dashboard.jsp");
            }
        } else {
            response.sendRedirect("login.jsp?error=invalid");
        }
    }
}
