# Building a PDF File Indexing Project with Struts2, Apache Tika, Hibernate, and Wildfly 10 📄🔍

This project aims to demonstrate the construction of a web application for indexing PDF files using Struts2, Apache Tika, Hibernate, and Wildfly 10.

## Overview ℹ️

This web application provides functionalities for indexing PDF files, including:

- Uploading PDF files for indexing
- Extracting text content from PDF files using Apache Tika
- Storing metadata and extracted text content in a database using Hibernate
- Searching indexed PDF files

The project utilizes the following technologies:

- Struts2: A web application framework for developing Java EE web applications.
- Apache Tika: A content analysis toolkit capable of extracting text and metadata from various document formats, including PDF.
- Hibernate: An object-relational mapping (ORM) library for the Java language.
- Wildfly 10: A lightweight, flexible, and modular application server for Java EE applications.

## Features 🚀

- **Struts2 Actions**: Actions for handling user requests and interacting with the backend services.
- **Apache Tika Integration**: Integration of Apache Tika for extracting text content and metadata from PDF files.
- **Hibernate Entities**: Entities representing the data model for storing PDF file metadata and text content.
- **User Interface**: Basic user interface for uploading PDF files and searching indexed PDF files.

## Setting Up a JDBC Realm on Wildfly 10 🔒

To enhance security and manage authentication and authorization, you can set up a JDBC Realm on Wildfly 10. Follow the steps outlined below:

1. **Create Security Domain:**
   
   We create a security domain named `booksRealm`, configuring it to use a default cache type for enhanced performance.

2. **Configure Authentication:**
   
   Within the `booksRealm` security domain, we configure the authentication method as `classic`, indicating traditional login-based authentication. Two login modules are defined:

   - `Database`: This module handles authentication by querying a database. It requires specifying the JNDI name of the datasource (`java:/jdbc/booksDS`), along with SQL queries to retrieve user credentials and roles. Password hashing is performed using the MD5 algorithm with BASE64 encoding.

   - `RoleMapping`: This module maps roles retrieved from the database to application roles defined in a properties file. The path to the properties file containing role mappings (`booksRealm.properties`) is provided, along with an option to replace existing roles.

## Contributing 🤝

Contributions to this project are welcome! If you have ideas for improvements, new features, or bug fixes, feel free to open an issue or submit a pull request.

## License 📝

This project is licensed under the [MIT License](LICENSE).
