package services;

import entidades.Reaccion;

public class ReaccionService extends BaseService<Reaccion> {
    private static ReaccionService reaccionServiceInstance;

    public ReaccionService() {
        super(Reaccion.class);
    }

    public static ReaccionService getInstance(){
        if( reaccionServiceInstance == null){
            reaccionServiceInstance = new ReaccionService();
        }
        return reaccionServiceInstance;
    }
}
