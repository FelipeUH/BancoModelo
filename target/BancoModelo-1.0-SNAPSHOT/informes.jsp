<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.*,jakarta.servlet.*, Modelo.Cliente, Modelo.Transaccion" %>
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
        <title>Informe - Banco Modelo</title>
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
                                <h1 class="fw-bolder mb-3">Informe diario</h1>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <!-- Clientes atendidos en el dia -->
            <section class="py-5 bg-light" id="scroll-target">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Clientes atendidos hoy</h2>
                            <c:if test="${not empty clientesAtendidosHoy}">
                                <ul>
                                    <c:forEach var="cliente" items="${clientesAtendidosHoy}">
                                        <li>${cliente.nombre} (${cliente.email})</li>
                                    </c:forEach>
                                </ul>
                            </c:if>
                            <c:if test="${empty clientesAtendidosHoy}">
                                <p class="lead fw-normal text-muted mb-0">No se atendieron clientes hoy.</p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Transacciones del dia -->
            <section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Transacciones del dia</h2>
                            <c:if test="${not empty transaccionesDelDia}">
                                <table border="1">
                                    <tr>
                                        <th>ID Transacción</th>
                                        <th>ID Cuenta</th>
                                        <th>Monto</th>
                                        <th>Tipo de Transacción</th>
                                        <th>Fecha de Transacción</th>
                                    </tr>
                                    <c:forEach var="transaccion" items="${transaccionesDelDia}">
                                        <tr>
                                            <td>${transaccion.id}</td>
                                            <td>${transaccion.cuentaId}</td>
                                            <td>${transaccion.monto}</td>
                                            <td>${transaccion.tipoTransaccion}</td>
                                            <td>${transaccion.fechaTransaccion}</td>
                                        </tr>
                                    </c:forEach>
                                </table>
                            </c:if>
                            <c:if test="${empty transaccionesDelDia}">
                                <p class="lead fw-normal text-muted mb-0">No se realizaron transacciones hoy.</p>
                            </c:if>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Arqueo de caja -->
            <section class="py-5 bg-light" id="scroll-target">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Arqueo de caja</h2>
                            <p class="lead fw-normal text-muted mb-0">Total Recaudado Hoy: ${totalRecaudado}</p>
                            <p class="lead fw-normal text-muted mb-0">Total Retirado Hoy: ${totalRetirado}</p>
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
        <!-- Bootstrap core JS-->
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
        <!-- Core theme JS-->
        <script src="js/scripts.js"></script>
    </body>
</html>