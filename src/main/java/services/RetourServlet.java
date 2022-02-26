package services;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "RetourServlet", value = "/retour")
public class RetourServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null||user.isBibliothecaire()){
            response.sendRedirect("/login");
        }else{
            getServletContext().getRequestDispatcher("/WEB-INF/Retour.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null||user.isBibliothecaire()){
            response.sendRedirect("/login");
        }

        int id = Integer.parseInt(request.getParameter("id"));

        Document document = Mediatheque.getInstance().getDocument(id);
        document.retour();
        request.setAttribute("success","le document est retourné!");
        getServletContext().getRequestDispatcher("/WEB-INF/Retour.jsp").forward(request,response);

    }
}
