public class Thunderbolt {
    private int codigo;
    private String nombre;
    private String habilidadPrincipal;
    private int nivelRedencion;
    private String misionAsignada;

    // Constructor
    public Thunderbolt(int codigo, String nombre, String habilidadPrincipal, int nivelRedencion, String misionAsignada) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.habilidadPrincipal = habilidadPrincipal;
        this.nivelRedencion = nivelRedencion;
        this.misionAsignada = misionAsignada;
    }

    // Getters y Setters
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getHabilidadPrincipal() {
        return habilidadPrincipal;
    }

    public void setHabilidadPrincipal(String habilidadPrincipal) {
        this.habilidadPrincipal = habilidadPrincipal;
    }

    public int getNivelRedencion() {
        return nivelRedencion;
    }

    public void setNivelRedencion(int nivelRedencion) {
        this.nivelRedencion = nivelRedencion;
    }

    public String getMisionAsignada() {
        return misionAsignada;
    }

    public void setMisionAsignada(String misionAsignada) {
        this.misionAsignada = misionAsignada;
    }
}
