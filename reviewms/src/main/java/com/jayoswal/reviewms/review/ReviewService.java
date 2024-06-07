package com.jayoswal.reviewms.review;

import java.util.List;


public interface ReviewService {

    List<Review> getAllReviews(int companyId);
    Review createReview(int companyId, Review review);
    Review getAReview(int reviewId);
    Review updateAReview(int reviewId, Review review);
    Boolean deleteAReview(int reviewId);
}
