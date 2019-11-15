package servicios;

import encapsulacion.Usuario;
import org.h2.tools.Server;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;


public class InicioServices {
    public static void iniciarDb() throws SQLException {
        Server.createTcpServer("-tcpPort", "9092", "-tcpAllowOthers").start();
    }

    public static void stopDb() throws SQLException {
        Server.shutdownTcpServer("tcp://localhost:9092", "", true, true);
    }

    public static void crearAdministrador() {
        final UsuarioServices usuarioServices = new UsuarioServices();
        if(usuarioServices.getUsuario("administrador") == null){
            Usuario administrador = new Usuario(
                    "administrador",
                    "administrador",
                    "administrador",
                    true,
                    false
            );
            usuarioServices.crearUsuario(administrador);
        }
    }

    public static void crearTablas() throws SQLException {
        // Connection con = DataBaseServices.getConexion();

        String sql = "CREATE TABLE IF NOT EXISTS USUARIO (" +
                    " username VARCHAR(124) PRIMARY KEY NOT NULL, " +
                    " nombre VARCHAR(100) NOT NULL, " +
                    " password VARCHAR(100) NOT NULL, " +
                    " administrador BOOL NOT NULL," +
                    " autor BOOL NOT NULL," +
                ");";
        sql += "\nCREATE TABLE IF NOT EXISTS ARTICULO\n" +
                "(\n" +
                    " id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                    " titulo VARCHAR(255) NOT NULL,\n" +
                    " cuerpo LONGTEXT NOT NULL,\n" +
                    " fecha DATETIME NOT NULL,\n" +
                    " autor VARCHAR(124) NOT NULL,\n" +
                ");\n";
        sql += "\nCREATE TABLE IF NOT EXISTS etiqueta\n" +
                "(\n" +
                    " id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                    " nombre_etiqueta VARCHAR(256) NOT NULL,\n" +
                    " ARTICULO INTEGER NOT NULL,\n" +
                ");";
        sql += "\nCREATE TABLE IF NOT EXISTS COMENTARIO\n" +
                "(\n" +
                    " id INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,\n" +
                    " comentario VARCHAR(4096) NOT NULL,\n" +
                    " articulo INTEGER NOT NULL,\n" +
                    " autor VARCHAR(124) NOT NULL,\n" +
                ");";
        sql += "\nALTER TABLE comentario ADD FOREIGN KEY (autor) REFERENCES usuario(username);";
        sql += "\nALTER TABLE articulo ADD FOREIGN KEY (autor) REFERENCES usuario(username);";
        sql += "\nALTER TABLE comentario ADD FOREIGN KEY (articulo) REFERENCES articulo(id);";
        sql += "\nALTER TABLE etiqueta ADD FOREIGN KEY (articulo) REFERENCES articulo(id);";

        Connection con = DataBaseServices.getInstance().getConexion();
        Statement stm = con.createStatement();
        stm.execute(sql);

        stm.close();
        con.close();
        crearAdministrador();
    }
}
