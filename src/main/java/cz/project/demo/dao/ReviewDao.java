package cz.project.demo.dao;

import cz.project.demo.model.Review;
import org.springframework.stereotype.Repository;

@Repository
public class ReviewDao extends BaseDao<Review> {
    public ReviewDao() {
        super(Review.class);
    }
}
