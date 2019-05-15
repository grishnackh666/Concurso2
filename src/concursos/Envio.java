/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package concursos;

public class Envio {
    private final String nombre;
    private final int numero;
    private final String respuesta;
    private final Resultado evaluacion;

    public Envio(String NombreEquipo, int numeroProblema, String respuesta, Resultado evaluacion) {
        this.nombre = NombreEquipo;
        this.numero = numeroProblema;
        this.respuesta = respuesta;
        this.evaluacion = evaluacion;
    }

    public String getNombreEquipo() {
        return nombre;
    }

    public int getNumeroProblema() {
        return numero;
    }

    public String getRespuesta() {
        return respuesta;
    }

    public Resultado getEvaluacion() {
        return evaluacion;
    }
    
}
