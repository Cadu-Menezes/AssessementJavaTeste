package com.assessement.ExercicioDois;

/*
  Interface para logging de operações matemáticas
*/
public interface MathLogger {
    /**
        Registra uma operação matemática
        @param operation nome da operação realizada
        @param inputs valores de entrada da operação
    */
    void log(String operation, int[] inputs);
}