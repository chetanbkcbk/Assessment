package com.tek.acs.service;

import com.tek.acs.data.models.entity.Slot;
import com.tek.acs.util.ResponseStructure;
import org.springframework.http.ResponseEntity;

import java.util.Date;

public interface SlotService {

    public ResponseEntity<ResponseStructure> bookSlot(Slot slot);
}
