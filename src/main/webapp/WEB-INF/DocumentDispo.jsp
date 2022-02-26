<%@ page import="mediatek2022.Document" %>
<%@ page import="mediatek2022.Mediatheque" %>
<%@ page import="java.util.List" %>

<%
    response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8");
    StringBuilder sb = new StringBuilder("<select name=\"documentSelect\">");
    List<Document> documents = Mediatheque.getInstance().tousLesDocumentsDisponibles();
    for(Document doc:documents){
        if (doc.disponible())
            sb.append("<option value=\"").append(doc.toString().split(" ")[0]).append("\">").append(doc).append("</option>");
    }
    sb.append("</select>");
    response.getWriter().println(sb);
%>
