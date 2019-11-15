package services;
import org.h2.tools.Server;
import java.sql.SQLException;

public class DataBaseService {
    private static DataBaseService instance;

    public static DataBaseService getInstance() {
        if (instance == null) {
            instance = new DataBaseService();
        }
        return instance;
    }

    public void iniciarDb() {
        try {
            Server.createTcpServer("-tcpPort","9092","-tcpAllowOthers","-tcpDaemon").start();

        } catch (SQLException ex) {
            System.out.println("Database error: "+ex.getMessage());
        }
    }
    public void init(){
        iniciarDb();
    }
}
