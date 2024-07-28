package com.tek.acs.controller;

import com.tek.acs.data.models.entity.Slot;
import com.tek.acs.service.SlotService;
import com.tek.acs.util.ResponseStructure;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/slots")
@RequiredArgsConstructor
public class SlotController {


    private final SlotService slotService;

    @PostMapping("/")
    public ResponseEntity<ResponseStructure> bookSlot(@RequestBody Slot slot)
    {

        return slotService.bookSlot(slot);
    }




}
