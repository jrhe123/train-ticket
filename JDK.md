# train-ticket

train ticket platform

#### JDK 9 - 17 Changes

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
