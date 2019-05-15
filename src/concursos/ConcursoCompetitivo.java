package concursos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConcursoCompetitivo extends Concurso {
    
    private Map<Integer, Boolean> noResuelto;
    
    public ConcursoCompetitivo(String nombre, int numProblemas, int tiempoConcurso) {
        super(nombre, numProblemas, tiempoConcurso);
        noResuelto = new HashMap<>();
    }
    
    
    @Override
    public void agreSolucion(String solucion){
        if(soluciones.size() < numero){
            soluciones.add(solucion);
            noResuelto.put(soluciones.size(), false);
            if(soluciones.size() == numero){
                iniciado = true;
                this.Inicio = System.currentTimeMillis();
                this.Final = this.Inicio + this.tiempo*60000;
                this.envios = new ArrayList<>();
                
            }
        }else{
            System.out.println("No puede agregar soluciones");
        }
    }
    
    @Override
    public Envio registrarEnvio(String equipo, int numProblema, String respuesta) {
        if(respusCorrecta(equipo, numProblema, respuesta) && this.Marcha() && !aceptarRespuesta(equipo, numProblema)){
            if(!noResuelto.get(numProblema)){
                Resultado estadoActual;
                if(this.tenerSolucion(numProblema).equals(respuesta)){
                    estadoActual = Resultado.ACEPTADO;
                    noResuelto.put(numProblema, true);
                    cambiarPunto(equipo, 3);
                }else{
                    estadoActual = Resultado.RECHAZADO;
                    cambiarPunto(equipo, -1);
                }
                Envio envio = new Envio(equipo, numProblema, respuesta, estadoActual);
                envios.add(envio);
                return envio;
            }
            return null;
        }else{
            return null;
        }
    }
}
