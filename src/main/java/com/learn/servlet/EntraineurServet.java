package com.learn.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.learn.Dao.EntraineurDAO;
import com.learn.models.Entraineur;

import java.io.IOException;
import java.util.List;

public class EntraineurServlet extends HttpServlet {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private EntraineurDAO entraineurDAO = new EntraineurDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("ajouter".equals(action)) {
            ajouterEntraineur(request, response);
        } else if ("modifier".equals(action)) {
            modifierEntraineur(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("supprimer".equals(action)) {
            supprimerEntraineur(request, response);
        } else if ("lister".equals(action)) {
            listerEntraineurs(request, response);
        }
    }

    private void ajouterEntraineur(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("password");
        String specialite = request.getParameter("specialite");

        Entraineur entraineur = new Entraineur(0, nom, email, motDePasse, specialite);
        entraineurDAO.ajouterEntraineur(entraineur);

        response.sendRedirect("listeEntraineurs.jsp?success=added");
    }

    private void modifierEntraineur(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("password");
        String specialite = request.getParameter("specialite");

        Entraineur entraineur = new Entraineur(id, nom, email, motDePasse, specialite);
        entraineurDAO.modifierEntraineur(entraineur);

        response.sendRedirect("listeEntraineurs.jsp?success=updated");
    }

    private void supprimerEntraineur(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        entraineurDAO.supprimerEntraineur(id);
        response.sendRedirect("listeEntraineurs.jsp?success=deleted");
    }

    private void listerEntraineurs(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Entraineur> entraineurs = entraineurDAO.getTousLesEntraineurs();
        request.setAttribute("entraineurs", entraineurs);
        request.getRequestDispatcher("listeEntraineurs.jsp").forward(request, response);
    }
}


