package cl.doman.desafio.configuration;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import cl.doman.desafio.controller.OperationHttpMessageConverter;



@Configuration
//@EnableWebMvc
@ComponentScan(basePackages = "cl.doman.desafio.controller")
public class WebConfig extends WebMvcConfigurationSupport {
  static Logger log = LoggerFactory.getLogger(WebConfig.class);

  public WebConfig(){
    //this.con
  }
  
  @Override
  public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    configurer.enable();
  }

//  @Override
//  public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
//    converters.add(new OperationHttpMessageConverter());
//    this.addDefaultHttpMessageConverters(converters);
//  }

}
