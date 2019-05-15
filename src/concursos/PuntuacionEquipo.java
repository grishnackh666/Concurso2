
package concursos;

public class PuntuacionEquipo {
    private final String equipo;
    private int puntos;

    public PuntuacionEquipo(String equipo) {
        this.equipo = equipo;
        this.puntos = 0;
    }
    
    public void addPunto(int a){
        this.puntos += a;
    }
    public void setPuntos(int puntos) {
        this.puntos = puntos;
    } 
    public String getEquipo() {
        return equipo;
    }
    public int getPuntos() {
        return puntos;
    }
    
}
