# HistoricalWeatherData

Historical weather data app is created to give a user weather information in table or chart format. A user enters the location and period and gets data in PDF file.

## Technologies

Project created with:
* Java version: 1.8
* Maven version: 3.0
* Spring Boot version: 2.7.4

## Usage

### Table

* Request:

`http://localhost:8080/table` 

* Body:
``` json
{
    "startDateTime" : "2019-01-01",
    "location" : "Paris",
    "endDateTime" : "2019-01-03"
}
```

* Response:
``` json
[
    {
        "address": "Paris, Île-de-France, France",
        "latitude": 48.8572,
        "longitude": 2.34141,
        "tz": "Europe/Paris"
    },
    "C:\\курсы\\weatherdata\\src\\main\\resources\\pdftables\\weatherChangesTable.pdf"
]
```

* File:


![img.png](master/src/main/resources/forreadme/img.png))

### Chart

* Request:

`http://localhost:8080/chart/humidity-changes`

You can also use this requests for temperature and precipitation changes charts:\
`http://localhost:8080/chart/temperature-changes`
`http://localhost:8080/chart/precipitation-changes`

* Body:
``` json
{
    "startDateTime" : "2022-05-01",
    "location" : "Madrid",
    "endDateTime" : "2022-05-05"
}
```

* Response:
``` json
[
    {
        "address": "Madrid, Comunidad de Madrid, España",
        "latitude": 40.4196,
        "longitude": -3.69196,
        "tz": "Europe/Madrid"
    },
    "C:\\курсы\\weatherdata\\src\\main\\resources\\pdfchart\\humidityChangesData.pdf"
]
```

* File:

![img.png](src/main/resources/image1/img.png)

## Error cases

* You can only use dates from 1970-01-01 to now:

`"message": "Start date cannot be before 1970-01-01 00:00:00Z"`\
`"message": "Historical data requests cannot be in the future"`

* The period is limited:

`"message": "Incorrect period. Period must be less than six days."`

* Errors in date sequence:

`"message": "Incorrect period. Start date time must be earlier than end date time."`\
`"message": "Incorrect period. Dates can not be equals"`

* Empty request parameters:

`"location": "Location can not be empty."`\
`"startDateTime": "Date can not be empty."`

* Date format (required: "yyy-MM-dd"):

`"endDateTime": "Incorrect date time format."`

* Unknown or non-existent location:

`"message": "The geographic location of !unknown location! could not be found. If this is a partial address, please consider adding addition details such as the city, state or country."`

## Setup

To run this project, install it locally. As best use postman.

For project running `mvn spring-boot:run`\
For tests running `mvn clean test`
