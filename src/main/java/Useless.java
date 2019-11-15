import encapsulacion.Articulo;
import encapsulacion.Etiqueta;
import encapsulacion.Usuario;
import servicios.ArticuloServices;
import servicios.UsuarioServices;

import java.sql.Date;

public class Useless {
//    /*
//     * PRUEBAS DE USUARIO
//     * */
//    Usuario chema = new Usuario("chema", "Jose Martinez", "chema03", true, true);
//    Usuario jtmlmass = new Usuario("jtmlmass", "Jose Molina", "molinator", true, true);
//    UsuarioServices usuarioServices = new UsuarioServices();
//    ArticuloServices articuloServices = new ArticuloServices();
//        usuarioServices.crearUsuario(chema);
//        usuarioServices.crearUsuario(jtmlmass);
//        usuarioServices.actualizarUsuario(new Usuario("chema", "Jose Martinez", "chemin", true, false));
//        System.out.println(usuarioServices.getUsuario("chema").toString());
//        usuarioServices.borrarUsuario("jtmlmass");
//        System.out.println(usuarioServices.getUsuario("jtmlmass").toString());
//    /*
//     * PRUEBAS DE ARTICULOS Y DERIVADOS
//     * */
//    Etiqueta tag = new Etiqueta("CompSci");
//    Etiqueta tag2 = new Etiqueta("Software Development");
//        articuloServices.crearEtiqueta(tag);
//        articuloServices.crearEtiqueta(tag2);
//    Articulo art1 = new Articulo("Simple Mobile Development with Flutter", "As simple as this not cost effective and the learning curve is awful.",
//            "chema",  new Date(System.currentTimeMillis()), 1);
//        articuloServices.insertArticulo(art1);
//    Articulo art2 = new Articulo("Is GoLang de 21st Century Language?", "Go. Simple sintax and very powerfu, but....",
//            "chema", new Date(System.currentTimeMillis()), 2);
//        System.out.println(articuloServices.getArticulo(1).toString());
//        System.out.println(articuloServices.getArticulo(2).toString());
//        articuloServices.updateArticulo(new Articulo("Go Lang is The Future!", "Go. Simple sintax and very powerfu, but....",
//                                                             "chema",  new Date(System.currentTimeMillis()), 2));
//        articuloServices.borrarArticulo(1);
//        System.out.println(articuloServices.getArticulo(1).toString());
//    Usuario admin = new Usuario("admin", "Jose", "admin", true, true);

    //        userDao = new UsuarioDao();
//        System.out.println("Prueba Dao");
//        UsuarioDao usuarioDao = new UsuarioDao();
//        List<Usuario> allEstudiante = usuarioDao.getAll();
//        for(Usuario estudiante : allEstudiante){
//            System.out.printf("Username: %s - Nombre: %s\n",
//                    estudiante.getUsername(), estudiante.getNombre());

//        usuarioDao.save(new Usuario("jtmlmass", "Tomas", "ljf4656d", false, true));
//        System.out.println(usuarioDao.getAll());
//        Usuario user= usuarioDao.get("chema").get(0);
//        System.out.println(user);

    //Update works
//        userDao.update(new Usuario(selectedUser.getUsername(), selectedUser.getNombre(), "plepla",
//                selectedUser.isAdministrator(),
//                true));
//        System.out.println(usuarioDao.getAll());
//        userDao.delete(selectedUser);
//        userDao.delete(selectedUser);
//        System.out.println(usuarioDao.getAll());

    /* Articulo Works
     */

//        articuloDao.save(new Articulo("This is new...", "As simple as this not cost effective and the learning curve is awful.",
//                usuarioDao.get("chema").get(0).getUsername(), new Date()));
//        articuloDao.update(new Articulo(6, "Flutter is good, but not there yet",
//                "The documentation is stupid",
//                usuarioDao.get("chema").get(0).getUsername(), new Date()));
//        System.out.println(articuloDao.getAll());
//        System.out.println(articuloDao.get("6"));
//Funciona to... respira tu dema....

    /* Comentario Works
     */

//        comentarioDao.save(new Comentario("This is bullshit, Flutter rocks.", 6, "jtmlmass"));
//        comentarioDao.save(new Comentario("Agreed. Flutter rocks", 6, "chema"));
//        comentarioDao.update(new Comentario(1, "Pretty fucking horrid this flutter thing is!.", 6, "jtmlmass"));
//        System.out.println(articuloDao.getAll());
//        System.out.println(articuloDao.get("1"));

//        etiquetaDao.save(new Etiqueta(1, "Desarrollo Movil Nativo"));
//        etiquetaDao.save(new Etiqueta(3, "Opinion"));
//        /*etiquetaDao.update(new Etiqueta(1, "Desarrollo Movil Hibrido"));*/
//        System.out.println(etiquetaDao.getAll());
//        System.out.println(etiquetaDao.get("1"));




//        Usuario user1 = getUser(0);
//        System.out.println(user1);
//        userDao.update(user1, new String[]{"Jake", "jake@domain.com"});

    //Usuario user2 = getUser(1);
    //userDao.delete(user2);
    //userDao.save(new Usuario("Julie", "julie@domain.com"));

    // userDao.getAll().forEach(user -> System.out.println(user.getName()));

   /* private static Usuario getUser(String id) {
        List<Usuario> user = userDao.get(id);

        return user.orElseGet(
                () ->new Usuario("non-existing user", "no-email", "thisPass",
                        false, false));
    }*/
}

