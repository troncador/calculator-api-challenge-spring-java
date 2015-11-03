package cl.doman.desafio.model;

import java.util.HashMap;
import java.util.Map;

public enum Operator {
  mult('*'), div('/'), add('+'), sub('-');
  
  private static Map<Character, Operator> symbolMap;
  
  private char symbol;
  private Operator(char symbol){
    this.symbol = symbol;
  }
  
  public char getChar(){
    return symbol;
  }
  
  public static Operator get(char symbol){
    if (symbolMap == null) {
      symbolMap = new HashMap<Character, Operator>();
      for (Operator operator: Operator.values() ){
        symbolMap.put(operator.symbol, operator);
      }
    }
    return symbolMap.get(symbol);
  }
  
  public Double calculate(Double val1, Double val2){
    switch(this){
      case mult:
        return val1 * val2;
      case div:
        return val1 / val2;
      case add:
        return val1 + val2;
      case sub:
        return val1 - val2;
      default:
        throw new RuntimeException("Operador no definido");
    }
  }
}
