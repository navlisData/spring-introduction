# Aufgaben

In dieser Aufgabe werden wir uns mit der Entwicklung einer Webanwendung zur Verwaltung von 
Autos in einem Autohaus befassen. Als Entwickler wirst du in der Lage sein, eine RESTful-API 
aufzubauen, die es den Benutzern ermöglicht, Autos zu erstellen, zu aktualisieren, 
anzuzeigen und zu löschen. Darüber hinaus wirst du auch in der Lage sein, Unit-Tests 
durchzuführen, um sicherzustellen, dass deine Anwendung fehlerfrei und stabil ist.

Als Grundlage werden wir das Spring Boot-Framework verwenden, um schnell und effizient eine 
leistungsstarke Webanwendung zu erstellen. Wir werden auch verschiedene Datenbank- und 
Testtechnologien einsetzen, um sicherzustellen, dass unsere Anwendung stabil und robust ist.

### Aufgabe 1:
Erstelle ein SQL-Schema für eine Tabelle namens Car, das die folgenden Spalten enthält:

- **id** (integer): Die eindeutige ID des Autos. Dieses Feld sollte als Primary Key festgelegt werden und darf nicht null sein.
- **model** (text): Der Name des Auto-Modells.
- **brand** (text): Der Name des Auto-Herstellers.
- **created_at** (timestamp with time zone): Das Datum und die Uhrzeit, zu denen das Auto erstellt wurde. Dieses Feld sollte vom Typ **timestamptz** sein.

Die Tabelle sollte so gestaltet sein, dass sie die grundlegenden Informationen zu einem Auto speichern kann, einschließlich der Marke, des Modells und des Erstellungszeitpunkts. Um sicherzustellen, dass jedes Auto eine eindeutige ID hat, sollte die **id**-Spalte als Primary Key definiert werden. Darüber hinaus sollte sichergestellt werden, dass **id** nicht **null** sein kann.

Zusätzlich zu den Spalten in der Tabelle **Car** können auch andere Spalten hinzugefügt werden, um zusätzliche Informationen zu den Autos zu speichern, wie z.B. die Farbe, die Kilometerzahl oder der Preis.


### Aufgabe 2:
Erstelle eine Java-Klasse namens Car, die die folgenden Eigenschaften enthält:

- **id** (Datentyp Long): Die eindeutige ID des Autos.
- **model** (Datentyp String): Der Name des Auto-Modells.
- **brand** (Datentyp String): Der Name des Auto-Herstellers.
- **date** (Datentyp Instant): Das Datum und die Uhrzeit, zu denen das Auto erstellt wurde.

Die id-Eigenschaft sollte als Primary Key definiert werden und automatisch generiert werden, 
wenn ein neues Auto erstellt wird. Hierfür sollten die Annotationen **@Id** und **@GeneratedValue** 
verwendet werden. Die **@Id**-Annotation gibt an, dass die Eigenschaft als Primary Key verwendet 
werden soll, während die **@GeneratedValue**-Annotation angibt, dass der Wert automatisch generiert 
werden soll.

Die date-Eigenschaft sollte mithilfe der Annotation **@CreatedDate** automatisch gesetzt werden, 
wenn das Auto erstellt wird. Diese Annotation wird von Spring Data JPA bereitgestellt und wird 
verwendet, um die Eigenschaft automatisch mit der aktuellen Zeit zu füllen.

Erstelle Konstruktoren, Attribute, Getter und Setter für alle Eigenschaften. Der Konstruktor 
sollte eine Methode sein, die alle Eigenschaften der Klasse als Parameter akzeptiert und eine 
neue Instanz der Klasse erstellt. Die Getter- und Setter-Methoden sollten verwendet werden, 
um auf die Eigenschaften der Klasse zuzugreifen und sie zu aktualisieren.

### Aufgabe 3:
Ergänze den Controller namens CarDealershipController, um die folgende Methoden:

#### Aufgabe 3.1
- **all()**: Eine **@GetMapping**-Methode, die eine Liste aller Autos zurückgibt. Die Methode 
sollte den Endpunkt **/cars** verwenden und eine **ResponseEntity<List<Car>>** zurückgeben. 
Die @GetMapping-Annotation gibt an, dass diese Methode auf einen GET-Request antworten 
soll. Die Rückgabe von ResponseEntity ermöglicht es, die HTTP-Statuscodes und -Header 
anzupassen, die zurückgegeben werden sollen.

#### Aufgabe 3.2
- **get()**: Eine **@GetMapping**-Methode, die ein spezifisches Auto anhand seiner ID zurückgibt. 
Die Methode sollte den Endpunkt **/car/{id}** verwenden und eine ResponseEntity<Car> zurückgeben. 
Die @PathVariable-Annotation gibt an, dass die ID als Pfadvariable in der URL verwendet wird. 
Die Methode sollte einen Parameter mit dem Namen id und dem Datentyp Long akzeptieren.

#### Aufgabe 3.3
- **replaceCar()**: Eine **@PostMapping**-Methode, die ein vorhandenes Auto durch ein neues Auto ersetzt. 
Die Methode sollte den Endpunkt **/cars** verwenden und eine ResponseEntity<Optional<Car>> zurückgeben. 
Die @Valid, @NotNull und @RequestBody-Annotationen geben an, dass die Eingabe validiert werden 
und dass sie im Body des HTTP-Requests übergeben wird. Die Methode sollte einen Parameter mit 
dem Namen carCheckMappingRequest und dem Datentyp CarCheckMappingRequest akzeptieren.

#### Aufgabe 3.4
- **deleteCar()**: Eine **@DeleteMapping**-Methode, die ein spezifisches Auto anhand seiner ID löscht. 
Die Methode sollte den Endpunkt **/cars/{id}** verwenden und eine ResponseEntity<Void> zurückgeben. 
Die @PathVariable-Annotation gibt an, dass die ID als Pfadvariable in der URL verwendet wird. 
Die Methode sollte einen Parameter mit dem Namen id und dem Datentyp Long akzeptieren.

#### Aufgabe 3.5
- **deleteCarByBrand()**: Eine **@DeleteMapping**-Methode, die alle Autos eines bestimmten Herstellers 
löscht. Die Methode sollte den Endpunkt **/cars/brand/{brand}** verwenden und eine ResponseEntity<Void> 
zurückgeben. Die @PathVariable-Annotation gibt an, dass der Herstellername als Pfadvariable in der 
URL verwendet wird. Die Methode sollte einen Parameter mit dem Namen brand und dem Datentyp String 
akzeptieren.

### Aufgabe 4
Local Deployment: Local Deployment bedeutet, dass eine Anwendung auf einem lokalen Computer ausgeführt 
wird. Dies kann z.B. auf einem Entwicklungscomputer oder einer Testumgebung erfolgen, bevor die 
Anwendung auf einer produktiven Umgebung bereitgestellt wird. Dabei wird die Anwendung auf dem Computer 
installiert und ausgeführt, ohne dass sie über das Internet oder ein Netzwerk erreichbar ist. Der 
Vorteil von Local Deployment ist, dass Entwickler die Möglichkeit haben, ihre Anwendung in einer realistischen 
Umgebung zu testen und zu debuggen, bevor sie sie auf einer produktiven Umgebung bereitstellen.

Die Schritte für ein Local Deployment kannst du hier finden `localdeployment/README.md`.
Sind diese Schritte abgeschlossen, kannst du mithilfe des Programms Insomnia mit der API interagieren.
Probiere dies nun einmal aus. Füge Einträge hinzu, lösche sie und lasse sie dir ausgeben.

### Aufgabe 5
Unit Tests sind automatisierte Tests, die sicherstellen sollen, dass jede Komponente einer Anwendung 
(z.B. eine Methode oder eine Klasse) wie erwartet funktioniert. Sie werden in der Regel von Entwicklern 
geschrieben und ausgeführt, um sicherzustellen, dass der Code so funktioniert, wie er sollte. Der Vorteil 
von Unit Tests liegt darin, dass sie schnell ausgeführt werden können und Fehler frühzeitig erkannt 
werden, was die Fehlersuche und -behebung erleichtert.

Beim Mocking handelt es sich um eine Technik, bei der simuliert wird, dass eine bestimmte Abhängigkeit 
oder Komponente vorhanden ist. Das kann zum Beispiel eine Datenbank oder ein externer Service sein. 
Anstelle der tatsächlichen Abhängigkeit wird eine sogenannte Mock-Objekt eingefügt, welches das 
Verhalten des eigentlichen Objekts simuliert. Das ermöglicht es, Tests zu schreiben, die unabhängig 
von externen Abhängigkeiten sind und somit reproduzierbar und zuverlässig sind.

Schreibe in der Klasse CarDealershipControllerTest geeignete Unit Tests. Ein Beispiel
könnte wie folgt aussehen:

~~~~java
@Test
    void all() {
        // GIVEN (Vorbereitung)
        final List<Car> expected = List.of(Car.builder().model("TestModel").brand("TestBrand").build());
        when(repository.findAll()).thenReturn(expected);

        // WHEN (Methode aufrufen, die getestet werden soll)
        final ResponseEntity<List<Car>> result = controller.all();

        // THEN (Eigentlicher Testfall)
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getBody()).isEqualTo(expected);
        verify(repository).findAll();
    }
~~~~

### Aufgabe 6
InMemory-Tests auf Datenbanken sind eine Art von Unit-Tests, bei denen eine temporäre Datenbank 
im Arbeitsspeicher erstellt und für den Testbetrieb genutzt wird. Im Gegensatz zu einem echten 
Datenbankbetrieb, der auf einer physischen Festplatte läuft, können InMemory-Tests auf Datenbanken
schneller ausgeführt werden und sind unabhängig von einer echten Datenbank-Umgebung.

Ein Vorteil von InMemory-Tests auf Datenbanken ist die Geschwindigkeit, da die Datenbank im 
Arbeitsspeicher läuft und somit schneller ist als eine Datenbank auf der Festplatte. Zudem 
können InMemory-Tests unabhängig von einer echten Datenbank-Umgebung durchgeführt werden, 
was die Testumgebung portabler und einfacher zu verwalten macht. Auch lassen sich InMemory-Tests
auf Datenbanken automatisiert durchführen und wiederholen, wodurch Fehler schneller gefunden
und behoben werden können.

Ein Nachteil von InMemory-Tests auf Datenbanken ist, dass sie möglicherweise nicht alle Probleme
aufdecken können, die in einer echten Datenbank-Umgebung auftreten können, wie z.B. 
Konfigurationsfehler, Netzwerklatenz oder Probleme mit der Speicherung großer Datenmengen. 
Es ist daher wichtig, InMemory-Tests auf Datenbanken sorgfältig zu planen und auszuführen, 
um sicherzustellen, dass sie ausreichend aussagekräftig sind.

Vervollständige die Klasse...

#### Aufgabe 6.1
DeleteCarInMemoryTest

#### Aufgabe 6.2
GetCarInMemoryTest

#### Aufgabe 6.3
PostCarInMemoryTest

...durch geeignete Testfälle.

Als Input/Anregung für Testfälle für InMemory-Tests auf Datenbanken könnten folgende 
Edgecases betrachtet werden:

- Ein Test, der prüft, ob die Datenbank korrekt erstellt wird und alle notwendigen Tabellen und Spalten enthält
- Ein Test, der prüft, ob die CRUD-Operationen (Create, Read, Update, Delete) korrekt ausgeführt werden und ob die Daten in der Datenbank korrekt gespeichert und abgerufen werden können
- Ein Test, der prüft, wie sich die Datenbank bei einer großen Anzahl von Anfragen verhält
- Ein Test, der prüft, wie die Datenbank bei einem gleichzeitigen Zugriff mehrerer Nutzer reagiert

### Aufgabe 7
Ein Cron Job ist eine geplante Aufgabe, die regelmäßig und automatisch zu einem bestimmten 
Zeitpunkt ausgeführt wird. Es ist ein Prozess, der in regelmäßigen Abständen automatisch 
gestartet wird, um eine bestimmte Aufgabe auszuführen.

Um täglich alte Einträge zu löschen, könnte man beispielsweise einen Cron Job einrichten, 
der jeden Tag um Mitternacht läuft. Dieser Cron Job würde dann alle Datensätze aus der 
Datenbank abrufen, die älter als ein bestimmtes Datum sind, und sie löschen.

Vervollständige die Klasse CronTask, um Einträge, die älter als ein Tag sind, zu entfernen.
Hinweis: Mit ${configuration.cron.schedule} kannst du auf den Intervall zugreifen.