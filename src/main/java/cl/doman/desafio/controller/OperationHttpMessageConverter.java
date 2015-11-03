package cl.doman.desafio.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.doman.desafio.model.Operation;
import cl.doman.desafio.model.Operator;

public class OperationHttpMessageConverter extends AbstractHttpMessageConverter<Operation> {
  static Logger log =LoggerFactory.getLogger(OperationHttpMessageConverter.class);
  
  
  public OperationHttpMessageConverter(){
    List<MediaType> supportedMediaTypes = new ArrayList<MediaType>();
    supportedMediaTypes.add(MediaType.ALL);
    this.setSupportedMediaTypes(supportedMediaTypes);
  }
  
  
  
  @Override
  protected boolean supports(Class<?> clazz) {
    return Operation.class.equals(clazz);
  }

 
//  @Override
//  public boolean canRead(java.lang.Class<?> clazz, MediaType mediaType) {
//    return true;
//  }
  
  protected Operation readInternal(Class<? extends Operation> clazz, 
        HttpInputMessage httpInputMessage)
      throws  IOException, HttpMessageNotReadableException {
    try {
      Operation operation = new Operation();
      ObjectMapper mapper = new ObjectMapper();
      InputStream inputStream = httpInputMessage.getBody();
      mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
      mapper.configure(Feature.ALLOW_SINGLE_QUOTES, true);
      
      StringWriter writer = new StringWriter();
      IOUtils.copy(inputStream, writer);
      String str = java.net.URLDecoder.decode(writer.toString(),"UTF-8");
      
      log.info(str);
      
      JsonNode jsonNode = mapper.readValue(str, JsonNode.class);
      Character op = jsonNode.get("op").asText().charAt(0);
      Iterator<JsonNode> iterator = jsonNode.get("ops").elements();
      List<Double> doubleList = new ArrayList<Double>();
      while (iterator.hasNext()){
        doubleList.add(iterator.next().asDouble());
      }
      operation.setOperand(doubleList);
      operation.setOperator(Operator.get(op));
      return operation;
    } catch (Exception e){
      throw new IOException(e.getMessage(),e);
    }
  }

  @Override
  protected void writeInternal(Operation arg0, HttpOutputMessage arg1)
      throws IOException, HttpMessageNotWritableException {
  }


}
