import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// javac Sample.java & java -classpath ".;sqlite-jdbc-3.46.0.0.jar;slf4j-api-2.0.13.jar;slf4j-nop-2.0.7.jar" Sample
public class App {
    public static void main(String[] args) {
        System.out.println("Hello world");



    try (
      // create a database connection
      Connection connection = DriverManager.getConnection("jdbc:sqlite:sample.db");
      Statement statement = connection.createStatement();
    ) {
      statement.setQueryTimeout(30);  // set timeout to 30 sec.

      statement.executeUpdate("drop table if exists person");
      statement.executeUpdate("create table person (id integer, name string)");
      statement.executeUpdate("insert into person values(1, 'leo')");
      statement.executeUpdate("insert into person values(2, 'yui')");
      statement.executeUpdate("insert into person values(3, 'Claude')");
      ResultSet rs = statement.executeQuery("select * from person");
      while(rs.next())
      {
        // read the result set
        System.out.println("name = " + rs.getString("name"));
        System.out.println("id = " + rs.getInt("id"));
      }
    }
    catch(SQLException e)
    {
      // if the error message is "out of memory",
      // it probably means no database file is found
      e.printStackTrace(System.err);
    }
    }
}