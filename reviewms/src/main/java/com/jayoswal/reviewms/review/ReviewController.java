package com.jayoswal.reviewms.review;

import com.jayoswal.reviewms.review.dto.AverageReviewDTO;
import com.jayoswal.reviewms.review.messaging.ReviewMessageProducer;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//TODO
//get all reviews of a company
// post a review of a company
// get a review
// put a review
// delete a review
@RestController
@RequestMapping("/reviews")
public class ReviewController {

    private ReviewService reviewService;

    private ReviewMessageProducer reviewMessageProducer;

    public ReviewController(ReviewMessageProducer reviewMessageProducer, ReviewService reviewService) {
        this.reviewMessageProducer = reviewMessageProducer;
        this.reviewService = reviewService;
    }

    @GetMapping
    public ResponseEntity<List<Review>> getAllReviewsOfACompany(@RequestParam int companyId){
        List<Review> allReviews = reviewService.getAllReviews(companyId);
        return new ResponseEntity<>(allReviews, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Review> createReviewOfACompany(@RequestParam int companyId, @RequestBody Review review){
        Review newReview = reviewService.createReview(companyId, review);
        if (newReview != null) {
            reviewMessageProducer.sendMessage(newReview);
            return new ResponseEntity<>(newReview, HttpStatus.CREATED);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{reviewId}")
    public ResponseEntity<Review> getAReview(@PathVariable int reviewId){
        Review review = reviewService.getAReview(reviewId);
        if(review != null) {
            return new ResponseEntity<>(review, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{reviewId}")
    public ResponseEntity<Review> updateAReview(@PathVariable int reviewId, @RequestBody Review review){
        Review updated = reviewService.updateAReview(reviewId, review);
        if(updated != null) {
            return new ResponseEntity<>(updated, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{reviewId}")
    public ResponseEntity<String> deleteAReview(@PathVariable int reviewId) {
        boolean deleted = reviewService.deleteAReview(reviewId);
        if(deleted) {
            return new ResponseEntity<>("Review Deleted", HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>("Deletion Failed", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/average")
    public ResponseEntity<AverageReviewDTO> averageReview(@RequestParam int companyId) {

        List<Review> reviews = reviewService.getAllReviews(companyId);
        Double averageRating = reviews.stream().mapToDouble(Review::getRating).average().orElse(0.0);
        AverageReviewDTO averageReviewDTO = new AverageReviewDTO();
        averageReviewDTO.setAverageRating(averageRating);
        averageReviewDTO.setNumberOfReviews(reviews.size());
        return new ResponseEntity<>(averageReviewDTO, HttpStatus.OK);

    }
}
