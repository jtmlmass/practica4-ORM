package servicios;

import encapsulacion.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UsuarioServices {
    public Usuario getUsuario(String username) {
        Usuario usuario = null;
        Connection connection = null;

        try {
            String sql = "SELECT * FROM usuario WHERE username = ?";
            connection = DataBaseServices.getInstance().getConexion();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                usuario = new Usuario();
                usuario.setAdministrator(rs.getBoolean("administrador"));
                usuario.setAuthor(rs.getBoolean("autor"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUsername(rs.getString("username"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return usuario;
    }

    public ArrayList<Usuario> getAllUsuarios() {
        ArrayList<Usuario> misUsuarios = new ArrayList<>();
        Connection connection = null;

        try {
            String sql = "SELECT * FROM usuario";
            connection = DataBaseServices.getInstance().getConexion();

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario = new Usuario();
                usuario.setAdministrator(rs.getBoolean("administrador"));
                usuario.setAuthor(rs.getBoolean("autor"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setPassword(rs.getString("password"));
                usuario.setUsername(rs.getString("username"));
                misUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return misUsuarios;
    }


    public boolean crearUsuario(Usuario usuario){
        boolean ok =false;

        Connection connection = null;
        try {

            String query = "INSERT INTO usuario(username, nombre, password, administrador, autor) values(?,?,?,?,?)";
            connection = DataBaseServices.getInstance().getConexion();
            //
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, usuario.getUsername());
            prepareStatement.setString(2, usuario.getNombre());
            prepareStatement.setString(3, usuario.getPassword());
            prepareStatement.setBoolean(4, usuario.isAdministrator());
            prepareStatement.setBoolean(5, usuario.isAuthor());
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return ok;
    }

    public boolean actualizarUsuario(Usuario usuario){
        boolean ok =false;

        Connection connection = null;
        try {

            String query = "UPDATE usuario SET nombre=?, password=?, administrador=?, autor=? WHERE username = ?";
            connection = DataBaseServices.getInstance().getConexion();
            //
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            //Antes de ejecutar seteo los parametros.
            prepareStatement.setString(1, usuario.getNombre());
            prepareStatement.setString(2, usuario.getPassword());
            prepareStatement.setBoolean(3, usuario.isAdministrator());
            prepareStatement.setBoolean(4, usuario.isAuthor());
            prepareStatement.setString(5, usuario.getUsername());

            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }

        return ok;
    }

    public boolean borrarUsuario(String nombreDeUsuario){
        boolean ok =false;

        Connection connection = null;
        try {

            String query = "DELETE FROM usuario WHERE username = ?";
            connection = DataBaseServices.getInstance().getConexion();
            //
            PreparedStatement prepareStatement = connection.prepareStatement(query);
            //Indica el where...
            prepareStatement.setString(1, nombreDeUsuario);
            //
            int fila = prepareStatement.executeUpdate();
            ok = fila > 0 ;

        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally{
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return ok;
    }
}
