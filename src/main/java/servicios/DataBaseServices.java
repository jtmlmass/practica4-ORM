package servicios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseServices {
    private static DataBaseServices instance;
    private static String URL = "jdbc:h2:tcp://localhost/~/jdbctest";

    private DataBaseServices() {
        registrarDriver();
    }

    public static DataBaseServices getInstance() {
        if (instance == null) {
            instance = new DataBaseServices();
        }
        return instance;
    }

    private void registrarDriver() {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException ex) {
            // Logger.getLogger()
        }
    }

    public static Connection getConexion() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(URL, "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void testConexion() {
        try {
            getConexion().close();
            System.out.println("Conexión realizado con exito...");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }
}
