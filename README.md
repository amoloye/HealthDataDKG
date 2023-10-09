# HealthDataDKG

Spring Boot Application Documentation
Introduction
This documentation provides an overview of a Spring Boot application designed to manage doctor information. The application uses Hibernate for data persistence and provides API endpoints to perform CRUD (Create, Read, Update, Delete) operations on doctor records.

Table of Contents
Project Structure
Entity Classes
Database Configuration
API Endpoints
Running the Application

1. Project Structure <a name="project-structure"></a>
The project follows the standard Spring Boot project structure:

src/
|-- main/
|   |-- java/
|   |   |-- com.example/
|   |   |   |-- application/
|   |   |   |   |-- Application.java
|   |   |   |-- controllers/
|   |   |   |   |-- DoctorController.java
|   |   |   |-- models/
|   |   |   |   |-- Doctor.java
|   |   |   |   |-- MedicalSpeciality.java
|   |   |   |   |-- Person.java
|   |   |   |-- repositories/
|   |   |   |   |-- DoctorRepository.java
|   |   |   |   |-- MedicalSpecialityRepository.java
|   |   |   |   |-- PersonRepository.java
|   |   |   |-- services/
|   |   |   |   |-- DoctorService.java
|   |   |   |   |-- MedicalSpecialityService.java
|   |   |   |   |-- PersonService.java
|-- resources/
|   |-- application.properties
|   |-- data.sql
|   |-- schema.sql


Application.java: Main entry point of the Spring Boot application.

DoctorController.java: Controller handling API endpoints related to doctors.

Doctor.java, MedicalSpeciality.java, Person.java: Entity classes representing doctors, medical specialities, and persons.

DoctorRepository.java, MedicalSpecialityRepository.java, PersonRepository.java: Repositories for database operations.

DoctorService.java, MedicalSpecialityService.java, PersonService.java: Service classes to handle business logic.

application.properties: Configuration file for database connection and Hibernate settings.

data.sql, schema.sql: SQL scripts for initializing the database schema and inserting sample data.

2. Entity Classes <a name="entity-classes"></a>

2.1 Doctor
Attributes:
doctorId (Long): Unique identifier for the doctor.

legalCode (String): Legal code of the doctor.

licenseValidTill (LocalDateTime): Date and time until the license is valid.

medicalSpeciality (MedicalSpeciality): Reference to the doctor's medical speciality.

person (Person): Reference to the person associated with the doctor.

2.2 MedicalSpeciality
Attributes:
specialityId (Long): Unique identifier for the medical speciality.
specialityName (String): Name of the medical speciality.

2.3 Person
Attributes:
personId (Long): Unique identifier for the person.

personalCode (String): Personal code following a specific pattern (e.g., gender, birth year, month, day, society identifier).

emailAddress (String): Email address of the person.

name (String): First name of the person.

familyName (String): Last name of the person.

3.1 Database Connection
The application is configured to connect to a MySQL database. The connection details can be specified in the application.properties file

4. API Endpoints <a name="api-endpoints"></a>
The application provides the following API endpoints:

GET /doctors: Retrieve a list of all doctors.
GET /doctors/{doctorId}: Retrieve details of a specific doctor.
POST /doctors: Create a new doctor.
PUT /doctors/{doctorId}: Update details of a specific doctor.

For further details on how to interact with the API or customize the application, refer to the codebase and relevant Spring Boot documentation.

