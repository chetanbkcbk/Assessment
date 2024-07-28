package com.tek.acs.service.impl;

import com.tek.acs.dao.AssessmentDAO;
import com.tek.acs.dao.SlotDAO;
import com.tek.acs.dao.UserDAO;
import com.tek.acs.data.models.entity.Assessment;
import com.tek.acs.data.models.entity.Slot;
import com.tek.acs.data.models.entity.User;
import com.tek.acs.repository.AssessmentRepository;
import com.tek.acs.repository.SlotRepository;
import com.tek.acs.repository.UserRepository;
import com.tek.acs.service.SlotService;
import com.tek.acs.util.ApiResponseStructure;
import com.tek.acs.util.CommonConstants;
import com.tek.acs.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class SlotServiceImpl implements SlotService { //dont throw except in service insted give response

    private final SlotRepository slotRepository;
    private final UserRepository userRepository;
    private final UserDAO userDAO;
    private final SlotDAO slotDAO;
    private final AssessmentDAO assessmentDAO;
    private final AssessmentRepository assessmentRepository;
    private final MongoTemplate mongoTemplate;


    @Override
    public ResponseEntity<ResponseStructure> bookSlot(Slot slot) {


        LocalDate currentDate = LocalDate.now();//retrieves the current date.
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd"); // creates a 'formatter' for dates in the yyyy-MM-dd format ,ie(formaters give structured way to convert between date/time objects and their string representations),
        LocalDate userSelectedDate = LocalDate.parse(slot.getDate(), dateFormatter); //converts a String date (slot.getDate()) into "a LocalDate object" (userSelectedDate) using the specified formatter.

        LocalTime currentTime = LocalTime.now();//retrieves the current time.
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");// creates a 'formatter' for times in the HH:mm:ss format.,ie(formaters give structured way to convert between date/time objects and their string representations),
        LocalTime userSelectedTime = LocalTime.parse(slot.getStartTime(), timeFormatter);//converts a String time (slot.getStartTime()) into a "LocalTime object" (userSelectedTime) using the specified formatter.

        LocalTime userSlotEndTime = userSelectedTime.plusMinutes(60);


        slot.setEndTime(userSlotEndTime.format(timeFormatter)); // CORRECT APPROACH setting the slot end time
        // converts the LocalTime object userSlotEndTime into a "String" formatted according to the pattern specified (HH:mm:ss).


        // Validate if the date is the current date or future dates
        if (userSelectedDate.isBefore(currentDate)) {
ApiResponseStructure.badResponse(CommonConstants.INVALID_DATE);
        }

        //if i choose today date, making sure the start & end time are not before current time
        if (userSelectedDate.isEqual(currentDate) && (userSelectedTime.isBefore(currentTime) || userSelectedDate.isEqual(currentDate) && userSlotEndTime.isBefore(currentTime))) {
            ApiResponseStructure.badResponse(CommonConstants.INVALID_TIME);
        }
//        String userId = slot.getUserId();
//        User user = userDAO.findingUserById(userId); 
        String assessmentId = slot.getAssessmentId();

        Query query = new Query(Criteria.where("_id").is(assessmentId));
        Assessment assessment = mongoTemplate.findOne(query, Assessment.class);
        //check for slot overlap

        if (Objects.nonNull(assessment)) {
            Slot existSlot = assessment.getSlot();
            if (existSlot != null) //if that assessment already has a slot ,make sure that the existing slot which has a start time and end time ,will not collide with the "tobebooked slot timings"
            {
                LocalTime existingSlotStartTime = LocalTime.parse(existSlot.getStartTime(), timeFormatter);
                LocalTime existingSLotEndTime = LocalTime.parse(existSlot.getEndTime(), timeFormatter);
                if ((userSelectedTime.isBefore(existingSLotEndTime) && userSlotEndTime.isAfter(existingSlotStartTime))) {
                    ApiResponseStructure.badResponse(CommonConstants.SLOT_OVERLAPS);
                }
            }
            //if no slot is present in any assessment ,i have to  book a slot to that assessment
            else {
                assessment.setSlot(slot); //embed the slot into the assessment
                assessmentDAO.createAssesment(assessment); //and then save the assessment

                Slot savedintoassessment = slotDAO.addSlot(slot);//to save the slot in separate collection
                return ApiResponseStructure.createResponse(CommonConstants.BOOK_SLOT);

            }
        }

            throw new NoSuchElementException("Assessment not found");



    }
    }





/*            for(String id:assessmentIds)   //traverse each assessmentid
            {
                Assessment assessment = assessmentDAO.findByid(id); //find the assessment by id
                if(assessment!=null)
                {
                    Slot existSlot = assessment.getSlot(); //if the assessment is present get the slot and check if the slot is null or not

                    if(existSlot!=null) //if that assessment already has a slot ,make sure that the existing slot which has a start time and end time ,will not collide with the "tobebooked slot timings"
                    {
                        LocalTime existingSlotStartTime = LocalTime.parse(existSlot.getStartTime(), timeFormatter);
                        LocalTime existingSLotEndTime = LocalTime.parse(existSlot.getEndTime(),timeFormatter);
                        if ((userSelectedTime.isBefore(existingSLotEndTime) && userSlotEndTime.isAfter(existingSlotStartTime)))
                        {
                            throw new NoSuchElementException("Slot time overlaps with one of the existing assessments that is already booked. Please choose another time.");
                        }
                    }
                    //if no slot is present in any assessment ,i have to  book a slot to that assessment
                   else {
                        assessment.setSlot(slot); //embed the slot into the assessment
                        assessmentDAO.createAssesment(assessment); //and then save the assessment

                        Slot savedintoassessment = slotDAO.addSlot(slot);//to save the slot in separate collection
                        return ApiResponseStructure.createResponse("Booked a slot for that assessment");

                    }
                }

            }

        }
        return ApiResponseStructure.badResponse("NO User found with such id");
    }
}

*/

  /*
            boolean overlapDetected = assessmentIds.stream().map(id -> assessmentDAO.findByid(id)).filter(Objects::nonNull).map(Assessment::getSlot).filter(Objects::nonNull)
                    .map(existSlot -> {
                        LocalTime existingSlotStartTime = LocalTime.parse(existSlot.getStartTime(), timeFormatter);//existSlot.getStartTime() returns string form which will get formatted into local time
                        LocalTime existingSlotEndTime = LocalTime.parse(existSlot.getEndTime(), timeFormatter);
                        return userSelectedTime.isBefore(existingSlotEndTime) && userSlotEndTime.isAfter(existingSlotStartTime);
                    })
                    .findFirst()
                    .orElse(false);

            if (overlapDetected) {
                throw new NoSuchElementException("Slot time overlaps with an existing assessment. Please choose another time.");
            } else {

                assessment.setSlot(slot); //embed the slot into the assessment
                assessmentDAO.createAssesment(assessment); //and then save the assessment

                Slot savedintoassessment = slotDAO.addSlot(slot);//to save the slot in separate collection
                return ApiResponseStructure.createResponse("Booked a slot for that assessment");
            }
        }
    }


   */