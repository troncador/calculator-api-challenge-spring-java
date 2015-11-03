CREAR DOCKER:

sudo docker build -t troncador_tomcat .


Compilar:
``` bash
sudo docker run   \
   -v $PWD/m2:/root/.m2 \
   -v $PWD:/opt/build/   --rm   troncador_tomcat    mvn  -f /opt/build/pom.xml package
```


Correr:
``` bash 
    sudo docker run   -it \
      -p 8888:8080\
      -v $PWD/target/ROOT.war:/usr/local/tomcat/webapps/ROOT.war \
      --rm  \
    troncador_tomcat    /usr/local/tomcat/bin/catalina.sh run
```


