
## Drones Service REST API

---
📜 **START**

### Introduction

There is a major new technology that is destined to be a disruptive force in the field of transportation: **the drone**. Just as the mobile phone allowed developing countries to leapfrog older technologies for personal communication, the drone has the potential to leapfrog traditional transportation infrastructure.

Useful drone functions include delivery of small items that are (urgently) needed in locations with difficult access.

---

### Task description

We have a fleet of **10 drones**. A drone is capable of carrying devices, other than cameras, and capable of delivering small loads. For our use case **the load is medications**.

A **Drone** has:
- serial number (100 characters max);
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight);
- weight limit (500gr max);
- battery capacity (percentage);
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED, RETURNING).

Each **Medication** has:
- name (allowed only letters, numbers, ‘-‘, ‘_’);
- weight;
- code (allowed only upper case letters, underscore and numbers);
- image (picture of the medication case).

Develop a service via REST API that allows clients to communicate with the drones (i.e. **dispatch controller**). The specific communicaiton with the drone is outside the scope of this task.

The service should allow:
- registering a drone;
- loading a drone with medication items;
- checking loaded medication items for a given drone;
- checking available drones for loading;
- check drone battery level for a given drone;

Feel free to make assumptions for the design approach.

---

### Requirements

While implementing your solution **please take care of the following requirements**:

#### Functional requirements

- There is no need for UI;
- Prevent the drone from being loaded with more weight that it can carry;
- Prevent the drone from being in LOADING state if the battery level is **below 25%**;
- Introduce a periodic task to check drones battery levels and create history/audit event log for this.

---

#### Non-functional requirements

- Input/output data must be in JSON format;
- Your project must be buildable and runnable;
- Your project must have a README file with build/run/test instructions (use DB that can be run locally, e.g. in-memory, via container);
- Required data must be preloaded in the database.
- JUnit tests are optional but advisable (if you have time);
- Advice: Show us how you work through your commit history.

---
### How to build

#### Requirements

- A favourite code editor IDE (IntelliJ IDEA or Eclipse)
- JDK 1.8 or later
- Gradle 4+ or Maven 3.2+
- Postman (For testing APIs)
  

### Steps by step for building and running the project locally

- Clone the git repository 

- Open the cloned project in your editor or IDE

- Update the Maven dependencies

- Build the project and run

- You can run the JUnit test cases to assert that everything is working correctly before proceeding


---

### Testing the API
- Assumption made: Multiple medicines can be loaded to a drone as long as list items do not exceed the weight limit of the drone
- ContentType is application/json 
- Base URL: http://localhost:8080/api/

---

####  Register Drone

Endpoint: **POST  /drone/register**

The payload should be in json as shown below
- serialNumber of Drone (100 characters max)
- model (Lightweight, Middleweight, Cruiserweight, Heavyweight)
- weightlimit (Maximum 500)
- batteryCapacity (Maximum 100)
- state (IDLE, LOADING, LOADED, DELIVERING, DELIVERED 

``` json
{
    "serialNumber": "DRZ6KULBUA9S", 
    "model": "Lightweight", 
    "weightLimit": 450, 
    "batteryCapacity": 64, 
    "state": "IDLE"
} 
```

Success Response
``` json
{
    "status": "success",
    "message": "Drone registered successfully",
    "data": {
        "serialNumber": "DRZ6KULBUA9S",
        "model": "Lightweight",
        "weightLimit": 450.0,
        "batteryCapacity": 64.0,
        "state": "IDLE"
    }
}
```

Error response 
``` json
{
    "status": "error",
    "message": "An error occurred whiles validating data!",
    "data": {
        "model": "must not be empty",
        "weightLimit": "Weight limit can be 500 gram maximum"
    }
}
```


---

####  Registering Medication

Endpoint: **POST /medication/register**

The payload should be in json as shown below
- name (allowed only letters, numbers, hyphen, and underscore)
- code (allowed only letters, numbers, and underscore);
- weight
- image

``` json
{
    "name": "Paracetamol", 
    "code": "LBUA9S", 
    "weight": 250, 
    "image": "paracetamol.png"
} 

```

Success Response
``` json
{
    "status": "success",
    "message": "Medication created successfully",
    "data": {
        "name": "Paracetamol",
        "weight": 250.0,
        "code": "LBUA9S",
        "image": "paracetamol.png"
    }
}
```

Error Response 
``` json
{
    "status": "error",
    "message": "An error occurred whiles validating data!",
    "data": {
        "code": "Allowed only upper case letters, underscore and numbers",
        "name": "Allowed only letters, numbers, hyphen and underscore"
    }
}
```

---

####  List Registered Medications

Endpoint: **GET /medication/list**

Response
``` json
{
    "status": "success",
    "message": "Medication created successfully",
    "data": {
        "name": "Paracetamol",
        "weight": 250.0,
        "code": "LBUA9S",
        "image": "paracetamol.png"
    }
}
```

---

####  Checking Available Drones For Loading

Endpoint: **GET /drone/available**

Response
``` json
{
    "status": "success",
    "message": "Available drones loaded successfully",
    "data": [
        {
            "serialNumber": "DRZ6KULBUA9S",
            "model": "Lightweightd",
            "weightLimit": 450.0,
            "batteryCapacity": 64.0,
            "state": "IDLE"
        }
    ]
}
```

---

####  Load Drone With Medication Items

Endpoint: **POST /delivery/load**

The payload should be in json as shown below
- serialNumber of Drone (100 characters max)
- medications (List of medication codes - Drones can handle more than one item)
- source (Point of departure)
- destination (Point of Arrival)

``` json
{
    "serialNumber": "DRZ6KULBUA9S",
    "medications": [
        "LBUA9S"
    ],
    "source": "Plovdiv",
    "destination": "Sofia"
} 
```

Success Response 
``` json
{
    "status": "success",
    "message": "Drone loaded successfully",
    "data": {
        "delivery": {
            "id": 2,
            "drone": "DRZ6KULBUA9S",
            "source": "Plovdiv",
            "destination": "Sofia",
            "createdAt": "2022-05-30T18:46:45.174372Z",
            "dispatchedAt": null,
            "deliveredAt": null
        },
        "items": [
            {
                "id": 2,
                "deliveryId": 2,
                "medication": "LBUA9S"
            }
        ],
        "timestamp": "2022-05-30T18:46:45.178499Z"
    }
}
```
Error Response

```json
{
    "status": "error",
    "message": "Drone is not available! State: LOADED",
    "data": {
        "serialNumber": "DRZ6KULBUA9S",
        "model": "Lightweight",
        "weightLimit": 450.0,
        "batteryCapacity": 64.0,
        "state": "LOADED"
    }
}
```

---

####  Check Loaded Medication Items For A Given Drone

Endpoint: **GET /delivery/items/{serialNumber}**

Response
``` json
{
    "status": "success",
    "message": "Drone items loaded successfully",
    "data": {
        "delivery": {
            "id": 1,
            "drone": "DRZ6KULBUA9S",
            "source": "Plovdiv",
            "destination": "Sofia",
            "createdAt": "2022-05-30T19:23:53.938253Z",
            "dispatchedAt": null,
            "deliveredAt": null
        },
        "items": [
            {
                "name": "Paracetamol",
                "weight": 250.0,
                "code": "LBUA9S",
                "image": "paracetamol.png"
            }
        ]
    }
}
```

---

####  Dispatch Drone For Delivery

Endpoint: **POST /delivery/dispatch**

The payload should be in json as shown below
- serialNumber of Drone (100 characters max)

``` json
{
    "serialNumber": "DRZ6KULBUA9S"
} 
```

Success Response
``` json
{
    "status": "success",
    "message": "Drone dispatched successfully",
    "data": {
        "delivery": {
            "id": 1,
            "drone": "DRZ6KULBUA9S",
            "source": "Plovdiv",
            "destination": "Sofia",
            "createdAt": "2022-05-30T19:23:53.938253Z",
            "dispatchedAt": "2022-05-30T19:25:41.566389Z",
            "deliveredAt": null
        },
        "items": [
            {
                "id": 1,
                "deliveryId": 1,
                "medication": "LBUA9S"
            }
        ],
        "timestamp": "2022-05-30T19:25:41.567619Z"
    }
}
```
Error Response

```json
{
  "status": "error",
  "message": "No delivery for drone with serial number DRZ6KULBUA9S!",
  "data": null
}
```


---

####  Set Drone Items As Delivered

Endpoint: **POST /delivery/delivered**

The payload should be in json as shown below
- serialNumber of Drone (100 characters max)

``` json
{
    "serialNumber": "DRZ6KULBUA9S"
} 
```

Success Response
``` json
{
    "status": "success",
    "message": "Drone delivered successfully",
    "data": {
        "delivery": {
            "id": 1,
            "drone": "DRZ6KULBUA9S",
            "source": "Plovdiv",
            "destination": "Sofia",
            "createdAt": "2022-05-30T19:23:53.938253Z",
            "dispatchedAt": "2022-05-30T19:25:41.562477Z",
            "deliveredAt": "2022-05-30T19:27:20.441577Z"
        },
        "items": [
            {
                "id": 1,
                "deliveryId": 1,
                "medication": "LBUA9S"
            }
        ],
        "timestamp": "2022-05-30T19:27:20.442013Z"
    }
}
```
Error Response

```json
{
  "status": "error",
  "message": "No delivery for drone with serial number DRZ6KULBUA9S!",
  "data": null
}
```

