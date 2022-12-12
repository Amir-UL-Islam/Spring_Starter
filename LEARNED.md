``` 
<version>${project.parent.version}</version>
``` 
from ```porm.xml``` file.
- This Line is Added to remove This Line of Error.
- Plugin 'org.springframework.boot:spring-boot-maven-plugin:' not found.


```
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-data-jpa</artifactId>
</dependency>
```
- Comment These Lines of Codes for:
  ``` Failed to configure a DataSource: 'url' attribute is not specified and no embedded datasource could be configured. ```
  ``` Reason: Failed to determine a suitable driver class ```

```aidl
// Create a Main class
public class Main {
  int x;  // Create a class attribute

  // Create a class constructor for the Main class
  public Main() {
    x = 5;  // Set the initial value for the class attribute x
  }

  public static void main(String[] args) {
    Main Obj = new Main(); // Create an object of class Main (This will call the constructor)
    System.out.println(Obj.x); // Print the value of x
  }
}

```
- Constructor
