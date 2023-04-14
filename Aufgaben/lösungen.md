# Lösungen

#### Aufgabe 0:
~~~~sql
version: "1.0"

services:

    postgres:
        image: postgres
        container_name: springintroduction
        hostname: postgres
        ports:
        - "5432:5432"
    environment:
        - POSTGRES_USER=postgres
        - POSTGRES_PASSWORD=password
        - POSTGRES_DB=springintroduction
~~~~
#### Aufgabe 1:
~~~~sql
CREATE TABLE IF NOT EXISTS Car (

    id BIGINT NOT NULL PRIMARY KEY,
    model varchar,
    brand varchar,
    created_at timestamptz

    );
~~~~

#### Aufgabe 2:
~~~~java
@Entity
@Builder
public class Car extends Auditable {

    @Id @GeneratedValue
    private Long id;
    private String model;
    private String brand;
    @CreatedDate
    private Instant date;

    public Car() {}

    public Car(final String model, final String brand){
        this.model = model;
        this.brand = brand;
    }
    public Car(final Long id, final String model, final String brand){
        this.id = id;
        this.model = model;
        this.brand = brand;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(final String name) {
        this.model = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(final String role) {
        this.brand = role;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)
            return true;
        if(!(o instanceof Car))
            return false;
        Car car = (Car) o;
        return Objects.equals(this.id, car.id) && Objects.equals(this.model, car.model)
                && Objects.equals(this.brand, car.brand);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id, this.model, this.brand);
    }

    @Override
    public String toString() {
        return "Car {" + "id=" + this.id + ", model='" + this.model + '\'' + ", brand='" + this.brand + '\'' + '}';
    }

}
~~~~

#### Aufgabe 3.1
~~~~java
@GetMapping("/cars")
    public ResponseEntity<List<Car>> all() {
        return ResponseEntity.ok(repository.findAll());
    }
~~~~
#### Aufgabe 3.2
~~~~java
@GetMapping("/car/{id}")
    public ResponseEntity<Car> get(@PathVariable final Long id) {
        final Optional<Car> car = repository.findById(id);
        if (car.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.of(car);
    }
~~~~
#### Aufgabe 3.3
~~~~java
@PostMapping("/cars")
    public ResponseEntity<Optional<Car>> replaceCar(
            @Valid @NotNull @RequestBody final CarCheckMappingRequest carCheckMappingRequest) {

        Car newCar = new Car(carCheckMappingRequest.getId(), carCheckMappingRequest.getModel(),
                carCheckMappingRequest.getBrand());

        return ResponseEntity.ok(Optional.of(repository.save(newCar)));
    }
~~~~
#### Aufgabe 3.4
~~~~java
@DeleteMapping("/cars/{id}")
    public ResponseEntity<Void> deleteCar(@PathVariable final Long id) {
        final boolean wasAvailable = repository.existsById(id);
        repository.deleteById(id);
        return wasAvailable ? ResponseEntity.ok().build() : ResponseEntity.noContent().build();
    }
~~~~
#### Aufgabe 3.5
~~~~java
@Transactional
@DeleteMapping("/cars/brand/{brand}")
public ResponseEntity<Void> deleteCarByBrand(@PathVariable final String brand) {
final int deleteCount = repository.deleteAllByBrand(brand);
        return deleteCount == 0 ? ResponseEntity.notFound().build() : ResponseEntity.noContent().build();
        }
~~~~

### Aufgabe 5
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

~~~~java
@Test
    void get() {
        // GIVEN
        final Optional<Car> input = Optional.of(
        Car.builder()
        .model("TestModel")
        .brand("TestBrand")
        .build()
        );
        final ResponseEntity<Car> expected = ResponseEntity.of(input);

        when(repository.findById(any())).thenReturn(input);

        // WHEN
        final ResponseEntity<Car> result = controller.get(1L);

        // THEN
        assertThat(result).isEqualTo(expected);

        }
~~~~

~~~~java
@Test
    void getEmpty() {
        // GIVEN
        final Optional<Car> input = Optional.empty();
        final ResponseEntity<Car> expected = ResponseEntity.of(input);

        when(repository.findById(any())).thenReturn(Optional.empty());

        // WHEN
        final ResponseEntity<Car> result = controller.get(1L);

        // THEN
        assertThat(result).isEqualTo(expected);

        }
~~~~

~~~~java
@Test
    void replaceCar() {
        // GIVEN (Vorbereitung)
        final Car expected = Car.builder().model("5").brand("BMW").build();
                when(repository.save(any())).thenReturn(expected);
        
        // WHEN (Methode aufrufen, die getestet werden soll)
        final ResponseEntity<Optional<Car>> result = controller.replaceCar(new CarCheckMappingRequest(1L, "5", "BMW"));

        // THEN (Eigentlicher Testfall)
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getBody()).isPresent();
        assertThat(result.getBody().get()).isEqualTo(expected);
        }
~~~~

~~~~java
@Test
    void deleteCar() {
            controller.deleteCar(1L);

            verify(repository).deleteById(1L);
            }
~~~~

~~~~java
@Test
    void deleteCarByBrand() {
        // GIVEN (Vorbereitung)
        final ResponseEntity<Void> expected = ResponseEntity.noContent().build();
                when(repository.deleteAllByBrand(any())).thenReturn(1);
        
        // WHEN (Methode aufrufen, die getestet werden soll)
        final ResponseEntity<Void> result = controller.deleteCarByBrand("BMW");

        // THEN (Eigentlicher Testfall)
        assertThat(result.hasBody()).isTrue();
        assertThat(result.getBody()).isEqualTo(expected.getBody());

        }
~~~~

### Aufgabe 6.1
~~~~java
@Test
    void deleteCarByBrandSuccess() {
        // GIVEN
        final String brand = "BMW";
        getRepository().save(new Car("5", brand));

        // WHEN
        final ResponseEntity<Void> response = getTestRestTemplate()
                .exchange(DELETE_CAR_BY_ROLE_URL + brand, HttpMethod.DELETE,
                        new HttpEntity<>(getDefaultHeaders()), Void.class);

        // THEN
        final List<Car> currentCars = getRepository().findAll();
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
        assertThat(currentCars).isEmpty();
    }
~~~~

~~~~java
@Test
    void deleteCarByBrandNothingDeleted() {
        // GIVEN
        final String brand = "BMW";

        // WHEN
        final ResponseEntity<Void> response = getTestRestTemplate()
                .exchange(DELETE_CAR_BY_ROLE_URL + brand, HttpMethod.DELETE,
                        new HttpEntity<>(getDefaultHeaders()), Void.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
~~~~

~~~~java
@Test
    void deleteCarById() {
        // GIVEN
        final long id = getRepository().save(new Car("5", "BMW")).getId();

        // WHEN
        final ResponseEntity<Void> response = getTestRestTemplate()
                .exchange(DELETE_CAR_BY_ID_URL + id, HttpMethod.DELETE,
                        new HttpEntity<>(getDefaultHeaders()), Void.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
~~~~

~~~~java
@Test
    void deleteCarByIdNoId() {
        // GIVEN
        final long id = getRepository().save(new Car("5", "BMW")).getId();
        getRepository().deleteById(id);

        // WHEN
        final ResponseEntity<Void> response = getTestRestTemplate()
                .exchange(DELETE_CAR_BY_ID_URL + id, HttpMethod.DELETE,
                        new HttpEntity<>(getDefaultHeaders()), Void.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);
    }
~~~~


### Aufgabe 6.2
~~~~java
@Test
    void getCarById() {
        // GIVEN
        final long id = getRepository().save(new Car("5", "BMW")).getId();

        // WHEN
        final ResponseEntity<Car> response = getTestRestTemplate()
                .exchange(GET_CAR_URL + "/" + id, HttpMethod.GET,
                        new HttpEntity<>(getDefaultHeaders()), Car.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
~~~~

~~~~java
@Test
    void getCarByIdNoSuccess() {
        // GIVEN
        final long id = getRepository().save(new Car("5", "BMW")).getId();
        getRepository().deleteById(id);

        // WHEN
        final ResponseEntity<Car> response = getTestRestTemplate()
                .exchange(GET_CAR_URL + "/" + id, HttpMethod.GET,
                        new HttpEntity<>(getDefaultHeaders()), Car.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
~~~~

~~~~java
@Test
    void getAllCarsSuccess() {
        // GIVEN
        getRepository().save(new Car("5", "BMW"));
        getRepository().save(new Car("3", "BMW"));

        // WHEN
        final ResponseEntity<List> response = getTestRestTemplate()
                .exchange(GET_CAR_URL, HttpMethod.GET,
                        new HttpEntity<>(getDefaultHeaders()), List.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
~~~~

~~~~java
@Test
    void getAllCarsEmptyList() {
        // GIVEN (Nothing)

        // WHEN
        final ResponseEntity<List> response = getTestRestTemplate()
                .exchange(GET_CAR_URL, HttpMethod.GET,
                        new HttpEntity<>(getDefaultHeaders()), List.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
~~~~

### Aufgabe 6.3
~~~~java
@Test
    void createNewCar() {
        // GIVEN
        final Car car = new Car("5", "BMW");

        // WHEN
        final ResponseEntity<Car> response = getTestRestTemplate()
                .exchange(POST_CAR_URL, HttpMethod.POST,
                        new HttpEntity<>(car,
                                getDefaultHeaders()), Car.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }
~~~~

~~~~java
@Test
    void replaceAlreadyExistingCar() {
        // GIVEN
        final long id = getRepository().save(new Car("TestModel", "TestBrand")).getId();
        final Car car = new Car(id, "5", "BMW");

        // WHEN
        final ResponseEntity<Car> response = getTestRestTemplate()
                .exchange(POST_CAR_URL, HttpMethod.POST,
                        new HttpEntity<>(car,
                                getDefaultHeaders()), Car.class);

        // THEN
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

~~~~

### Aufgabe 7
~~~~java
@Scheduled(cron = "${configuration.cron.schedule}")
    @Transactional
    public void deleteOldTableEntries(){
        System.out.println("Test");
        Instant time = Instant.now().minus(1, ChronoUnit.DAYS);;
        repository.deleteAllByDateBefore(time);
    }
~~~~