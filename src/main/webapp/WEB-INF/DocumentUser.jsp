<%@ page import="mediatek2022.Document" %>
<%@ page import="mediatek2022.Mediatheque" %>
<%@ page import="java.util.List" %>
<%@ page import="mediatek2022.Utilisateur" %>

<%
    Utilisateur user = (Utilisateur) session.getAttribute("user");
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    StringBuilder sb = new StringBuilder("<select name=\"id\">");
    List<Document> documents = Mediatheque.getInstance().tousLesDocumentsDisponibles();
    for(Document doc:documents){
        if (!doc.disponible())
            sb.append("<option value=\"").append(doc.toString().split(" ")[0]).append("\">").append(doc).append("</option>");
    }
    sb.append("</select>");
    response.getWriter().println(sb);
%>
