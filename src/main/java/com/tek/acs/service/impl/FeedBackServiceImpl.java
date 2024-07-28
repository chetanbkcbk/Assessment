package com.tek.acs.service.impl;

import com.tek.acs.data.models.entity.FeedBack;
import com.tek.acs.repository.FeedBackRepository;
import com.tek.acs.service.FeedBackService;
import com.tek.acs.util.ApiResponseStructure;
import com.tek.acs.util.CommonConstants;
import com.tek.acs.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FeedBackServiceImpl implements FeedBackService {

    private final FeedBackRepository feedBackRepository;

    private final MongoTemplate mongoTemplate;

    @Override
    public ResponseEntity<ResponseStructure> postFeedBack(FeedBack feedBack) {
        feedBack.createBaseEntity(feedBack.getCreatedByName(), feedBack.getCreatedByEmail());
//invoking base entity methods just before saving that subentity into the DB
        FeedBack fb = feedBackRepository.insert(feedBack);
        return ApiResponseStructure.createResponse(fb);
    }

    @Override
    public ResponseEntity<ResponseStructure> findFeedBackByUserId(String userId) {
        Optional<FeedBack> feedBack = feedBackRepository.findById(userId);
        if (feedBack != null) {
            return ApiResponseStructure.okResponse(feedBack);
        } else {
           // throw new NoSuchElementException("No FeedBack with such userid found");
            return ApiResponseStructure.badResponse(CommonConstants.NO_USER);//using common constants class
        }
    }

    @Override
    public ResponseEntity<ResponseStructure> findTop5Feedbacks() {

/*
        // Fetch top 5 feedbacks sorted by rating using ' METHOD' in repository
        List<FeedBack> topfivefeedbacks = feedBackRepository.findTop5ByOrderByRatingDesc();

        return ApiResponseStructure.okResponse(topfivefeedbacks);
*/



/*USING STREAMS
        List<FeedBack> feedBacks = feedBackRepository.findAll();
// Sort feedbacks by rating in descending order
        List<FeedBack> sortedFeedbacks = feedBacks.stream() // Convert the list of feedbacks to a stream

                .sorted(Comparator.comparingInt(FeedBack::getRating).reversed()) //   Sort the stream by rating in descending order

                .collect(Collectors.toList()); //      Collect the sorted stream into a new list


        // Get top 5 feedbacks
        List<FeedBack> top5Feedbacks = sortedFeedbacks.stream()
                .limit(5)       // Limit the stream to the first 5 elements

                .collect(Collectors.toList());    // Collect the limited stream into a new list


        return ApiResponseStructure.okResponse(top5Feedbacks);
    }

 */

        //using
        Query query=new Query();//Query object is used to build and customize the query that will be executed against the MongoDB collection. It acts as a container for query criteria, sort instructions, and other query parameters.
        query.with(Sort.by(Sort.Order.desc("rating")));//The with method is used to add sorting instructions to the query. In this case, it sorts the results by the rating field in descending order (Sort.Order.desc("rating"))
        query.limit(5);//The limit method restricts the number of results to the specified value, in this case, 5
        List<FeedBack> top5Feedbacks = mongoTemplate.find(query, FeedBack.class);//The mongoTemplate.find method executes the query and maps the results to instances of the FeedBack class. The FeedBack.class parameter specifies the type of documents expected in the result
        return ApiResponseStructure.okResponse(top5Feedbacks);
    }

    }



