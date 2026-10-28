# BancoModelo – Simulador de banca en línea

Aplicación web en **Java (Jakarta EE)** que simula un banco: los clientes se registran, abren cuentas de distintos tipos, hacen depósitos y retiros en varias monedas y consultan su historial. El sistema cobra comisiones y cuotas de mantenimiento mensuales de forma automática.

Proyecto académico construido con el patrón **MVC** (Servlets + JSP) y acceso a datos con **DAO** sobre MariaDB/MySQL.

## Funcionalidades

- **Registro e inicio de sesión** de clientes, con manejo de sesión HTTP (30 min).
- **Tres tipos de cuenta** con distinta cuota de mantenimiento:
  | Tipo | Cuota mensual |
  |---|---|
  | Ahorros | $5.000 |
  | Corriente | $10.000 |
  | Suprema | $50.000 |
- **Depósitos multimoneda**: pesos, dólares y euros, convertidos a COP según una tasa de cambio.
- **Retiros con comisión del 1 %**; los retiros de más de $20.000.000 exigen confirmar de nuevo la contraseña (autenticación adicional).
- **Historial de transacciones** por cuenta.
- **Cobro automático de mantenimiento**: un `ServletContextListener` programa una tarea que descuenta la cuota el día 1 de cada mes y la registra como transacción.
- **Informe diario**: clientes con movimientos del día, total retirado y total recaudado en comisiones y mantenimiento.
- **Configuración del perfil** del cliente.

## Arquitectura

```
src/main/java/
├── Modelo/        → Entidades de dominio
│                    Cuenta (abstracta) ← CuentaAhorros, CuentaCorriente, CuentaSuprema
│                    Cliente, Transaccion
├── DAO/           → Acceso a datos con JDBC y PreparedStatement
│                    ClienteDAO, CuentaDAO, TransaccionDAO, InformeDAO
├── Controlador/   → Servlets (login, registro, cuentas, depósito, retiro, informes...)
├── Servicios/     → Lógica de negocio (CobrarMantenimiento)
├── Listeners/     → Tarea programada mensual
└── Util/          → DBConnection

src/main/webapp/   → Vistas JSP, CSS y JS
```

### Conceptos de POO aplicados

- **Herencia y polimorfismo**: `Cuenta` es abstracta y define `cobrarComision()`. Cada tipo de cuenta lo implementa con su propia tarifa, y el servicio de mantenimiento recorre todas las cuentas sin saber de qué tipo es cada una.
- **Sobrecarga**: `ingresar(cantidad)` / `ingresar(cantidad, moneda)` y lo mismo para `retirar`.
- **Encapsulamiento** del saldo y de los datos del cliente.
- **Separación de responsabilidades**: las vistas no consultan la base de datos; los servlets usan los DAO.

## Tecnologías

- Java 11
- Jakarta EE 10 (Servlets, JSP)
- MariaDB / MySQL con JDBC (`mariadb-java-client`)
- Maven (empaquetado WAR)
- Desarrollado en NetBeans y desplegado en un servidor compatible con Jakarta EE 10 (p. ej. GlassFish 7 o Tomcat 10.1+)

## Cómo ejecutarlo

### 1. Base de datos

Crea la base de datos `banco-inmobiliaria`. Este esquema se reconstruyó a partir de las consultas de los DAO:

```sql
CREATE DATABASE `banco-inmobiliaria`;
USE `banco-inmobiliaria`;

CREATE TABLE clientes (
    cliente_id INT AUTO_INCREMENT PRIMARY KEY,
    nombre     VARCHAR(100),
    apellido   VARCHAR(100),
    ciudad     VARCHAR(100),
    telefono   VARCHAR(20),
    email      VARCHAR(150) UNIQUE,
    contrasena VARCHAR(255)
);

CREATE TABLE cuentas (
    cuenta_id      INT AUTO_INCREMENT PRIMARY KEY,
    cliente_id     INT NOT NULL,
    tipo_cuenta    VARCHAR(20),   -- ahorros | corriente | suprema
    estado         VARCHAR(20),
    saldo          DOUBLE DEFAULT 0,
    fecha_apertura DATE,
    FOREIGN KEY (cliente_id) REFERENCES clientes(cliente_id)
);

CREATE TABLE transaccionesbanco (
    transaccion_id    INT AUTO_INCREMENT PRIMARY KEY,
    cuenta_id         INT NOT NULL,
    tipo_transaccion  VARCHAR(30), -- Deposito | Retiro | Comision | Mantenimiento
    monto             DOUBLE,
    fecha_transaccion DATE,
    FOREIGN KEY (cuenta_id) REFERENCES cuentas(cuenta_id)
);
```

Ajusta usuario y contraseña en `src/main/java/Util/DBConnection.java`.

### 2. Compilar y desplegar

```bash
mvn clean package
```

Despliega `target/BancoModelo-1.0-SNAPSHOT.war` en tu servidor y abre http://localhost:8080/BancoModelo.

## Posibles mejoras

- Guardar las contraseñas con hash (BCrypt) en lugar de texto plano.
- Pasar las credenciales de la base de datos a variables de entorno.
- Usar `BigDecimal` para los montos en lugar de `double`.
- Hacer los retiros dentro de una transacción de base de datos.
- Agregar pruebas unitarias con JUnit para la lógica de cuentas.
