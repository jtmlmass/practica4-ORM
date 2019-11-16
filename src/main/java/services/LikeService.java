package services;

import entidades.Like;

public class LikeService extends BaseService<Like> {
    private static LikeService likeServiceInstance;

    public LikeService(){
        super(Like.class);
    }

    public static LikeService getInstance(){
        if (likeServiceInstance == null){
            likeServiceInstance = new LikeService();
        }
        return likeServiceInstance;
    }

}
