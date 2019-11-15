package dao;

import encapsulacion.Articulo;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class ArticuloDao implements Dao<Articulo> {
    private Sql2o sql2o;
    private List<Articulo> misArticulos = new ArrayList<>();
    public ArticuloDao(){
        //subiendola en modo Embedded
        this.sql2o = new Sql2o("jdbc:h2:~/demojdbc", "sa", "");
        createTable();
        //cargaDemo();
    }

    @Override
    public void createTable() {
        String sql="CREATE TABLE IF NOT EXISTS articulo(" +
                "id integer PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "titulo VARCHAR(255) NOT NULL," +
                "cuerpo LONGTEXT NOT NULL, " +
                "autor VARCHAR(124) NOT NULL, " +
                "fecha DATETIME NOT NULL," +
                "etiqueta INTEGER NOT NULL);" +
                "ALTER TABLE articulo\n" +
                "    ADD FOREIGN KEY (autor) \n" +
                "    REFERENCES usuario (username);" +
                "ALTER TABLE articulo\n" +
                "    ADD FOREIGN KEY (etiqueta) \n" +
                "    REFERENCES etiqueta (id);";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }

    @Override
    public List<Articulo> get(String id) {
        String sql="select * from articulo where id = :id;";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", Integer.parseInt(id))
                    .executeAndFetch(Articulo.class);
        }
    }

    @Override
    public List<Articulo> getAll() {
        String sql="select * from articulo;";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Articulo.class);
        }
    }

    @Override
    public void save(Articulo articulo) {
        String insertSql =
                "insert into articulo(titulo, cuerpo, autor, fecha, etiqueta) " +
                        "values (:titulo, :cuerpo, :autor, :fecha, :etiqueta)";

        try (Connection con = sql2o.open()) {
            con.createQuery(insertSql)
                    .addParameter("titulo", articulo.getTitulo())
                    .addParameter("cuerpo", articulo.getCuerpo())
                    .addParameter("autor", articulo.getAutor())
                    .addParameter("fecha", articulo.getFecha())
                    .addParameter("etiqueta", articulo.getEtiquetaId())
                    .executeUpdate();
        }
        //misUsuarios.add(usuario);
    }

    @Override
    public void update(Articulo articulo) {
        String updateSql = "UPDATE articulo SET titulo = :titulo, cuerpo = :cuerpo, autor = :autor, " +
                "fecha = :fecha, etiqueta = :etiqueta where id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("etiqueta", articulo.getEtiquetaId())
                    .addParameter("titulo", articulo.getTitulo())
                    .addParameter("cuerpo", articulo.getCuerpo())
                    .addParameter("autor", articulo.getAutor())
                    .addParameter("fecha", articulo.getFecha())
                    .addParameter("id", articulo.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(Articulo articulo) {
        String updateSql = "DELETE FROM articulo WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("id", articulo.getId())
                    .executeUpdate();
        }
    }
}
