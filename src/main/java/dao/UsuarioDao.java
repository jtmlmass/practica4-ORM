package dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import encapsulacion.Usuario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

public class UsuarioDao implements Dao<Usuario> {
    private Sql2o sql2o;
    private List<Usuario> misUsuarios = new ArrayList<>();
    public UsuarioDao(){
        //subiendola en modo Embedded
        this.sql2o = new Sql2o("jdbc:h2:~/demojdbc", "sa", "");
        createTable();
        cargaDemo();
    }
    private void crearTabla(){

    }

    @Override
    public void createTable() {
        String sql="CREATE TABLE IF NOT EXISTS usuario(username VARCHAR(124) PRIMARY KEY NOT NULL, nombre VARCHAR(100)," +
                "password VARCHAR(100) NOT NULL, administrator BOOL NOT NULL, author BOOL NOT NULL);";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }

    @Override
    public List<Usuario> get(String username) {
        String sql="select * from usuario where username = :username;";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("username", username)
                    .executeAndFetch(Usuario.class);
        }
    }

    @Override
    public List<Usuario> getAll() {
        String sql="select * from usuario";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Usuario.class);
        }
    }

    @Override
    public void save(Usuario usuario) {
        String insertSql =
                "insert into usuario(username, nombre, password, administrator, author) " +
                        "values (:username, :nombre, :password, :administrator, :author)";

        try (Connection con = sql2o.open()) {
            con.createQuery(insertSql)
                    .addParameter("username", usuario.getUsername())
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("password", usuario.getPassword())
                    .addParameter("administrator", usuario.isAdministrator())
                    .addParameter("author", usuario.isAuthor())
                    .executeUpdate();
        }
        misUsuarios.add(usuario);
    }
    /*Buscar en el enrutamiento el usuario que coincida con el username en la db y agarrar lo que mande el UI del
    formulario, el formulario debe de autocompletarse con la informacion vieja para que esto funcione.
     */
    @Override
    public void update(Usuario usuario) {
        String updateSql = "UPDATE usuario SET nombre = :nombre, password = :pass, administrator = :admin, " +
                "author = :author where username = :username";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                .addParameter("nombre", usuario.getNombre())
                .addParameter("pass", usuario.getPassword())
                .addParameter("admin", usuario.isAdministrator())
                .addParameter("author", usuario.isAuthor())
                .addParameter("username", usuario.getUsername())
                .executeUpdate();
        }
    }

    @Override
    public void delete(Usuario usuario) {
        String updateSql = "DELETE FROM usuario WHERE username = :username";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("username", usuario.getUsername())
                    .executeUpdate();
        }
    }

        private void cargaDemo(){
            System.out.println("Cargando el demo...");
            if(getAll().isEmpty()){
                crearDataDemo();
            }
        }

    public void crearDataDemo(){
        String sql = "insert into usuario(username, nombre, password, administrator, author) values(:username,:nombre, " +
                ":password, :administrator, :author)";
        try (Connection con = sql2o.open()) {
                con.createQuery(sql)
                    .addParameter("username", "jtmlmass")
                    .addParameter("nombre", "Jose ")
                    .addParameter("password", "asdfgh55555jkl")
                    .addParameter("administrator", false)
                    .addParameter("author", false)
                    .executeUpdate();
        }
    }
}
/*
*     /**
 *
 */





