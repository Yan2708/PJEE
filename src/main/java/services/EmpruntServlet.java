package services;

import mediatek2022.Document;
import mediatek2022.Mediatheque;
import mediatek2022.Utilisateur;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(name = "EmpruntServlet", value = "/emprunt")
public class EmpruntServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null|| user.isBibliothecaire()){
            response.sendRedirect("/login");
        }else{
            getServletContext().getRequestDispatcher("/WEB-INF/Emprunt.jsp").forward(request,response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session= request.getSession(true);
        Utilisateur user = (Utilisateur) session.getAttribute("user");
        if (user==null || user.isBibliothecaire()){
            response.sendRedirect("/login");
            return;
        }
        int numDoc = Integer.parseInt(request.getParameter("documentSelect"));
        Document document = Mediatheque.getInstance().getDocument(numDoc);

        try {
            document.emprunt(user);
            request.setAttribute("success","le document est emprunté");
        } catch (Exception e) {
            request.setAttribute("error", "le document est déjà emprunté");
        }

        getServletContext().getRequestDispatcher("/WEB-INF/Emprunt.jsp").forward(request,response);

    }
}
