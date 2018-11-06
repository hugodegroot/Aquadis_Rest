package com.aquadis.config;

import javax.ejb.Stateless;

@javax.annotation.sql.DataSourceDefinition(
        name = "java:comp/env/jdbc/aquadisDb",
        className = "com.mysql.cj.jdbc.MysqlXADataSource",
        url = "jdbc:mysql://oege.ie.hva.nl:3306/zgoedhal0031?createDatabaseIfNotExist=true&serverTimezone=CET",
        user = "goedhal0031",
        password = "Cc1$m8FubtIzX5")
@Stateless

// TODO: Make sure that you change the host and credentials to your environment
public class DataSourceDefinition {
}