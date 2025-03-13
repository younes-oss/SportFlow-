package com.learn.servlet;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import com.learn.Dao.MembreDAO;
import com.learn.models.Membre;


import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.text.ParseException;

public class MembreServlet extends HttpServlet {
    
	private static final long serialVersionUID = 1L;
	private MembreDAO membreDAO = new MembreDAO();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("ajouter".equals(action)) {
            ajouterMembre(request, response);
        } else if ("modifier".equals(action)) {
            modifierMembre(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("supprimer".equals(action)) {
            supprimerMembre(request, response);
        } else if ("lister".equals(action)) {
            listerMembres(request, response);
        }
    }

    private void ajouterMembre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("password");
        String dateNaissanceStr = request.getParameter("dateNaissance");
        String sport = request.getParameter("sport");

        // Conversion de la date (String → Date)
       Date dateNaissance = null;
       try {
    	   SimpleDateFormat dateForme = new SimpleDateFormat("yyyy,mm,dd");
    	   dateNaissance=dateForme.parse(dateNaissanceStr);
       }catch(ParseException e){
    	   e.printStackTrace();
    	   response.sendRedirect("error.jsp?message=InvalidDate");
       }

        Membre membre = new Membre(0, nom, email, motDePasse, dateNaissance, sport);
        membreDAO.ajouterMembre(membre);

        response.sendRedirect("listeMembres.jsp?success=added");
    }

    private void modifierMembre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String nom = request.getParameter("nom");
        String email = request.getParameter("email");
        String motDePasse = request.getParameter("password");
        String dateNaissanceStr = request.getParameter("dateNaissance");
        String sport = request.getParameter("sport");

        // Conversion de la date (String → Date)
        Date dateNaissance = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            dateNaissance = dateFormat.parse(dateNaissanceStr);
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=InvalidDate");
            return;
        }

        Membre membre = new Membre(id, nom, email, motDePasse, dateNaissance, sport);
        membreDAO.modifierMembre(membre);

        response.sendRedirect("listeMembres.jsp?success=updated");
    }


    private void supprimerMembre(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        membreDAO.supprimerMembre(id);
        response.sendRedirect("listeMembres.jsp?success=deleted");
    }

    private void listerMembres(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Membre> membres = membreDAO.getTousLesMembres();
        request.setAttribute("membres", membres);
        request.getRequestDispatcher("listeMembres.jsp").forward(request, response);
    }
}

