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
                                <h1 class="fw-bolder mb-3">Politicas y codigo de etica.</h1>
                                <p class="lead fw-normal text-muted mb-4">Conoce porqué somos el banco mas elegido a nivel mundial.</p>
                                <a class="btn btn-primary btn-lg" href="#scroll-target">Leer</a>
                            </div>
                        </div>
                    </div>
                </div>
            </header>
            <!-- Politica de Apertura de cuentas-->
            <section class="py-5 bg-light" id="scroll-target">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Politica de Apertura de Cuentas</h2>
                            <p class="lead fw-normal text-muted mb-0"><strong>Elegibilidad: </strong>Los clientes deben ser mayores de 18 años y presentar una identificación válida.</p>
                            <hr>
                            <p class="lead fw-normal text-muted mb-0"><strong>Proceso de Apertura: </strong>Los clientes deben completar el formulario de solicitud en línea o en una sucursal, seguido de una revisión y aprobación por parte del banco.</p>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Politica de Seguridad-->
            <section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Politica de Seguridad</h2>
                            <p class="lead fw-normal text-muted mb-0"><strong>Proteccion de datos: </strong>El banco se compromete a proteger la información personal de los clientes mediante el uso de tecnologías avanzadas de seguridad.</p>
                            <hr>
                            <p class="lead fw-normal text-muted mb-0"><strong>Monitoreo y alerta: </strong>Todas las transacciones son monitoreadas en tiempo real para detectar y prevenir fraudes.</p>

                        </div>
                    </div>
                </div>
            </section>
            <!-- Politica de Tarifas y Comisiones-->
            <section class="py-5 bg-light" id="scroll-target">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Politica de Tarifas y Comisiones</h2>
                            <p class="lead fw-normal text-muted mb-0"><strong>Cuota de mantenimiento: </strong>Se cobra una cuota mensual por el mantenimiento de la cuenta, según el tipo de cuenta. Ahorros: 5.000 COP. Corriente: 10.000 COP. Suprema: 50.000 COP</p>
                            <hr>
                            <p class="lead fw-normal text-muted mb-0"><strong>Comisiones por transacciones: </strong>Todas las transacciones, retiro o depósito tienen una comisión del 1%. Si la comisión es menor a 50,000 COP, se cobrará 100 COP.</p>
                            <hr>
                            <p class="lead fw-normal text-muted mb-0"><strong>Cambio de Divisas: </strong>Las transacciones en dólares y euros se convierten a pesos colombianos según la tasa de cambio vigente.</p>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Politica de Intereses-->
            <section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6 order-first order-lg-last"><img class="img-fluid rounded mb-5 mb-lg-0" src="https://dummyimage.com/600x400/343a40/6c757d" alt="..." /></div>
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Politica de Intereses</h2>
                            <p class="lead fw-normal text-muted mb-0"><strong>Tasas de Interes: </strong>Las tasas de interés varían según el tipo de cuenta.</p>
                            <hr>
                            <p class="lead fw-normal text-muted mb-0"><strong>Interes Compuestos: </strong>Los intereses se calculan diariamente y se acumulan como interés compuesto.</p>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Politica de Privacidad-->
            <section class="py-5 bg-light" id="scroll-target">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Politica de Privacidad</h2>
                            <p class="lead fw-normal text-muted mb-0"><strong>Uso de datos personales: </strong>La información personal de los clientes se utiliza exclusivamente para proporcionar servicios bancarios y mejorar la experiencia del cliente.</p>
                            <hr>
                            <p class="lead fw-normal text-muted mb-0"><strong>Divulgacion de información personal: </strong>El banco no compartirá la información personal de los clientes con terceros sin el consentimiento del cliente, excepto cuando sea requerido por ley.</p>
                            <hr>
                            <p class="lead fw-normal text-muted mb-0"><strong>Acceso y correccion de datos: </strong>Los clientes tienen el derecho de acceder y corregir su información personal en cualquier momento.</p>
                        </div>
                    </div>
                </div>
            </section>
            <!-- Politica de Manejo de transacciones-->
            <section class="py-5">
                <div class="container px-5 my-5">
                    <div class="row gx-5 align-items-center">
                        <div class="col-lg-6 order-first order-lg-last"><img class="img-fluid rounded mb-5 mb-lg-0" src="https://dummyimage.com/600x400/343a40/6c757d" alt="..." /></div>
                        <div class="col-lg-6">
                            <h2 class="fw-bolder">Politica de Manejo de transacciones</h2>
                            <p class="lead fw-normal text-muted mb-0"><strong>Depósitos: </strong>Los depósitos pueden realizarse en pesos, dólares o euros. Todas las transacciones se registrarán y se convertirán a pesos colombianos.</p>
                            <hr>
                            <p class="lead fw-normal text-muted mb-0"><strong>Retiros: </strong>Los retiros que excedan los $20,000,000 pesos requerirán una autenticación adicional.</p>
                            <hr>
                            <p class="lead fw-normal text-muted mb-0"><strong>Registro de Transacciones: </strong>Todas las transacciones se registran con fecha y hora para mantener un historial detallado.</p>
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