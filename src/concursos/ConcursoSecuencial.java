
package concursos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class ConcursoSecuencial extends ConcursoLimitado {
    private Map<String, Integer> problemaActual;

    public ConcursoSecuencial(String nombre, int numProblemas, int tiempoConcurso, int intent) {
        super(nombre, numProblemas, tiempoConcurso, intent);
        problemaActual = new HashMap<>();
    }
    
    
    @Override
    public void añadirEquipo(String ...nombres){
        super.añadirEquipo(nombres);
        for (String name : nombres) {
            problemaActual.put(name, 1);
        }
    }
    
    @Override
    public Envio registrarEnvio(String equipo, int numProblema, String respuesta) {
        if(respusCorrecta(equipo, numProblema, respuesta) && (this.Marcha() && !aceptarRespuesta(equipo, numProblema))){
            int actual = problemaActual.get(equipo);
            int intento = tenerIntento(equipo, numProblema);
            if((actual == numProblema) && (actual <= numero)){
                if(intento < limitarIntento){
                    Resultado estado;
                    if(this.tenerSolucion(numProblema).equals(respuesta)){
                        estado = Resultado.ACEPTADO;
                        cambiarPunto(equipo, 3);
                    }else{
                        estado = Resultado.RECHAZADO;
                        cambiarPunto(equipo, -1);
                    }
                    Envio envio1 = new Envio(equipo, numProblema, respuesta, estado);
                    envios.add(envio1);
                    if(actual <= numero && estado == Resultado.ACEPTADO){
                        problemaActual.put(equipo, ++actual);
                    }
                    
                    intento++;
                    quitarIntento(equipo, numProblema, intento);
                    return envio1;
                }else{
                    if(++actual <= numero)problemaActual.put(equipo, actual);
                }
            }
            return null;
        }else{
            return null;
        }
    }
    
    @Override
    public void mostrar() {
        super.mostrar();
        System.out.println("Estado del concurso: ");
        for (String to : equipos) {
            System.out.println("     " + to + " -> Problema actual: " + problemaActual.get(to));
        }
    }
}
