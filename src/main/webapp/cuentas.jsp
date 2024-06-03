<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="Modelo.*, DAO.CuentaDAO, java.util.List" %>
<%
    Cliente usuario = null;
    if (session != null) {
        usuario = (Cliente) session.getAttribute("usuario");
    }
    
    CuentaDAO cuentaDAO = new CuentaDAO();
    List<Cuenta> cuentas = cuentaDAO.obtenerCuentasPorUsuario(usuario.getId());
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
        <script>
            function showAlert(message) {
                alert(message);
            }
            
            window.onload = function() {
            const urlParams = new URLSearchParams(window.location.search);
            if (urlParams.has('error') && urlParams.get('update') === '1') {
                showAlert('No tienes el saldo suficiente.');
            }
        }
        </script>
    </head>
    <body class="d-flex flex-column">
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
            <!-- Header-->
            <header class="py-5">
                <div class="container px-5">
                    <div class="row justify-content-center">
                        <div class="col-lg-8 col-xxl-6">
                            <div class="text-center my-5">
                                <h1 class="fw-bolder mb-3">Tus cuentas.</h1>
                                <a class="btn btn-primary btn-lg" href="#scroll-target">Crear cuenta</a>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <!-- Seccion de cuentas usuario-->
            <section class="py-5 bg-light">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Mis cuentas</h2>
                            <% if (cuentas.isEmpty()) { %>
                            <p class="lead fw-normal text-muted mb-0">No tienes cuentas aún. ¡Crea tu primera cuenta!</p>
                            <% } else { %>
                            <ul>
                                <% int contador = 0; for (Cuenta cuenta : cuentas) { contador +=1;%>
                                <li>
                                    <p class="lead fw-normal text-muted mb-0"><b>Cuenta <%= contador %></b></p><br>
                                    <p class="lead fw-normal text-muted mb-0"><b>Tipo de Cuenta:</b> <%= cuenta.getTipo() %></p><br>
                                    <p class="lead fw-normal text-muted mb-0"><b>Saldo disponible:</b> <%= cuenta.getSaldo() %></p><br>
                                    <p class="lead fw-normal text-muted mb-0"><b>Fecha de apertura</b> <%= cuenta.getFechaApertura() %></p><br>
                                    <!-- Formulario depositar -->
                                    <form action="DepositoServlet" method="POST" style="display:inline;">
                                        <p class="lead fw-normal text-muted mb-0"><b>Depositar:</b></p><br>
                                        <input type="hidden" name="cuentaId" value="<%= cuenta.getCuentaId() %>">
                                        <input class="form-control" id="cantidad-depositar" name="cantidad-depositar" type="number" placeholder="Cantidad a ingresar" min="0" required />
                                        <label for="moneda">Moneda</label>
                                        <select class="form-control" name="moneda" required>
                                            <option value="pesos">Pesos</option>
                                            <option value="dolares">Dólares</option>
                                            <option value="euros">Euros</option>
                                        </select>
                                        <div class="d-grid"><button type="submit">Ingresar</button></div>
                                    </form>
                                        <br>
                                    <!-- Formulario retirar -->
                                    <form action="RetiroServlet" method="POST" style="display:inline;">
                                        <p class="lead fw-normal text-muted mb-0"><b>Retirar:</b></p><br>
                                        <input type="hidden" name="cuentaId" value="<%= cuenta.getCuentaId() %>">
                                        <input class="form-control" id="cantidad-retirar" name="cantidad-retirar" type="number" placeholder="Cantidad a retirar" min="0" required />
                                        <label for="moneda">Moneda</label>
                                        <select class="form-control" name="moneda">
                                            <option value="pesos">Pesos</option>
                                            <option value="dolares">Dólares</option>
                                            <option value="euros">Euros</option>
                                        </select>
                                        <div class="d-grid"><button type="submit">Retirar</button></div>
                                    </form>
                                </li>
                                <hr>
                                <% } %>
                            </ul>
                            <% } %>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Seccion de creacion de cuenta-->
            <section class="py-5" id="scroll-target">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Crear cuenta</h2>
                            <!-- Formulario crear cuenta -->
                            <form action="CrearCuentaServlet" method="POST">
                                <!-- Tipo cuenta input-->
                                <label for="tipo">Tipo de cuenta</label>
                                <div class="form-floating mb-3">
                                    <select class="form-control"  name="tipo" id="tipo" required>
                                        <option value="Ahorros">Ahorros</option>
                                        <option value="Corriente">Corriente</option>
                                        <option value="Suprema">Suprema</option>
                                    </select><br>
                                </div>
                                <!-- Saldo inicial input-->
                                <div class="form-floating mb-3">
                                    <input class="form-control" id="saldo" name="saldo" type="number" value="0" min="0" required />
                                    <label for="saldo">Saldo inicial</label>
                                </div>
                                <!-- Submit Button-->
                                <div class="d-grid"><input id="submitButton" type="submit" value="Crear cuenta" /></div>
                            </form>
                        </div>
                    </div>
                </div>
            </section>
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