package services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "AjoutDocServlet", value = "/create")
public class AjoutDocServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null||!user.isBibliothecaire()){
            response.sendRedirect("${pageContext.request.contextPath}/login");
        }else{
            getServletContext().getRequestDispatcher("/WEB-INF/Ajout.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null||!user.isBibliothecaire()){
            response.sendRedirect("/PJEE-1.0-SNAPSHOT/login");
        }

        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        int type = Integer.parseInt(request.getParameter("type"));

        if (titre.equals("")||auteur.equals("")){
            request.setAttribute("error","tous les champs doivent être remplis");
            getServletContext().getRequestDispatcher("/WEB-INF/Ajout.jsp").forward(request,response);
            return;
        }


        Mediatheque.getInstance().ajoutDocument(type,titre,auteur);
        request.setAttribute("success","le document est ajouté!");
        getServletContext().getRequestDispatcher("/WEB-INF/Ajout.jsp").forward(request,response);

    }
}
