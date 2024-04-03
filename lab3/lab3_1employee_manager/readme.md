__A__: Identify a couple of examples that use AssertJ expressive methods chaining.

* `assertThat(fromDb).isNull();`, `assertThat(found.getName()).isEqualTo(name);` and `assertThat(doesEmployeeExist).isTrue();` check if a single value is equal to other value, `null` or `true`.
* `assertThat(allEmployees).hasSize(3).extracting(Employee::getName).contains(alex.getName(), john.getName(), bob.getName());` checks if allEmployees has 3 elements and their name properties contain values of `alex`, `john` and `bob`.
* `assertThat(found).extracting(Employee::getName).containsOnly("bob");` checks that all elements in `found` have value "bob" in their name property.

__B__: Identify an example in which you mock the behavior of the repository (and avoid involving a 
database).

- In class `B_EmployeeService_UnitTest`: 
``` java
Mockito.when(employeeRepository.findByName(john.getName())).thenReturn(john);
Mockito.when(employeeRepository.findByName(alex.getName())).thenReturn(alex);
Mockito.when(employeeRepository.findByName("wrong_name")).thenReturn(null);
Mockito.when(employeeRepository.findById(john.getId())).thenReturn(Optional.of(john));
Mockito.when(employeeRepository.findAll()).thenReturn(allEmployees);
Mockito.when(employeeRepository.findById(-99L)).thenReturn(Optional.empty());
```

__C__: What is the difference between standard @Mock and @MockBean?

* `@Mock` is used by Mockito to mock classes in unit testing.
* `@MockBean` is used by Spring Boot to mock beans (that would normally be created by Spring components, but are instead Mockito mocks) in integration testing.

__D__: What is the role of the file “application-integrationtest.properties”? In which conditions will it be 
used?

`application-integrationtest.properties` serves as a configuration file for integration test-specific environment. It will be used in Spring integration tests.

__E__: the sample project demonstrates three test strategies to assess an API (C, D and E) developed with 
SpringBoot. Which are the main/key differences?

* C: Tests the controller layer by mocking the service layer (without a database).
* D: Tests all layers (database, service, repository and controller) using Spring Boot application context.
* E: Tests all layers using an HTTP client.
