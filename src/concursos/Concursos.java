package concursos;

import java.util.ArrayList;

public class Concursos {
    public static void main(String[] args) {
        
        Concurso concurso1 = new ConcursoLimitado("Sesión 1", 2, 5, 1);
        concurso1.agreSolucion("23");
        concurso1.agreSolucion("15");
        
        Concurso concurso2 = new ConcursoCompetitivo("Sesión 2", 3, 15);
        concurso2.agreSolucion("AACTG");
        concurso2.agreSolucion("null");
        concurso2.agreSolucion("[13, 98]");
        
        Concurso concurso3 = new ConcursoSecuencial("Sesión 3", 3, 30, 2);
        concurso3.agreSolucion("null");
        concurso3.agreSolucion("[0, 3]");
        concurso3.agreSolucion("AAA");
        
        ArrayList<Concurso> listaConcurso = new ArrayList<>();
        listaConcurso.add(concurso1);
        listaConcurso.add(concurso2);
        listaConcurso.add(concurso3);
        
        for (Concurso to : listaConcurso) {
            to.añadirEquipo("Equipo 1", "Equipo 2", "Equipo 3");
            to.iniciarConcurso();
            for(int i = 1; i <= to.getSoluciones().size(); i++){
                to.registrarEnvio("Equipo 1", i, "null");
                to.registrarEnvio("Equipo 2", i, "null");
                to.registrarEnvio("Equipo 3", i, "null");
            }
        }
        
        listaConcurso.forEach((concurso) -> {
            concurso.mostrar();
        });
        
    }
}
