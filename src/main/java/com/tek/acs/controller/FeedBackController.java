package com.tek.acs.controller;

import com.tek.acs.data.models.entity.FeedBack;
import com.tek.acs.service.FeedBackService;
import com.tek.acs.util.ApiResponseStructure;
import com.tek.acs.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/feedbacks")
@RequiredArgsConstructor
public class FeedBackController {

    private final FeedBackService feedBackService;

    @PostMapping("/")
    public ResponseEntity<ResponseStructure>postFeedBack(@RequestBody FeedBack feedBack)
    {
        return feedBackService.postFeedBack(feedBack);
    }

    @GetMapping ("/{userId}")
    public ResponseEntity<ResponseStructure>findFeedBackByUserId(@PathVariable String userId)
    {
        return feedBackService.findFeedBackByUserId(userId);
    }

    @GetMapping("/top5")
    public ResponseEntity<ResponseStructure> findTop5Feedbacks() {

        return feedBackService.findTop5Feedbacks();
    }


}
