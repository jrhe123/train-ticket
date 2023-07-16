# train-ticket

train ticket platform

#### Springboot 3

- AOT vs. JIT
- JIT: just in time
- AOT: ahead of time
  - not support AOP
  - pre-compile, work with machine code, .exe instead of jar

#### JIT issue

- warm up
  - no traffic
  - use 1 thread to warm up the feat (required JIT compile -> machine code execute)
  - let traffic in
