<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.*,jakarta.servlet.*, Modelo.Cliente" %>
<% 
    Cliente usuario = null;
    if (session != null) {
        usuario = (Cliente) session.getAttribute("usuario");
    }
%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content="" />
        <meta name="author" content="" />
        <title>Banco Modelo</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body class="d-flex flex-column h-100">
        <main class="flex-shrink-0">
            <!-- Navigation-->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container px-5">
                    <a class="navbar-brand" href="index.jsp">Banco Modelo</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                            <li class="nav-item"><a class="nav-link" href="index.jsp">Inicio</a></li>
                            <li class="nav-item"><a class="nav-link" href="politicas.jsp">Politicas</a></li>
                            <% if (usuario != null) { %>
                            <li class="nav-item"><a class="nav-link" href="cuentas.jsp">Tus cuentas</a></li>
                                <li class="nav-item dropdown">
                                    <a class="nav-link dropdown-toggle" id="navbarDropdownPortfolio" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">Perfil</a>
                                    <ul class="dropdown-menu dropdown-menu-end" aria-labelledby="navbarDropdownPortfolio">
                                        <li><p class="dropdown-item">Bienvenido/a <%= usuario.getNombre()%>!</p></li>
                                        <li><a class="dropdown-item" href="configuracion.jsp">Configuracion</a></li>
                                        <li><a class="dropdown-item" href="LogoutServlet">Cerrar sesion</a></li>
                                    </ul>
                                </li> 
                            <% } %>
                        </ul>
                    </div>
                </div>
            </nav>
            <!-- Page Content-->
            <section class="py-5">
                <div class="container px-5 my-5">
                    <div class="text-center mb-5">
                        <h1 class="fw-bolder">Configura tu perfil</h1>
                        <!-- Config form -->
                        <form action="ConfigurarServlet" method="POST">
                            <% if (request.getParameter("error") != null) { %> <p class="error-message">Error al actualizar.</p> <% } %>
                            <!-- Nombre input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="nombre" name="nombre" type="text" value="<%= usuario.getNombre() %>" required />
                                <label for="name">Nombre</label>
                            </div>
                            <!-- Apellido input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="apellido" name="apellido" type="text" value="<%= usuario.getApellido() %>" required />
                                <label for="name">Apellido</label>
                            </div>
                            <!-- Ciudad input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="ciudad" name="ciudad" type="text" value="<%= usuario.getCiudad() %>"/>
                                <label for="name">Ciudad</label>
                            </div>
                            <!-- Telefono input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="telefono" name ="telefono" type="tel" value="<%= usuario.getTelefono() %>"/>
                                <label for="telefono">Telefono</label>
                            </div>
                            <!-- Email input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="email" name="email" type="email" value="<%= usuario.getEmail() %>" required />
                                <label for="email">Correo electronico</label>
                            </div>
                            <!-- Password input-->
                            <div class="form-floating mb-3">
                                <input class="form-control" id="password" name="password" type="password" required />
                                <label for="name">Contraseña</label>
                            </div>
                            <!-- Submit Button-->
                            <div class="d-grid"><input id="submitButton" type="submit" value="Actualizar informacion" /></div>
                        </form>
                    </div>
                </div>
            </section>
        </main>
        <!-- Footer-->
        <footer class="bg-dark py-4 mt-auto">
            <div class="container px-5">
                <div class="row align-items-center justify-content-between flex-column flex-sm-row">
                    <div class="col-auto"><div class="small m-0 text-white">Copyright &copy; Banco Modelo</div></div>
                    <div class="col-auto">
                        <a class="link-light small" href="politicas.jsp">Politicas</a>
                        <span class="text-white mx-1">&middot;</span>
                        <a class="link-light small" href="contacto.jsp">Contacto</a>
                    </div>
                </div>
            </div>
        </footer>
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>

