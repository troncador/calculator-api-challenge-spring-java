package cl.doman.desafio.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import cl.doman.desafio.model.Operation;
import cl.doman.desafio.model.Operator;

@Controller
public class OperationController {
  static Logger log = LoggerFactory.getLogger(OperationController.class);

  @ExceptionHandler(value = IOException.class)
  public void defaultErrorHandler(HttpServletResponse res, HttpServletRequest req, Exception e)
      throws IOException {
    PrintWriter writer = res.getWriter();
    writer.append("null");
  }

  @RequestMapping(value = {"/"},
      method = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.HEAD, RequestMethod.OPTIONS,
          RequestMethod.PATCH, RequestMethod.PUT, RequestMethod.TRACE})
  @ResponseBody
  public String indexGet() {
    return "null";
  }

  @RequestMapping(value = {"/"}, method = RequestMethod.POST)
  @ResponseBody
  public String index(@RequestParam("query") String json) {
    try {
      Operation operation = new Operation();
      ObjectMapper mapper = new ObjectMapper();
      mapper.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

      JsonNode jsonNode = mapper.readValue(json, JsonNode.class);
      Character op = jsonNode.get("op").asText().charAt(0);
      Iterator<JsonNode> iterator = jsonNode.get("ops").elements();
      List<Double> doubleList = new ArrayList<Double>();
      while (iterator.hasNext()) {
        doubleList.add(iterator.next().asDouble());
      }
      operation.setOperand(doubleList);
      operation.setOperator(Operator.get(op));
      return operation.calculate().toString();
    } catch (Exception e) {
      return "null";
    }
  }
}
