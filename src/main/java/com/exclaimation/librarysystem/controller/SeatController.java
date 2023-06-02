package com.exclaimation.librarysystem.controller;


import com.exclaimation.librarysystem.domain.Seat;
import com.exclaimation.librarysystem.service.SeatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;

@Controller
public class SeatController {

    private final SeatService seatService;

    @Autowired
    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/seat")
    public String seat(Model model){
        // 48
        ArrayList<Seat.Simple> seats = (ArrayList<Seat.Simple>) seatService.findSeats();

        // 3 x 2 x 2 x 4
        ArrayList<ArrayList> groups = new ArrayList<>();
        for(int i = 0; i < 3; i++)
        {
            ArrayList<ArrayList> group_row = new ArrayList<>();
            // 2 x 2 x 4
            for(int j = 0; j < 2; j++)
            {
                // 2 x 4
                ArrayList<ArrayList> group = new ArrayList<>(); // int[] 대신 Seat[] 가 들어가야함
                int start = 1+i*16+j*4;
                for(int k = 0; k < 2; k++) {
                    // 4
                    ArrayList<Seat.Simple> seat_row = new ArrayList<>();
                    for (int l = 0; l < 4; l++)
                    {
                        int seat_id = start+l+k*8-1;
                        seat_row.add(seats.get(seat_id));
                    }
                    group.add(seat_row);
                }
                group_row.add(group);
            }
            groups.add(group_row);

        }

        model.addAttribute("groups", groups);
        return "seat/seat";
    }

    @PostMapping("/seat")
    public String updateSeat(Seat.Simple form, boolean isUsed){

        form.setUsed(isUsed);
        System.out.println(form);

        seatService.updateSeat(form);

        return "redirect:/seat";
    }

}
