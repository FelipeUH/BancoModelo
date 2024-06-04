package Modelo;

public class Cliente {
    private int id;
    private String nombre;
    private String apellido;
    private String ciudad;
    private String telefono;
    private String email;
    private String contrasena;
    
    public Cliente() {
    }

    public Cliente(String nombre, String apellido, String ciudad, String telefono, String email, String contrasena) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.ciudad = ciudad;
        this.telefono = telefono;
        this.email = email;
        this.contrasena = contrasena;
    }
    
    public int getId(){
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getCiudad() {
        return ciudad;
    }
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public String getApellido() {
        return apellido;
    }
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getContrasena() {
        return contrasena;
    }
    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }
}