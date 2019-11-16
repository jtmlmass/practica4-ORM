package services;

import entidades.Like;

public class DislikeService extends BaseService<Like> {
    private static DislikeService megustaServiceInstance;

    public DislikeService() {
        super(Like.class);
    }

    public static DislikeService getInstance() {
        if (megustaServiceInstance == null) {
            megustaServiceInstance = new DislikeService();
        }
        return megustaServiceInstance;
    }

}
