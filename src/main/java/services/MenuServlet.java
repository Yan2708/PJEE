package services;

import mediatek2022.Utilisateur;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "MenuServlet", value = "/menu")
public class MenuServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null){
            response.sendRedirect("/PJEE-1.0-SNAPSHOT/login");
            return;
        }
        if (user.isBibliothecaire()){
            getServletContext().getRequestDispatcher("/WEB-INF/Bibliothecaire.jsp").forward(request,response);
            return;
        }
        getServletContext().getRequestDispatcher("/WEB-INF/Client.jsp").forward(request,response);
    }

}
