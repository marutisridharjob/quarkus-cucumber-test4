# Quarkus-Cucumber-BDD

This is a sample Quarkus project demonstrating how to integrate a mongo db docker container with Cucumber for acceptance
testing.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Getting Started](#getting-started)

## Introduction

This project is built with Quarkus, which is a popular Java framework for building web applications. It showcases how to
incorporate a mongo db into a Quarkus application and how to write and run acceptance tests using Cucumber.

## Features

- Quarkus framework for building web applications
- Integration with mongo db
- Test Container to deploy mongo db docker container
- Cucumber for acceptance testing
- RESTful API endpoints

## Prerequisites

Before you begin, ensure you have met the following requirements:

- Java Development Kit (JDK) installed
- An IDE of your choice (e.g., IntelliJ IDEA, Eclipse)
- Docker installed
- Git installed (for cloning the repository)

## Getting Started

To get started with this project, follow these steps:

1. Clone the repository:

```bash
git clone https://github.com/tbounsiar/quarkus-cucumber-bdd.git
```

2. Navigate to the project directory:

```bash
cd quarkus-cucumber-bdd
```

3. Run tests

```bash
./mvnw clean test
```
