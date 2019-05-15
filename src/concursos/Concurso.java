package concursos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public abstract class Concurso {
    protected String nombre;
    protected final int numero;
    protected final int tiempo;
    protected ArrayList<String> equipos;
    protected ArrayList<String> soluciones;
    protected ArrayList<Envio> envios;
    protected long Inicio;
    protected long Final;
    protected boolean iniciado;
    protected Map<String, PuntuacionEquipo> puntuacion;

    public Concurso(String nombre, int numProblemas, int tiempoConcurso) {
        
        this.numero = numProblemas;
        this.nombre = nombre;
        this.tiempo = tiempoConcurso;
        this.soluciones = new ArrayList<>();
        this.equipos = new ArrayList<>();
        this.iniciado = false;
        this.puntuacion = new HashMap<>();
        this.envios = null;
    }
   
     public ArrayList<Envio> getEnvios() {
        return envios;
    }
    public String getNombre() {
        return nombre;
    }
    public int getNumero() {
        return numero;
    }
    public int getTiempo() {
        return tiempo;
    }
    public ArrayList<String> getEquipos() {
        return equipos;
    }
    public ArrayList<String> getSoluciones() {
        return soluciones;
    }
    public boolean Iniciado() {
        return iniciado;
    }
    
    
    public void añadirEquipo(String ...nombres){
        equipos.addAll(Arrays.asList(nombres));
        
        for (String to : nombres) {
            puntuacion.put(to, new PuntuacionEquipo(to));
        }
    }
    
    public boolean eliminarEquipo(String nombre){
        return equipos.remove(nombre);
    }
    
    public void agreSolucion(String solucion){
        if(soluciones.size() < numero){
            soluciones.add(solucion);
        }else{
            System.out.println("No puede agregar soluciones");
        }
    }
    
    
    
    public void cambiarPunto(String equipo, int puntos){
        PuntuacionEquipo Punto = puntuacion.get(equipo);
        Punto.addPunto(puntos);
        puntuacion.put(equipo, Punto);
    }
    
    public void iniciarConcurso(){
        this.iniciado = true;
        this.Inicio = System.currentTimeMillis();
        this.Final = this.Inicio + this.tiempo*60000;
        this.envios = new ArrayList<>();
    }
   
    
    public boolean Marcha(){
        return System.currentTimeMillis() < this.Final;
    }
    
    public boolean finalizado(){
        boolean finalizo = false;
        if(iniciado) {
            finalizo = System.currentTimeMillis() >= this.Final;
            
        }
        return finalizo;
    }
    
    
    public String tenerSolucion(int num){
        return soluciones.get(num - 1);
    }
    
    
    
    public abstract Envio registrarEnvio(String equipo, int numProblema, String respuesta);
    
    public boolean respusCorrecta(String equipo, int numProblema, String respuesta){
        
        return ((equipos.contains(equipo)&&(!"".equals(equipo) && equipo != null)))
                &&((!"".equals(respuesta) &&respuesta != null) && (numProblema <= numero &&numProblema > 0));
    }
    
    public boolean aceptarRespuesta(String equipo, int numProblema){
        for(int i = 0; i < envios.size(); i++){
            Envio envio1 = envios.get(i);
            if(envio1.getNombreEquipo().equals(equipo) && envio1.getNumeroProblema() == numProblema && envio1.getEvaluacion() == Resultado.ACEPTADO){
                return true;
            }
        }
        return false;
    }

    public void mostrar(){
        System.out.println("   " + nombre + "   ");
        System.out.println("Clasificación: ");
        for (String to : equipos) {
            System.out.println("    -" + to + " -> " + puntuacion.get(to).getPuntos());
        }
    }
    
   
    
    
}

