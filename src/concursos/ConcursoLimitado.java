
package concursos;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class ConcursoLimitado extends Concurso {
    protected int limitarIntento;
    private ArrayList<Intento> intentos;
    
    public ConcursoLimitado(String nombre, int numProblemas, int tiempoConcurso, int numIntento) {
        super(nombre, numProblemas, tiempoConcurso);
        this.limitarIntento = numIntento;
        intentos = new ArrayList<>();
    }

    
    public int tenerIntento(String equipo, int prob){
        for(int i = 0; i < this.intentos.size(); i++){ 
            if(intentos.get(i).getEquipo().equals(equipo) && intentos.get(i).getNumPregunta() == prob){
                return intentos.get(i).getNumPregunta();
            }
        }
        this.intentos.add(new Intento(equipo, prob));
        
        return 0;
    }
    
    public void quitarIntento(String equipo, int prob, int intent){
        for(int j = 0; j < this.intentos.size(); j++){ 
            if(intentos.get(j).getEquipo().equals(equipo) && intentos.get(j).getNumPregunta() == prob){
                intentos.get(j).setNumIntento(intent);
            }
        }
    }

    @Override
    public Envio registrarEnvio(String equipo, int numProblema, String respuesta) {
        if(respusCorrecta(equipo, numProblema, respuesta) && (this.Marcha() && !aceptarRespuesta(equipo, numProblema))){
            int intento = tenerIntento(equipo, numProblema);
            if(intento < limitarIntento){
                Resultado estadoActual;
                if(this.tenerSolucion(numProblema).equals(respuesta)){
                    estadoActual = Resultado.ACEPTADO;
                    cambiarPunto(equipo, 3);
                }else{
                    estadoActual = Resultado.RECHAZADO;
                    cambiarPunto(equipo, -1);
                }
                Envio envio = new Envio(equipo, numProblema, respuesta, estadoActual);
                envios.add(envio);
                intento++;
                quitarIntento(equipo, numProblema, intento);
                return envio;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }
}
