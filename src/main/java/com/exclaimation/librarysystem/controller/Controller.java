package com.exclaimation.librarysystem.controller;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@org.springframework.stereotype.Controller
public class Controller {

    @GetMapping("/seat")
    public String seat(Model model){

        int[][] seat_id = new int[6][8];
        for(int i = 0; i < 6; i++)
            for(int j = 0; j < 8; j++)
                seat_id[i][j] = 8*i+j+1;

        // 3 x 2 x 2 x 4
        ArrayList<ArrayList> groups = new ArrayList<>();
        for(int i = 0; i < 3; i++)
        {
            ArrayList<ArrayList> group_row = new ArrayList<>();
            // 2 x 2 x 4
            for(int j = 0; j < 2; j++)
            {
                // 2 x 4
                ArrayList<int[]> group = new ArrayList<>();
                int start = 1+i*16+j*4;
                for(int k = 0; k < 2; k++) {
                    // 4
                    int[] seat_row = new int[4];
                    for (int l = 0; l < 4; l++)
                        seat_row[l] = start+l+k*8; // seat_id -> seat

                    group.add(seat_row);
                }
                group_row.add(group);
            }
            groups.add(group_row);

        }


        model.addAttribute("groups", groups);

        return "seat";
    }
}
