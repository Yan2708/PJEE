package services;

import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;
import persistance.User;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "MenuServlet", value = "/menu")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null){
            response.sendRedirect("/login");
            return;
        }
        if (user.isBibliothecaire()){
            getServletContext().getRequestDispatcher("/Bibliothecaire.jsp").forward(request,response);
            return;
        }
        getServletContext().getRequestDispatcher("/Client.jsp").forward(request,response);
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
