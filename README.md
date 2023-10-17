# Medical Data Management System 

#Introduction

The Medical Data Management System is a web application designed to store and retrieve medical data records. This system provides endpoints for adding, retrieving, and managing medical data entries. It also includes functionality for filtering data based on sensitivity levels and other criteria.

#Table of Contents
1. Endpoints
2. Controllers
3. Services
4. Entities
5. Database Connection
6. DTOs
7. Repositories
8. Enums
9. Error Handling
10. Usage

1. # Medical Data Endpoints<a name="endpoints"></a>
1. POST /health/person-health-data: Adds a single medical data entry.
2. POST /health/person-health-data-list: Adds a list of medical data entries.
3. GET /health/get-health-data-list-by-sensitivity: Retrieves medical data based on sensitivity levels.
4. POST /health/get-by-patient-ids: Retrieves medical data by patient IDs.


2. #Controllers<a name="controllers"></a>
MedicalDataController
1. addMedicalData: Adds a single medical data entry.
2. addMedicalDataList: Adds a list of medical data entries.
3. getMedicalDataBySensitivityLevel: Retrieves medical data based on sensitivity levels.
4. getMedicalDataByPatientIds: Retrieves medical data by patient IDs.

#3.Services<a name="services"></a>
MedicalDataService
1. saveMedicalData: Saves a single medical data entry.
2. saveMedicalDataList: Saves a list of medical data entries.
3. getMedicalDataByPatientIdList: Retrieves medical data by patient IDs.
4. getMedicalDataByPatientIds: Retrieves medical data by patient IDs.
5. getMedicalDataBySensitivityLevel: Retrieves medical data based on sensitivity levels.
6. convertMedicalDataToResponse: Converts medical data entity to response DTO.
7. filterValidMedicalData: Filters valid medical data based on sensitivity levels and doctor information.

4. #Entity Classes <a name="entity-classes"></a>

4.1 Doctor
Attributes:
doctorId (Long): Unique identifier for the doctor.

legalCode (String): Legal code of the doctor.

licenseValidTill (LocalDateTime): Date and time until the license is valid.

medicalSpeciality (MedicalSpeciality): Reference to the doctor's medical speciality.

person (Person): Reference to the person associated with the doctor.

4.2 MedicalSpeciality
Attributes:
specialityId (Long): Unique identifier for the medical speciality.
specialityName (String): Name of the medical speciality.

4.3 Person
Attributes:
personId (Long): Unique identifier for the person.

personal code (String): Personal code following a specific pattern (e.g., gender, birth year, month, day, society identifier).

emailAddress (String): Email address of the person.

name (String): First name of the person.

familyName (String): Last name of the person.

5.1 Database Connection
The application is configured to connect to a MySQL database. The connection details can be specified in the application.properties file

# Database connection properties
spring.datasource.url=jdbc:mysql://localhost:3306/health_data
spring.datasource.username= root
spring.datasource.password= *********
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver


# Hibernate properties
spring.jpa.show-sql=true
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQLDialect

# Logging
logging.level.org.hibernate.SQL=DEBUG
logging.level.org.hibernate.type.descriptor.sql.BasicBinder=TRACE

#swagger
springdoc.swagger-ui.use-root-path=true
springdoc.swagger-ui.path=/swagger.html
springdoc.api-docs.path=/docs

6. #DTOs<a name="dtos"></a>
#MedicalDataDto
1. Represents a data transfer object for medical data.
2. Contains details like doctor IDs, patient ID, classifier, doctors' report, etc.

#MedicalDataResponse
Represents a data transfer object for medical data responses.
Contains details like medical data ID, doctor IDs, patient ID, classifier, doctors' report, etc.

#PatientRequest
Represents a request DTO for retrieving medical data by patient IDs.
Contains a list of patient IDs.


7. #Repositories<a name="repositories"></a>
MedicalDataRepository: Handles database operations for medical data entities.

8. #Enums<a name="enums"></a>
MedicalDataSensitivityLevel: Represents sensitivity levels for medical data.

9. £Error Handling<a name="error-handling"></a>
The system handles errors with appropriate HTTP status codes and error messages.
Common error cases include missing request bodies and invalid data.

10. #Usage<a name="usage"></a>
1. Use the provided endpoints to add and retrieve medical data entries.
2. Ensure that valid data is provided for each request, including sensitive information.
3. Handle any errors returned by the system, making sure to address missing or invalid data.

For further details on how to interact with the API or customize the application, refer to the codebase and relevant Spring Boot documentation.

