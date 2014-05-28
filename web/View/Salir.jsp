<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Expires" content="0" />
        <meta http-equiv="Pragma" content="no-cache" />
    </head>
    <body>
        <%
            session.invalidate();
            response.sendRedirect("index.jsp");
            
        %>
    </body>
</html>