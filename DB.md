# train-ticket

train ticket platform

#### Database

#### User module

- Member
  - phoneNumber
- Passenger
  - memberId, fn, ln, id, passengerType (0: adult, 1: child, 2: disable)
- Ticket
  - memberId, passengerId, fn+ln, date, trainInfo, seatInfo

#### Business module

- Station
  - name, alias
- Trip
  - no, type, leavingStation, leavingAt, destinationStation, arrivingAt
- TripStation
  - tripNo, stationName, arrivingAt, leavingAt, duration, mileage(previous stop to this stop)
- TripCompartment
  - tripNo, no, type(0: regular, 1: premium, 2: sleep), totalSeat, totalRow, totalColumn
- TripSeat
  - tripNo, TripCompartmentNo, row, column
- DailyTrip
  - Date, tripInfo
- DailyTripStation
  - Date, tripStationInfo
- DailyTripCompartment
  - Date, tripCompartmentInfo
- DailyTripSeat
  - Date, tripSeatInfo, sellInfo (e.g., 0010)
- DailyTicket
  - Date, tripNo, leavingStation, leavingAt, destinationStation, arrivingAt, tripSeatsInfo(e.g., regular - 199 left, premium - 50 left)

#### Other module

- Quartz (Batch - system tables)
- Seata (Distributed transactional)
