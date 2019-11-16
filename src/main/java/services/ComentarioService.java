package services;

import entidades.Comentario;

public class ComentarioService extends BaseService<Comentario> {
    private static ComentarioService comentarioServiceInstance;

    public ComentarioService() {
        super(Comentario.class);
    }
    public static ComentarioService getInstance() {
        if(comentarioServiceInstance==null){
            comentarioServiceInstance = new ComentarioService();
        }
        return comentarioServiceInstance;
    }


}
