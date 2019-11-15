package dao;

import encapsulacion.Articulo;
import encapsulacion.Comentario;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.List;

public class ComentarioDao implements  Dao<Comentario> {
    private Sql2o sql2o;
    private List<Comentario> misComentarios = new ArrayList<>();
    public ComentarioDao() {
        //subiendola en modo Embedded
        this.sql2o = new Sql2o("jdbc:h2:~/demojdbc", "sa", "");
        createTable();
        //cargaDemo();
    }

    @Override
    public void createTable() {
        String sql="CREATE TABLE IF NOT EXISTS comentario(" +
                "id integer PRIMARY KEY AUTO_INCREMENT NOT NULL, " +
                "comentario VARCHAR(1000) NOT NULL," +
                "articulo INTEGER NOT NULL, " +
                "autor varchar(124) NOT NULL,);" +
                "ALTER TABLE comentario\n" +
                "    ADD FOREIGN KEY (autor) \n" +
                "    REFERENCES usuario (username);" +
                "ALTER TABLE comentario\n" +
                "    ADD FOREIGN KEY (articulo) \n" +
                "    REFERENCES articulo (id);";
        try(Connection con = sql2o.open()){
            con.createQuery(sql).executeUpdate();
        }
    }

    @Override
    public List<Comentario> get(String id) {
        String sql="select * from comentario where id = :id;";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql)
                    .addParameter("id", Integer.parseInt(id))
                    .executeAndFetch(Comentario.class);
        }
    }

    @Override
    public List<Comentario> getAll() {
        String sql="select * from comentario";
        try(Connection con = sql2o.open()){
            return con.createQuery(sql).executeAndFetch(Comentario.class);
        }
    }

    @Override
    public void save(Comentario comentario) {
        String insertSql =
                "insert into comentario(comentario, articulo, autor) " +
                        "values (:comentario, :articulo, :autor)";

        try (Connection con = sql2o.open()) {
            con.createQuery(insertSql)
                    .addParameter("comentario", comentario.getComentario())
                    .addParameter("articulo", comentario.getArticulo())
                    .addParameter("autor", comentario.getAutor())
                    .executeUpdate();
        }
    }

    @Override
    public void update(Comentario comentario) {
        String updateSql = "UPDATE comentario SET comentario = :comentario, " +
                "articulo = :articulo, " +
                "autor = :autor " +
                "where id = :id;";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("comentario", comentario.getComentario())
                    .addParameter("articulo", comentario.getArticulo())
                    .addParameter("autor", comentario.getAutor())
                    .addParameter("id", comentario.getId())
                    .executeUpdate();
        }
    }

    @Override
    public void delete(Comentario comentario) {
        String updateSql = "DELETE FROM comentario WHERE id = :id";
        try (Connection con = sql2o.open()) {
            con.createQuery(updateSql)
                    .addParameter("id", comentario.getId())
                    .executeUpdate();
        }
    }
}
