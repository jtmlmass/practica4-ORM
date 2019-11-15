import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.*;
import encapsulacion.Articulo;
import encapsulacion.Comentario;
import encapsulacion.Etiqueta;
import encapsulacion.Usuario;

import java.sql.Array;
import java.sql.SQLException;
import java.sql.Date;
import encapsulacion.Usuario;
import org.h2.engine.User;
import org.jasypt.util.text.StrongTextEncryptor;
import servicios.ArticuloServices;
import servicios.DataBaseServices;
import servicios.InicioServices;
import servicios.UsuarioServices;
import spark.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import dao.Dao;
import dao.UsuarioDao;

import java.util.List;
import java.util.Optional;

import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.*;
import spark.Session;

public class Main {
    private static String encriptorClave = "aHaf920@_9";

    public static void main(String[] args) throws SQLException {
        InicioServices.iniciarDb();
        DataBaseServices.getInstance().testConexion();
        InicioServices.crearTablas();
        //InicioServices.crearAdministrador();

        ArticuloServices articuloServices = new ArticuloServices();
        UsuarioServices usuarioServices = new UsuarioServices();


        //staticFiles.location("/META-INF/resources"); //para utilizar los WebJars.
        staticFiles.location("/publico");
        //staticFiles.location("");

        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(Main.class, "/publico/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        Etiqueta auxEtiqueta = new Etiqueta();


        before("*", (request, response) -> {
            Session session = request.session(true);
            if(session.attribute("usuario") == null)
                session.attribute("usuario", "");
        });

        Spark.get("/login", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            return new ModelAndView(attributes, "login.ftl");
        }, freeMarkerEngine);

        Spark.get("/register", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            return new ModelAndView(attributes, "register.ftl");
        }, freeMarkerEngine);

        Spark.get("/home", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Home");
            List<Articulo> articulos = articuloServices.selectArticulos();
            attributes.put("articulos", articulos);
            attributes.put("editable", "no");
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(encriptorClave);
            Usuario usuario;
            if(request.cookie("username") != null){
                usuario = new Usuario(
                        textEncryptor.decrypt(request.cookie("username")),
                        textEncryptor.decrypt(request.cookie("nombre")),
                        textEncryptor.decrypt(request.cookie("password")),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isadmin"))),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isauthor")))
                );
                attributes.put("usuario", usuario);
            }else{
                attributes.put("usuario", "");
            }

            return new ModelAndView(attributes, "home.ftl");
        }, freeMarkerEngine);

        Spark.get("/misPosts", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(encriptorClave);
            Usuario usuario = null;
            if(request.cookie("username") != null){
                usuario = new Usuario(
                        textEncryptor.decrypt(request.cookie("username")),
                        textEncryptor.decrypt(request.cookie("nombre")),
                        textEncryptor.decrypt(request.cookie("password")),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isadmin"))),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isauthor")))
                );
                attributes.put("usuario", usuario);
                List<Articulo> articulosUsuario = articuloServices.selectMisArticulos(usuario.getUsername());
                attributes.put("articulos", articulosUsuario);

            }else{
                attributes.put("usuario", "");
            }

            if(usuario == null || attributes.get("usuario") == ""){
                response.redirect("/login");
            }
            attributes.put("titulo", "My Posts");
            attributes.put("editable", "si");
            return new ModelAndView(attributes, "home.ftl");
        }, freeMarkerEngine);

        Spark.get("/articulo", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(encriptorClave);
            Usuario usuario;
            if(request.cookie("username") != null){
                usuario = new Usuario(
                        textEncryptor.decrypt(request.cookie("username")),
                        textEncryptor.decrypt(request.cookie("nombre")),
                        textEncryptor.decrypt(request.cookie("password")),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isadmin"))),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isauthor")))
                );
                attributes.put("usuario", usuario.getUsername());
            }else{
                attributes.put("usuario", "");
            }
            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);

        Spark.get("/crearArticulo", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            attributes.put("editable", "no");
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(encriptorClave);
            Usuario usuario;
            if(request.cookie("username") != null){
                usuario = new Usuario(
                        textEncryptor.decrypt(request.cookie("username")),
                        textEncryptor.decrypt(request.cookie("nombre")),
                        textEncryptor.decrypt(request.cookie("password")),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isadmin"))),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isauthor")))
                );
                attributes.put("usuario", usuario);
            }else{
                attributes.put("usuario", "");
            }
            return new ModelAndView(attributes, "crearArticulo.ftl");
        }, freeMarkerEngine);

        Spark.get("/editarArticulo/:id", (request, response) -> {
            Session session = request.session(true);
            Map<String, Object> attributes = new HashMap<>();
            int idArticulo = Integer.parseInt(request.params("id"));
            ArrayList<Etiqueta> auxList = new ArrayList<>();
            String tags = "";
            Articulo articulo = articuloServices.getArticulo(idArticulo);
            ArrayList<Etiqueta> misEtiquetas;
            misEtiquetas = articuloServices.getAllEtiquetas();
            for (Etiqueta tag : misEtiquetas){
                if (tag.getArticulo() == idArticulo){
                    auxList.add(tag);
                }
            }
            int idx = 1;
            for (Etiqueta tag : auxList){
                if (idx < auxList.size()){
                    tags += tag + ", ";
                    //auxList.add(tag);
                }else{
                    tags += tag;
                }
                idx++;
            }
            //articulo.setListaEtiquetas(auxList);
            if(session.attribute("usuario") == null || session.attribute("usuario") == ""){
                response.redirect("/login");
            }
            attributes.put("titulo", "Edit Post");
            attributes.put("editable", "si");
            attributes.put("articulo", articulo);
            attributes.put("etiquetas", tags);
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(encriptorClave);
            Usuario usuario;
            if(request.cookie("username") != null){
                usuario = new Usuario(
                        textEncryptor.decrypt(request.cookie("username")),
                        textEncryptor.decrypt(request.cookie("nombre")),
                        textEncryptor.decrypt(request.cookie("password")),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isadmin"))),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isauthor")))
                );
                attributes.put("usuario", usuario);
            }else{
                attributes.put("usuario", "");
            }
            return new ModelAndView(attributes, "crearArticulo.ftl");
        }, freeMarkerEngine);

        Spark.post("/postArticulo/", (request, response) -> {
            Session session = request.session(true);
            String titulo = request.queryParams("titulo");
            String cuerpo = request.queryParams("cuerpo");
            String autor = session.attribute("usuario").toString();
            Date fecha = new Date(System.currentTimeMillis());
            Articulo articulo = new Articulo(titulo, cuerpo, autor, fecha);
            int idArt = articuloServices.insertArticulo(articulo);
            String etiquetas = request.queryParams("etiquetas");
            String inputTags[] = etiquetas.split(",");
            ArrayList<Etiqueta> auxList = new ArrayList<>();
            for (String etiqueta: inputTags) {
                Etiqueta etiquetaAux = new Etiqueta();
                etiquetaAux.setEtiqueta(etiqueta);
                etiquetaAux.setArticulo(idArt);
                articuloServices.crearEtiqueta(etiquetaAux);
                auxList.add(etiquetaAux);
            }
            articulo.setListaEtiquetas(auxList);
            //misEstudiantes.add(estudiante);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "New Article");
            System.out.println(articulo.getListaEtiquetas().toString());
            attributes.put("articulo", articulo);
            response.redirect("/home");
            return null;
        }, freeMarkerEngine);

        Spark.post("/editarPost/:id", (request, response) -> {
            int idArt = Integer.parseInt(request.params("id"));
            System.out.println(idArt);

            Articulo articulo = articuloServices.getArticulo(idArt);
            articulo.setCuerpo(request.queryParams("cuerpo"));
            articulo.setTitulo(request.queryParams("titulo"));
            //Enviar a una función:
            String etiquetas = request.queryParams("etiquetas");
            String inputTags[] = etiquetas.split(",");
            ArrayList<Etiqueta> auxList = new ArrayList<>();
            for (String etiqueta: inputTags) {
                Etiqueta etiquetaAux = new Etiqueta();
                etiquetaAux.setEtiqueta(etiqueta);
                etiquetaAux.setArticulo(idArt);
                articuloServices.crearEtiqueta(etiquetaAux);
                auxList.add(etiquetaAux);
            }
            articulo.setListaEtiquetas(auxList);
            //**********************************
            articuloServices.updateArticulo(articulo);
            response.redirect("/articulo/" + String.valueOf(articulo.getId()));
            return null;
        }, freeMarkerEngine);

        Spark.get("/articulo/:id", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            int idArticulo = Integer.parseInt(request.params("id"));
            Articulo articulo = articuloServices.getArticulo(idArticulo);
            System.out.println(articulo);
//            ArrayList<Etiqueta> auxList = new ArrayList<>();
//            ArrayList<Etiqueta> misEtiquetas;
//            misEtiquetas = articuloServices.getAllEtiquetas();
//            for (Etiqueta tag : misEtiquetas){
//                if (tag.getArticulo() == idArticulo){
//                    auxList.add(tag);
//                }
//            }
//            articulo.setListaEtiquetas(auxList);
            attributes.put("articulo", articulo);
            System.out.println(articulo.getListaEtiquetas().toString());
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(encriptorClave);
            Usuario usuario;
            if(request.cookie("username") != null){
                usuario = new Usuario(
                        textEncryptor.decrypt(request.cookie("username")),
                        textEncryptor.decrypt(request.cookie("nombre")),
                        textEncryptor.decrypt(request.cookie("password")),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isadmin"))),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isauthor")))
                );
                attributes.put("usuario", usuario);
                Usuario userAux = usuarioServices.getUsuario(usuario.getUsername());
                if (userAux != null){
                    attributes.put("isAdmin", userAux.isAdministrator());
                }
            }else{
                attributes.put("usuario", "");
            }
            System.out.println(articulo.getListaEtiquetas().toString());
            return new ModelAndView(attributes, "post.ftl");
        }, freeMarkerEngine);
        //Obtener al usuario específico.
        Spark.get("/author/:username", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            String username = request.params("username");
            Usuario author = usuarioServices.getUsuario(username);
            attributes.put("author", author);
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(encriptorClave);
            Usuario usuario;
            if(request.cookie("username") != null){
                usuario = new Usuario(
                        textEncryptor.decrypt(request.cookie("username")),
                        textEncryptor.decrypt(request.cookie("nombre")),
                        textEncryptor.decrypt(request.cookie("password")),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isadmin"))),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isauthor")))
                );
                attributes.put("usuario", usuario);
            }else{
                attributes.put("usuario", "");
            }
            return new ModelAndView(attributes, "author.ftl");
        }, freeMarkerEngine);

    //Mostrar el usuario que inició la sesión
        Spark.get("/author", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "Login");
            Session session = request.session(true);
            if(session.attribute("usuario") == null || session.attribute("usuario") == ""){
                response.redirect("/login");
            }
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(encriptorClave);
            Usuario usuario;
            if(request.cookie("username") != null){
                usuario = new Usuario(
                        textEncryptor.decrypt(request.cookie("username")),
                        textEncryptor.decrypt(request.cookie("nombre")),
                        textEncryptor.decrypt(request.cookie("password")),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isadmin"))),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isauthor")))
                );
                attributes.put("usuario", usuario);
            }else{
                attributes.put("usuario", "");
            }
            return new ModelAndView(attributes, "author.ftl");
        }, freeMarkerEngine);

        Spark.post("/hacerLogin/", (request, response) -> {
            Session session = request.session(true);
            List<Usuario> usuarios = usuarioServices.getAllUsuarios();
            Usuario usuario = null;
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            for (Usuario u : usuarios){
                System.out.println(u.toString());
                if (username.equalsIgnoreCase(u.getUsername()) && password.equals(u.getPassword())) {
                    usuario = new Usuario(u.getUsername(), u.getNombre(), u.getPassword(), u.isAdministrator(), u.isAuthor());
                    int recordar = (request.queryParams("recordar") != null?86400:1000);
                    StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
                    textEncryptor.setPassword(encriptorClave);
                    String encriptedUsername = textEncryptor.encrypt(usuario.getUsername());
                    String encriptedPassword = textEncryptor.encrypt(usuario.getPassword());
                    String encriptedName = textEncryptor.encrypt(usuario.getNombre());
                    String encriptedIsAdmin = textEncryptor.encrypt(String.valueOf(usuario.isAdministrator()));
                    String encriptedIsAuthor = textEncryptor.encrypt(String.valueOf(usuario.isAuthor()));
                    response.cookie("/", "username", encriptedUsername, recordar, false);
                    response.cookie("/", "password", encriptedPassword, recordar, false);
                    response.cookie("/", "nombre", encriptedName, recordar, false);
                    response.cookie("/", "isadmin", encriptedIsAdmin, recordar, false);
                    response.cookie("/", "isauthor", encriptedIsAuthor, recordar, false);
                    response.redirect("/home");
                }
            }

            session.attribute("usuario", usuario);
            //redireccionado a la otra URL.
            response.redirect("/login");
            return "";
        });

        Spark.post("/hacerRegister/", (request, response) -> {
            String nombre = request.queryParams("name");
            String username = request.queryParams("username");
            String password = request.queryParams("password");
            boolean isadmin = false;
            boolean isauthor = false;
            String auxIsAdmin = request.queryParams("isadmin");
            String auxIsAuthor = request.queryParams("isauthor");
            if(auxIsAdmin != null && auxIsAdmin.equals("on")){
                isadmin = true;
            }
            if(auxIsAuthor != null && auxIsAuthor.equals("on")){
                isauthor = true;
            }
            System.out.println(request.queryParams("isauthor"));
            Usuario usuario = new Usuario(username, nombre, password, isadmin, isauthor);
            usuarioServices.crearUsuario(usuario);

            //redireccionado a la otra URL.
            response.redirect("/login");

            return "";
        });

        Spark.get("/hacerLogout", (request, response) -> {
            //creando cookie en para un minuto
            Session session = request.session();
            session.invalidate();
            response.removeCookie("/", "username");
            response.removeCookie("/", "nombre");
            response.removeCookie("/", "password");
            response.removeCookie("/", "isadmin");
            response.removeCookie("/", "isauthor");
            response.redirect("/home");
            return "";
        });

        Spark.get("/", (request, response) -> {
            response.redirect("/home");
            return "";
        });

        //Filtros
        before("*", (request, response) -> {
            Session session = request.session(true);
            if(session.attribute("usuario") == null)
                session.attribute("usuario", "");
        });

        Spark.post("/comentar/:id", (request, response) -> {
            int codigo = Integer.valueOf(request.params("id"));
            String comentario = request.queryParams("comentario");
            //StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            //textEncryptor.setPassword(contrasenia);

            Session session = request.session(true);
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(encriptorClave);
            Usuario usuario = new Usuario(
                    textEncryptor.decrypt(request.cookie("username")),
                    textEncryptor.decrypt(request.cookie("nombre")),
                    textEncryptor.decrypt(request.cookie("password")),
                    Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isadmin"))),
                    Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isauthor")))
            );
            String username = usuario.getUsername();
            //String usuario = textEncryptor.decrypt(request.cookie("usuario"));
            if (session.attribute("usuario") != null || session.attribute("usuario") != "") {
                Comentario coment = new Comentario();
                coment.setComentario(comentario);
                coment.setAutor(username);
                articuloServices.crearComentario(coment, codigo);
                response.redirect("/articulo/" + String.valueOf(codigo));
            }
            response.redirect("/articulo/" + String.valueOf(codigo));
            return null;
        }, freeMarkerEngine);
        Spark.get("/eliminarComentario/:idComentario/:idArticulo", (request, response) -> {
            articuloServices.borrarComentario(Integer.parseInt(request.params("idComentario")));
            response.redirect("/articulo/" + String.valueOf(request.params("idArticulo")));
            return null;
        }, freeMarkerEngine);
        Spark.get("/eliminarArticulo/:idArticulo", (request, response) -> {
            articuloServices.borrarArticulo(Integer.parseInt(request.params("idArticulo")));
            response.redirect("/home");
            return null;
        }, freeMarkerEngine);
//        before("/crearArticulo",(request, response) -> {
//            Usuario usuario = request.session().attribute("usuario");
//            if(usuario==null){
//                response.redirect("/login");
//            }
//        });
    }
}

