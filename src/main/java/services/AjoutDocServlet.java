package services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;
import persistance.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "AjoutDocServlet", value = "/create")
public class AjoutDocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null||!user.isBibliothecaire()){
            response.sendRedirect("/login");
        }else{
            getServletContext().getRequestDispatcher("/Ajout.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null||!user.isBibliothecaire()){
            response.sendRedirect("/login");
        }

        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        int type = Integer.parseInt(request.getParameter("type"));

        if (titre.equals("")||auteur.equals("")){
            request.setAttribute("error","tous les champs doivent être remplis");
            getServletContext().getRequestDispatcher("/Ajout.jsp").forward(request,response);
            return;
        }


        Mediatheque.getInstance().ajoutDocument(type,titre,auteur);
        request.setAttribute("success","le document est ajouté!");
        response.setIntHeader("Refresh",2);
        getServletContext().getRequestDispatcher("/Ajout.jsp").forward(request,response);

    }
}
