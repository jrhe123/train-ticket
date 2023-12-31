# train-ticket

train ticket platform

#### JDK 9 - 17 Changes

#### JDK 17

- Switch

```
Object a = "Hello";
switch(a) {
  case String b -> System.out.println("string b: " + b);
  case Integer b -> System.out.println("integer b: " + b);
  default -> System.out.println("default b: " + b);
}
```

- Sealed
  - sealed if parent class, at lease one child
  - sealed child class, must be final / sealed / non-sealed

```
public sealed class TestA permits TestB {}

public final class TestB extends TestA {}
```

#### JDK 16

- Record

```
public class TestRecord(){
  private String name;
  private String password;

  // getter & setter..
}

public Record TestRecord(String name, String password) {}
TestRecord testRecord = new TestRecord("name", "password");
testRecord.name(); // cannot mutate, same as final String
```

#### JDK 14

- NullPointerException

```
List list = null;
list.size();
```

- instanceof

```
Object a = "Hello";
if (a instanceof String) {
  String b = (String) a;
  System.out.println("b: " + b);
}

if (a instanceof String b) {
  System.out.println("b: " + b);
}
```

- multiple line String

```
String json1 = "{\n"
				+ "   \"name\":\"roy\"\n"
				+ "}";

String json2 = """
				{
				   "name":"roy"
				}
				""";
```

#### JDK 11

- single file app

  - cmd: java TestA.java
  - no need to javac

- shebang (#!)
  - use java to write script
  - #!/bin/bash/[java jdk path] --source 11
  - cmd: chmod +X my_shell_script
  - cmd: ./my_shell_script

```
#!/bin/bash/Users/jiaronghe/.sdkman/candidates/java/current/bin/java --source 11

public class TestA {
  public static void main(String[] args) {
    System.out.println("Hello");
  }
}
```

#### JDK 10

- var
  - can be infer the class type
  - initialized, only local variable
  - can not be null

#### JDK 9

- jshell (java playground)
  - cmd: jshell
  - exit: /exit
- module
  - module / package / class & interfaces
  - e.g., add module-info.java
    - same path of other packages
    - control which package you want to expose

```
module A

module testA {
  exports.com.testa.test1;
}


module B (require class in module A)

module testB {
  requires testa;
}
```
