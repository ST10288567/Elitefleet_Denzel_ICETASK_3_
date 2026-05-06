Elitefleet_Denzel_ICETASK_3_


(No subject)
Yashodha Govender<yash.gov13@gmail.com>

​
Kaedon Naidoo​
Elite Fleet Management

Elite Fleet Management is an Android Studio prototype developed for PROG7311 Part 3 System Development. The application supports dealership operations including vehicle inventory management, appointment booking, claims workflow, reports and administrator profile features.

Technologies Used:

- Kotlin
- Jetpack Compose
- Android Studio
- Retrofit REST API integration
- Docker
- Node.js Express mock API
- MVVM architecture
- Repository pattern

Mandatory Requirements Covered

1. API Usage

The project uses a REST API created with Node.js and Express. The Android application connects to the API using Retrofit.

API features include:

- Login authentication
- Vehicle data retrieval
- Vehicle CRUD operations
- Booking data retrieval
- Booking CRUD operations
- Claims retrieval and status updates

2. Docker

The API is containerised using Docker.

Docker provides:

- Environment consistency
- Easier deployment
- Reduced setup problems between group members
- Same API behaviour on different machines

Run API:

```bash
docker compose up --build
