package com.jayoswal.reviewms.review.messaging;

import com.jayoswal.reviewms.review.Review;
import com.jayoswal.reviewms.review.dto.ReviewMessageDTO;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;

    public ReviewMessageProducer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void sendMessage(Review review) {
        ReviewMessageDTO reviewMessageDTO = new ReviewMessageDTO();
        reviewMessageDTO.setId(review.getId());
        reviewMessageDTO.setCompanyId(review.getCompanyId());
        reviewMessageDTO.setRating(review.getRating());

        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessageDTO);
    }
}
