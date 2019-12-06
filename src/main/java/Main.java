import com.sun.org.apache.xpath.internal.operations.Bool;
import dao.*;

import java.sql.Array;
import java.sql.SQLException;
import java.sql.Date;

import entidades.Articulo;
import org.h2.engine.User;
import org.jasypt.util.text.StrongTextEncryptor;
import entidades.Comentario;
import entidades.Etiqueta;
import entidades.Usuario;
import services.*;
import servicios.ArticuloServices;
import servicios.DataBaseServices;
import servicios.InicioServices;
import servicios.UsuarioServices;
import spark.*;

import java.time.LocalDate;
import java.util.*;
import dao.Dao;
import dao.UsuarioDao;

import freemarker.template.Configuration;
import spark.template.freemarker.FreeMarkerEngine;
import static spark.Spark.*;
import spark.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Main {
    private static String encriptorClave = "aHaf920@_9";

    public static void main(String[] args) throws SQLException {
        //Paso 1
        DataBaseService.getInstance().iniciarDb();
/*Relocate to Function*/
//        InicioServices.iniciarDb();
//        DataBaseServices.getInstance().testConexion();
//        InicioServices.crearTablas();
//        //InicioServices.crearAdministrador();
//
//        ArticuloServices articuloServices = new ArticuloServices();
//        UsuarioServices usuarioServices = new UsuarioServices();
        ArticuloService articuloServices = new ArticuloService();
        UsuarioService usuarioServices = new UsuarioService();
        ComentarioService comentarioServices = new ComentarioService();
        EtiquetaService etiquetaServices = new EtiquetaService();

        //staticFiles.location("/META-INF/resources"); //para utilizar los WebJars.
        staticFiles.location("/publico");
        //staticFiles.location("");
        //Paso 2
        Configuration configuration = new Configuration(Configuration.getVersion());
        configuration.setClassForTemplateLoading(Main.class, "/publico/templates");
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine(configuration);

        //Etiqueta auxEtiqueta = new Etiqueta();
        Usuario adminUser = new Usuario(
                "administrador",
                "administrador",
                "administrador",
                true,
                false
        );
        Usuario chema = new Usuario(
                "chema ",
                "Jose Martinez",
                "chemin",
                true,
                true
        );
        Usuario chemaMod = new Usuario(
                "chema ",
                "Jose Manuel Martinez",
                "chemin",
                true,
                false
        );
        //pruebaUsuario(adminUser, chema, chemaMod);
        //pruebaArticulo(adminUser, chema, chemaMod);

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
            Set<Articulo> articulos = articuloServices.selectDescDate();
            attributes.put("paginas", getCantPaginas(articulos.size()/2));
            attributes.put("articulos", articulos);
            encriptingCookies(request, attributes);
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
                List<Articulo> articulosUsuario = articuloServices.selectByUsuario(usuario);
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
            encriptingCookies(request, attributes);
            return new ModelAndView(attributes, "crearArticulo.ftl");
        }, freeMarkerEngine);

        Spark.get("/editarArticulo/:id", (request, response) -> {
            Session session = request.session(true);
            Map<String, Object> attributes = new HashMap<>();
            long idArticulo = Long.parseLong(request.params("id"));
            ArrayList<Etiqueta> auxList = new ArrayList<>();
            String tags = "";
            Articulo articulo = articuloServices.find(idArticulo);
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
            String titulo = request.queryParams("titulo");
            String cuerpo = request.queryParams("cuerpo");
            StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            textEncryptor.setPassword(encriptorClave);
            Usuario autor = new Usuario();
            if(request.cookie("username") != null){
                autor = new Usuario(
                        textEncryptor.decrypt(request.cookie("username")),
                        textEncryptor.decrypt(request.cookie("nombre")),
                        textEncryptor.decrypt(request.cookie("password")),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isadmin"))),
                        Boolean.parseBoolean(textEncryptor.decrypt(request.cookie("isauthor")))
                );
            }
            Date fecha = new Date(System.currentTimeMillis());
            Articulo articulo = new Articulo(titulo, cuerpo, fecha, autor);
            String etiquetas = request.queryParams("etiquetas");
            String[] inputTags = etiquetas.split(",");
//Filtrar etiqueta
            Set<Etiqueta> auxList = new HashSet<>();
            for (String etiqueta: inputTags) {
                Etiqueta etiquetaExiste = EtiquetaService.getInstance()
                        .findByColumn(Arrays.asList("nombre"), Arrays.asList(etiqueta));
                if (etiquetaExiste != null){
                    auxList.add(etiquetaExiste);
                }else {
                    Etiqueta etiquetaAux = new Etiqueta();
                    etiquetaAux.setNombre(etiqueta);
                    Set<Articulo> articulos = new HashSet<>();
                    articulos.add(articulo);
                    etiquetaAux.setArticulo(articulos);
                    auxList.add(etiquetaAux);
                }
            }

            articulo.setListaEtiquetas(auxList);
            ArticuloService.getInstance().crear(articulo);
            for(Etiqueta etiquetaInstance: articulo.getListaEtiquetas()) {
                Etiqueta etiquetaExistente = EtiquetaService.getInstance().findByColumn(Arrays.asList("nombre"), Arrays.asList(etiquetaInstance.getNombre()));
                if (etiquetaExistente == null) {
                    EtiquetaService.getInstance().crear(etiquetaInstance);
                } else {
                    etiquetaExistente.getArticulo().add(articulo);
                    EtiquetaService.getInstance().editar(etiquetaExistente);
                }
            }
            //misEstudiantes.add(estudiante);
            Map<String, Object> attributes = new HashMap<>();
            attributes.put("titulo", "New Article");
            System.out.println(articulo.getListaEtiquetas().toString());
            attributes.put("articulo", articulo);
            response.redirect("/home");
            return null;
        }, freeMarkerEngine);

        Spark.post("/editarPost/:id", (request, response) -> {
            long idArt = Long.parseLong(request.params("id"));
            System.out.println(idArt);

            Articulo articulo = articuloServices.find(idArt);
            articulo.setCuerpo(request.queryParams("cuerpo"));
            articulo.setTitulo(request.queryParams("titulo"));
            //Enviar a una función
            String etiquetas = request.queryParams("etiquetas");
            String inputTags[] = etiquetas.split(",");
            //filtrarEtiqueta.
            Set<Etiqueta> auxList = new HashSet<>();
            for (String etiqueta: inputTags) {
                Etiqueta etiquetaExistente = EtiquetaService.getInstance()
                        .findByColumn(Arrays.asList("nombre"), Arrays.asList(etiqueta));
                if (etiquetaExistente != null){
                    auxList.add(etiquetaExistente);
                }else{
                    Etiqueta etiquetaAux = new Etiqueta();
                    etiquetaAux.setNombre(etiqueta);
                    Set<Articulo> articulos = new HashSet<>();
                    etiquetaAux.setArticulo(articulos);
                    //etiquetaAux.etiquetarArticulo(articulo);
                    etiquetaServices.crear(etiquetaAux);
                    auxList.add(etiquetaAux);
                }
            }
            for (Etiqueta et: articulo.getListaEtiquetas()){
                boolean included = true;
                for (Etiqueta tag: auxList){
                    if (tag.getNombre() == et.getNombre()){
                        included = false;
                    }
                }if (included == true){
                    et.getArticulo().remove(articulo);
                    EtiquetaService.getInstance().editar(et);
                }
            }
            for (Etiqueta tag: auxList){
                Etiqueta etiquetaExiste = EtiquetaService.getInstance()
                        .findByColumn(Arrays.asList("nombre"), Arrays.asList(tag.getNombre()));
                if (etiquetaExiste == null){

                }else{
                    boolean included = true;
                    for (Etiqueta et: articulo.getListaEtiquetas()) {
                        if(et.getNombre() == tag.getNombre()) {
                            included = false;
                        }
                    }
                    if(included == true) {
                        tag.getArticulo().add(articulo);
                        EtiquetaService.getInstance().editar(tag);
                    }
                }
            }
            articulo.setListaEtiquetas(auxList);
            //**********************************
            ArticuloService.getInstance().editar(articulo);
            response.redirect("/articulo/" + String.valueOf(articulo.getId()));
            return null;
        }, freeMarkerEngine);

        Spark.get("/articulo/:id", (request, response) -> {
            Map<String, Object> attributes = new HashMap<>();
            long idArticulo = Long.parseLong(request.params("id"));
            Articulo articulo = articuloServices.find(idArticulo);
            //System.out.println(articulo);
            for (Comentario comment : articulo.getListaComentarios()){
                System.out.println(comment.getComentario());
            }
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
                Usuario userAux = usuarioServices.find(usuario.getUsername());
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
            Usuario author = usuarioServices.find(username);
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
            List<Usuario> usuarios = usuarioServices.findAll();
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
            usuarioServices.crear(usuario);

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
            long codigo = Long.valueOf(request.params("id"));
            String comentario = request.queryParams("comentario");
            //StrongTextEncryptor textEncryptor = new StrongTextEncryptor();
            //textEncryptor.setPassword(contrasenia);
            Articulo articulo = ArticuloService.getInstance().find(codigo);
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
            //String usuario = textEncryptor.decrypt(request.cookie("usuario"));
            if (session.attribute("usuario") != null || session.attribute("usuario") != "") {
                Comentario comment = new Comentario();
                comment.setComentario(comentario);
                comment.setUsuario(usuario);
                comment.setArticulo(articulo);
                comentarioServices.crear(comment);
                response.redirect("/articulo/" + String.valueOf(codigo));
            }
            response.redirect("/articulo/" + String.valueOf(codigo));
            return null;
        }, freeMarkerEngine);

        Spark.get("/eliminarComentario/:idComentario/:idArticulo", (request, response) -> {
            comentarioServices.eliminar(Long.parseLong(request.params("idComentario")));
            response.redirect("/articulo/" + String.valueOf(request.params("idArticulo")));
            return null;
        }, freeMarkerEngine);
        Spark.get("/eliminarArticulo/:idArticulo", (request, response) -> {
            articuloServices.eliminar(Long.parseLong(request.params("idArticulo")));
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

    private static void encriptingCookies(Request request, Map<String, Object> attributes) {
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
    }

    private static void pruebaUsuario(Usuario adminUser, Usuario chema, Usuario chemaMod) {
        UsuarioService.getInstance().crear(adminUser);
        UsuarioService.getInstance().crear(chema);
        UsuarioService.getInstance().editar(chemaMod);
    }

    private static void pruebaArticulo(Usuario adminUser, Usuario chema, Usuario chemaMod){
        Articulo articulo1 = new Articulo(
                "Cásate con Flask y Python.",
                "¿Qué es Flask? Flask es un microframework web ligero creado con el lenguaje de programación Python, esta diseñado para iniciar un proyecto rápido y fácil, tiene la facilidad de pasar de un proyecto simple a uno complejo.",
                Date.valueOf(LocalDate.now()),
                chema
        );
        /*

        */
        Articulo articulo2 = new Articulo("Cásate con Flask y Python.",
                "¿Qué es Flask? Flask es un microframework web ligero creado con el lenguaje de programación Python, esta diseñado para iniciar un proyecto rápido y fácil, tiene la facilidad de pasar de un proyecto simple a uno complejo.",
                Date.valueOf(LocalDate.now()),
                chema);

        Articulo articulo2Mod = new Articulo("Java, Spring Boot y Vaadin 14",
                "Con la llegada de más Frameworks orientados a componentes, el desarrollo en java para aplicaciones mas modernas es más intenso y esto a hecho evolucionar a Vaadin a un punto donde el diseñador, el desarrollador BackEnd y el hecho de usar componentes web ya es una realidad en java con una integración muy sencilla usando Vaadin 14.",
                Date.valueOf(LocalDate.now()),
                adminUser);
        ArticuloService.getInstance().crear(articulo1);
        ArticuloService.getInstance().crear(articulo2);
    }

    private static void createEntities(){
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("MiUnidadPersistencia");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();

        Usuario adminUser = new Usuario(
                "administrador",
                "administrador",
                "administrador",
                true,
                false
        );
        UsuarioService.getInstance().crear(adminUser);
    }

    static private Set<Integer> getCantPaginas(int tam) {
        Set<Integer> cantArticulos = new HashSet<>();
        for (int i = 1; i <= Math.ceil(tam) + 1; i++) {
            cantArticulos.add(i);
        }
        return cantArticulos;
    }

}

