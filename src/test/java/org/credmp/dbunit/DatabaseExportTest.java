package org.credmp.dbunit;

import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.junit.Test;

public class DatabaseExportTest {
    //@Test
    public void exportAllData() {
        try {

            // database connection
            Class driverClass = Class.forName("org.postgresql.Driver");
            Connection jdbcConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/fromnothing_test", "yourusername", "yourpassword");
            IDatabaseConnection connection = new DatabaseConnection(jdbcConnection);

            // // partial database export
            // QueryDataSet partialDataSet = new QueryDataSet(connection);
            // partialDataSet.addTable("BAR");
            // FlatXmlDataSet.write(partialDataSet, new FileOutputStream("partial.xml"));

            // full database export
            IDataSet fullDataSet = connection.createDataSet();
            FlatXmlDataSet.write(fullDataSet, new FileOutputStream("full.xml"));

            // // dependent tables database export: export table X and all tables that
            // // have a PK which is a FK on X, in the right order for insertion
            // String[] depTableNames =
            //   TablesDependencyHelper.getAllDependentTables( connection, "X" );
            // IDataSet depDataSet = connection.createDataSet( depTableNames );
            // FlatXmlDataSet.write(depDataSet, new FileOutputStream("dependents.xml"));
        } catch (Exception ex) {
            System.out.println("Oeps: " + ex);
        }
    }
}
