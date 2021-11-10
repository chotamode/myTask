package cz.project.demo.service;

import cz.project.demo.dao.MessageDao;
import cz.project.demo.dao.ReviewDao;
import cz.project.demo.model.Message;
import cz.project.demo.model.Review;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ReviewService {

    private ReviewDao dao;

    public ReviewService(ReviewDao dao) {
        this.dao = dao;
    }

    @Transactional
    public void persist(Review review) {
        dao.persist(review);
    }

}
