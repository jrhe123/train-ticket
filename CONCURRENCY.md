# train-ticket

train ticket platform

#### How to design system to support high concurrency (e.g., 100M QPS)

- Frontend
  - CDN for static resources
  - Static html page (standalone), mixed with dynamic renderer
  - Countdown, loading
  - SMS code
- Backend
  - Microservices, allocate resources dynamic (e.g., ticket query API can be separated into one module)
  - Load balance
  - Sential, limit traffic
  - Cache: local cache & distributed cache
  - Token: no token request, will be aborted
  - Async: MQ
- Database
  - Different modules splitted into different database
  - Read / write split
  - Sharding tables
  - Duplicate necessary data, instead of multiple table searching
  - DDBS 💸
- Others
  - Splitted into different time period
  - Auto scaling
  - Waiting list & queue up
