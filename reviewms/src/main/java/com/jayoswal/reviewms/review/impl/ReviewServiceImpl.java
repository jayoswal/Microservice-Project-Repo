package com.jayoswal.reviewms.review.impl;

import com.jayoswal.reviewms.review.Review;
import com.jayoswal.reviewms.review.ReviewRepository;
import com.jayoswal.reviewms.review.ReviewService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private ReviewRepository reviewRepository;



    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }


    @Override
    public List<Review> getAllReviews(int companyId) {
       List<Review> reviews = reviewRepository.findByCompanyId(companyId);
       return reviews;
    }

    @Override
    public Review createReview(int companyId, Review review) {
        Review newReview = new Review();
        newReview.setTitle(review.getTitle());
        newReview.setDescription(review.getDescription());
        newReview.setRating(review.getRating());
        if(companyId > 0){
            newReview.setCompanyId(companyId);
            reviewRepository.save(newReview);
            return newReview;
        }

        return null;

    }

    @Override
    public Review getAReview(int reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null) {
            return review;
        }
        else {
            return null;
        }

    }

    @Override
    public Review updateAReview(int reviewId, Review review) {
        Review oldReview = reviewRepository.findById(reviewId).orElse(null);
        if(oldReview == null) {
            return null;
        }
        else {
            oldReview.setTitle(review.getTitle());
            oldReview.setDescription(review.getDescription());
            oldReview.setRating(review.getRating());
            oldReview.setCompanyId(review.getCompanyId());
            Review saved = reviewRepository.save(oldReview);
            return saved;
        }
    }

    @Override
    public Boolean deleteAReview(int reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if(review != null) {
            reviewRepository.deleteById(reviewId);
            return true;
        }
        else {
            return false;
        }
    }
}
