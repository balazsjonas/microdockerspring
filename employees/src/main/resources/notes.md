olvasnivaló:
 * Craig Walls: Spring in Action, 5th (Manning)
 * Spring boot ref docs
 * udemy:
   * john thomson: Docker for java developers, Testing spring boot, reactive programming with spring 5
  * Beyond the 12 factor app
    * API first: (kivülről haladunk befelé)
    * vs clean architecture:
      * először üzleti logika, aztán API
    * credentials is külön tárolva :-)
      * GITHUB: figyelmeztet, ha security isssue van a repoban
    * telemetry
      * pl actuatorok



spring
* glue code
* template: (jdbc helyett pl)
*  plugin: mindent le lehet cserélni sajátra
*  tesztelhetőség

springboot
* autoconfig:
  * valami van a classpathon --> bekonfigolja
* actuator:
  * üzemeltethető alkalmazások
  
library vs keretrendszer:
* keretrendszer, ami engem hív
  *  ezek konténerek, feledete a komponensek tárolása
  *  ez egy map, kulcs a komponens neve
  *  ez a spring Application Context
* library amit én hívok

AOP példa springben:
* crosscuttingconcern:
  *  üzleti logikára merőleges funkció, pl biztonság, authentication/authorization
  
A spring nem kezeli a http protokollt (tomcat vagy jetty) 

springbootba bele lehet csomagolni a tomcatet

spring rétegek:
  fölsőbb réteg hívhat lefelé, alsóbb nem hívhat fölfelé

Spring 2.3.0:
  Docker támogatás!!!

config:
*  saját osztályok componenet scan
*  3rd party configgal

SpringTest
* Configokat cacheli
* Tesztek között nem hoz létre új ApplicationContexted
* Ha mégis van állapot:
  * @DirtiesContext --> ez a teszt szétbarmolja az applicationcontext
* Integrációs tesztek futtatása (ált package után)
  *IT-re filgyel: failsafe
* mvn pluginok:
  * unit: surefire
  * it: failsafe

http://maven.apache.org/guides/introduction/introduction-to-the-lifecycle.html

devtools:
* 2 osztálybetöltő:
  * egyik a saját kód
  * másik a függőségek
* újraindítja a tomcatetis
  * livereload
* Remote applications:
  * serveren futó alkalmazáshoz hozzáadhatjuk a saját classpathunkat

12 faktor:
* config nem az alkalmazás része
  * Infrastruktúra üzemeltetés repójában
    * pl Cloud Config mögöt repó van
  * háttérszolgáltatás:
    * db, cache
    * az alkalmazás összes állapota adatbázisban/cache-ben van

  * loggolás konzolra
        nem az alkalmazás feladata a naplózás, hanem az üzemeltetésé



linkek:
https://martinfowler.com/bliki/FeatureToggle.html
https://github.com/ff4j/ff4j

https://buildpacks.io/

https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1
https://spring.io/guides/gs/spring-boot-docker/


TODO
 * surefire vs failsafe
 * remote applications