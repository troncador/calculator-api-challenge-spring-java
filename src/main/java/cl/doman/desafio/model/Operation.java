package cl.doman.desafio.model;

import java.util.List;

public class Operation {
  private Operator operator;
  private List<Double> operand;
  
  public Operator getOperator() {
    return operator;
  }
  public void setOperator(Operator operator) {
    this.operator = operator;
  }
  public List<Double> getOperand() {
    return operand;
  }
  public void setOperand(List<Double> operand) {
    this.operand = operand;
  }
  public Double calculate() {
    Double value1 = operand.remove(0);
    for (Double value2 : operand ){
      value1 = operator.calculate(value1, value2);
    }
    return value1;
  }
  
  public String toString(){
    Boolean first = true;
    StringBuilder builder = new StringBuilder();
    for(Double d : operand){
      if (first){
       first = false; 
      } else {
       builder.append(",");
      }
      builder.append(d);
    }
    return String.format("{'op':'%c','ops':[%s]}", operator.getChar(), builder.toString());
  }
}
