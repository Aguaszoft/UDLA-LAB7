public class Persona {
    private String nombre;
    private int edad;
    private String seguroSocial;
    private Persona siguiente;

    public Persona(String nombre, int edad, String seguroSocial) {
        this.nombre = nombre;
        this.edad = edad;
        this.seguroSocial = seguroSocial;
        this.siguiente = null;
    }

    public String getNombre() {
        return nombre;
    }

    public int getEdad() {
        return edad;
    }

    public String getSeguroSocial() {
        return seguroSocial;
    }

    public Persona getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(Persona siguiente) {
        this.siguiente = siguiente;
    }

    @Override
    public String toString() {
        return "Nombre: " + nombre + ", Edad: " + edad + ", Seguro Social: " + seguroSocial;
    }
}
