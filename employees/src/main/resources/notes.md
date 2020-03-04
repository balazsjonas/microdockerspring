**olvasnivaló**:
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
* Beginning java EE 7
  * bean validation
  
**spring**
* glue code
* template: (jdbc helyett pl)
*  plugin: mindent le lehet cserélni sajátra
*  tesztelhetőség

**springboot**
* autoconfig:
  * valami van a classpathon --> bekonfigolja
* actuator:
  * üzemeltethető alkalmazások
  
**library vs keretrendszer**:
* keretrendszer, ami engem hív
  *  ezek konténerek, feledete a komponensek tárolása
  *  ez egy map, kulcs a komponens neve
  *  ez a spring Application Context
* library amit én hívok

**AOP példa springben**:
* crosscuttingconcern:
  *  üzleti logikára merőleges funkció, pl biztonság, authentication/authorization
  
A spring nem kezeli a http protokollt (tomcat vagy jetty) 

springbootba bele lehet csomagolni a tomcatet

spring rétegek:
  fölsőbb réteg hívhat lefelé, alsóbb nem hívhat fölfelé

Spring 2.3.0:
  Docker támogatás!!!

**config**:
*  saját osztályok componenet scan
*  3rd party configgal

**SpringTest**
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

**devtools**:
* 2 osztálybetöltő:
  * egyik a saját kód
  * másik a függőségek
* újraindítja a tomcatetis
  * livereload
* Remote applications:
  * serveren futó alkalmazáshoz hozzáadhatjuk a saját classpathunkat

**12 faktor**:
* config nem az alkalmazás része
  * Infrastruktúra üzemeltetés repójában
    * pl Cloud Config mögöt repó van
  * háttérszolgáltatás:
    * db, cache
    * az alkalmazás összes állapota adatbázisban/cache-ben van
* loggolás konzolra
  * nem az alkalmazás feladata a naplózás, hanem az üzemeltetésé
* API first
  * apiblueprint, swagger/openAPI 

**RESTful**
  * SOAP vs REST:
    * nincs interfész leíró nyelv
    * SOAP inkább XML-re támaszkodik
    * REST nem kötődik formátumhoz
    * SOAP: protokoll független (HTTP-n szoktuk, de lehet JMS vagy akármi)
    * REST HTTP-re épül
      * kérés-válasz
      * POST/GET etc
      * MIME, content negotiation
      * status codes
        * https://httpstatusdogs.com/
      * URL + paramétereket tudunk átadni
      * header, cookie
      * "az alkalmazás erőforrások összessége és az ezeken végzett CRUD műveletek"
      * rossz példa:
        * localhost/doTransfer?from=A&to=B&amount=100
        * ez inkább RPC http/json felett
      * CRUD:
        * Read: GET
        * Delete: DELETE
        * Create: POST v. PUT
        * Update: POST v. PUT
      * POST vs PUT:
        * **idempotens**: az adott művelet megismételhető!!!
        * Ha idempotens, akkor lehet PUT
        * Ha nem idempotens, akkor POST kell, hogy legyen
        * POST: újraküldésre a böngészők szoktak ráküldezni
        * pl visszalépés keresési oldalra:
          * GET-et kell használni, szerver oldalon nincs állapotváltozás
            * url-ben van --> bookmarkolható
            * probléma:
              * ékezet
              * méretkorlátok (szabványban nincs, de az eszközökben (router, böngésző, szerver) van)
              * megjelenik a history-ban 
          * POST:
            * minta: redirect after post
            * találatok GETtel jönnek le
            * probléma: adatokat kell adni
              * URL paraméter (ez sok lehet)
              * Session, de maradhat hátra adat
              * FLASH scope: kötvetkező get előtt kiveszi

**notes**
 * entitás csak repo és service között utazik
 * service réteg felett csak DTO utazzon
   * biztonság, nem menjen ki nem kívánt adat, entitás managed
 * command & query segregation:
   * vannak olyan metódus ami módosít és van olyan ami lekérdez
   * ne keveredjen pl AtomicInteger::addAndGet()   
   * egyiket kell tranzakcióba rakni a másikat nem
   * bejövő adat: command/query/request
   * kimenő adat: DTO/response
     * hátrány sok osztály és konvertálgatni kell (ModelMapper, Lombok)
     * 3 különböző DTO getre, queryre és updatere 

**Hibakezelés**
  * annotációval nem lehet, csak web.xml-lel
  * saját exceptionre @ResponseStatus annotáció
  * globális ExceptionResolver
  * lokálisan, controlleren belül @ExceptionHandler
  * külön *ControllerAdvice annotációval ellátott osztály az összes kontrollerre vonatkozik
  
**Integrációs teszt**
* SpringBootTest: egész alkalmazás elindul
* WebMvcTest csak a controller indul el és a Service réteget mockolni kell (Mockito vagy @MockBean)
* MockMvc (még ez sem indít servletet pl tomcatet)
* LocalServerPort-tal lekérdezhető a port, 

**Validáció**
* Referencia implementáció: Hibernate 
* ne réteghez legyen kötve, hanem az adatot hordozó beanhez
* paraméterre @Valid-ot kell rakni
* Beginning java EE 7

**Configuration**
* precedencia:
  * application.properties < OS environment < parancssori paraméterek
* @Value: egyszer felolvassa
* environment.getProperties(): Resten keresztül változtatható
* Profiles:
  * a profile elavult
  * ConditionalBean preferált
  
**Naplózás**
  * Springen belül Commons Logging, de saját implementációjuk van
  * best practice
    * SLF4j
    * Lombok   

**Adatbázis**
 * JDBCtemplate
   * ResultSetből mappelést nekünk kell végezni, minden mást elvégez
 * séma iniciaálizálás
   * pl: CommandLineRunnert kell implementálni, persze élesben sose :-)
   * Flyway, liquibase 
 * Spring Data JPA
 * deleteAll:
   * prodban nem lehet teszt kód
   * clean architecture: külön jar, de ne legyen deployolva
 * DataSource:
   * ez egy factory, 
   * ez csak a connection paramétereit tartalmazza
   * ettől lehet kérni connections
   * a legtöbb tud connection-poolt is
     * Spring connection pool: Hikari
 * implementáció: JDBC driverben
 * entitás:
   * kell, hogy legyen ID-ja
 * CRUD műveletek EntityManageren keresztül
 * memóriában levő managed állapotban lévő entitások: Persistence context
 * commit után detached állapot, aztán a gc összeszedi
   

**linkek**:
* https://martinfowler.com/bliki/FeatureToggle.html
* https://github.com/ff4j/ff4j
* https://buildpacks.io/
* https://spring.io/blog/2020/01/27/creating-docker-images-with-spring-boot-2-3-0-m1
* https://spring.io/guides/gs/spring-boot-docker/



**TODO**
 * surefire vs failsafe
 * remote applications
