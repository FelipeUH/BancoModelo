<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
        <meta name="description" content />
        <meta name="author" content />
        <title>Banco Modelo</title>
        <!-- Favicon-->
        <link rel="icon" type="image/x-icon" href="assets/favicon.ico" />
        <!-- Bootstrap icons-->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" rel="stylesheet" />
        <!-- Core theme CSS (includes Bootstrap)-->
        <link href="css/styles.css" rel="stylesheet" />
    </head>
    <body class="d-flex flex-column">
        <main class="flex-shrink-0">
            <!-- Navigation-->
            <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
                <div class="container px-5">
                    <a class="navbar-brand" href="index.jsp">Banco modelo</a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation"><span class="navbar-toggler-icon"></span></button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    </div>
                </div>
            </nav>
            <!-- Page content-->
            <section class="py-5">
                <div class="container px-5">
                    <div class="bg-light rounded-3 py-5 px-4 px-md-5 mb-5">
                        <div class="text-center mb-5">
                            <div class="feature bg-primary bg-gradient text-white rounded-3 mb-3"><i class="bi bi-envelope"></i></div>
                            <h1 class="fw-bolder">Registrar usuario</h1>
                            <p class="lead fw-normal text-muted mb-0">La mejor opcion para proteger tu bienestar financiero</p>
                        </div>
                        <div class="row gx-5 justify-content-center">
                            <div class="col-lg-8 col-xl-6">
                                <form action="RegistroServlet" method="POST">
                                    <% if (request.getParameter("registrado") == "true") { %> <p class="error-message">Registro exitoso! Inicia sesion para comenzar.</p> <% } else if (request.getParameter("registrado") == "false"){ %><p class="error-message">Error en el registro. Vuelve a intentarlo!</p> <% } %>
                                    <!-- Nombre input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="nombre" name="nombre" type="text" placeholder="Ingresa tu nombre" required />
                                        <label for="name">Nombre</label>
                                    </div>
                                    <!-- Apellido input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="apellido" name="apellido" type="text" placeholder="Ingresa tu apellido" required />
                                        <label for="name">Apellido</label>
                                    </div>
                                     <!-- Ciudad input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="ciudad" name="ciudad" type="text" placeholder="Ingresa tu ciudad"/>
                                        <label for="name">Ciudad</label>
                                    </div>
                                    <!-- Telefono input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="telefono" name ="telefono" type="tel" placeholder="(123) 456-7890"/>
                                        <label for="telefono">Telefono</label>
                                    </div>
                                    <!-- Email input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="email" name="email" type="email" placeholder="name@example.com" required />
                                        <label for="email">Correo electronico</label>
                                    </div>
                                    <!-- Password input-->
                                    <div class="form-floating mb-3">
                                        <input class="form-control" id="password" name="password" type="password" placeholder="Ingresa tu contraseña" required />
                                        <label for="name">Contraseña</label>
                                    </div>
                                    <!-- Submit Button-->
                                    <div class="d-grid"><input id="submitButton" type="submit" value="Registrarse" /></div>
                                </form>
                                <div>¿Ya tienes cuenta? <a href="login.jsp">Inicia sesion!</a></div>
                            </div>
                        </div>
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
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="js/scripts.js"></script>
        <script src="https://cdn.startbootstrap.com/sb-forms-latest.js"></script>
    </body>
</html>
