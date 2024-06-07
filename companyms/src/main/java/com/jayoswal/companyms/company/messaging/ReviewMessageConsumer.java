package com.jayoswal.companyms.company.messaging;

import com.jayoswal.companyms.company.CompanyService;
import com.jayoswal.companyms.company.dto.ReviewMessageDTO;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
public class ReviewMessageConsumer {
    private final CompanyService companyService;

    public ReviewMessageConsumer(CompanyService companyService) {
        this.companyService = companyService;
    }


    @RabbitListener(queues = "companyRatingQueue")
    public void consumeMessage(ReviewMessageDTO reviewMessageDTO){
        companyService.updateCompanyRating(reviewMessageDTO);
    }
}
