package com.tek.acs.dao.impl;

import com.tek.acs.dao.SlotDAO;
import com.tek.acs.data.models.entity.Slot;
import com.tek.acs.repository.SlotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SlotDAOImpl implements SlotDAO {

    private final SlotRepository slotRepository;

    @Override
    public Slot addSlot(Slot slot) {
   return   slotRepository.insert(slot);


    }


}
