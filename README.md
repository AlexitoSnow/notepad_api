# Notepad: API Rest

## Table of Contents
- [General Info](#[general-info])
- [Features](#features)
- [Execution Instructions](#execution-instructions)
- [Endpoints](#endpoints)

## General Info

Notepad API Rest is a prototype Rest API that connects to a MySQL database to communicate with the [Notepad Web App](https://github.com/AlexitoSnow/notepad_web_app) project designed for practical purposes.

**Programming Language:** Java

**Framework:** Spring Boot

**Database:** MySQL

**Architecture:** REST

**Arquetype:** Maven

**Default Port:** 9090

**Support:** Request/Response

## Features

- CRUD operations to manage notes.
- Connection to a MySQL database.

## Execution Instructions

1. Fork this project.

2. Go to the folder `path-to\notepad_api\src\main\resources`.

3. Configure the database username and password in the `application.yaml` file.

4. Run the `notepad_db.sql` script.

5. Run the project.

6. Test the API with Postman or any other tool using `http://localhost:9090/{endpoint}`.

7. If you want to test the API with the [Notepad Web App](https://github.com/AlexitoSnow/notepad_web_app) project, you must run the API and the Web App in the same machine at the same time.

## Endpoints

| Method | Endpoint | Description |
| ------ | ------ | ------ |
| GET | /files | Get all files |
| GET | /files/titles | Get all titles |
| GET | /file/{title} | Get the content of a file |
| POST | /file/{title} | Create an empty file |
| PUT | /file/{title} | Update the content of a file |
| PUT | /file/rename/{old-title}/{new-title} | Rename a file |
| DELETE | /file/{title} | Delete a file |
| DELETE | /files | Delete all files (Only avaible in localhost) |