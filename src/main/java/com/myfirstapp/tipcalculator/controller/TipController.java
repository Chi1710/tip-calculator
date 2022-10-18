package com.myfirstapp.tipcalculator.controller;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping
public class TipController {

    private static Double totalBill;
    private static Integer tipPercentage;
    private static Integer splitNum;
//    private static Double tipAmount;


    @GetMapping("tip")
    public String displayBillAndTipPercentage(@NotNull Model model){
        model.addAttribute("totalBill", totalBill);
        model.addAttribute("tipPercentage", tipPercentage);
        model.addAttribute("SplitNum", splitNum);
        return "index";
    }

    @PostMapping("tip")
    public String processBillAndTipPercentage(@RequestParam Double totalPlusTip,
                                              Model model, Double totalBill,
                                              Integer tipPercentage, Integer splitNum,
                                              Double tip,
                                              Double tipAmount) {
        
        tipAmount = (double) (Math.round((totalBill * tipPercentage / 100) * 100 / 100));

        if (splitNum > 0 ) {
            tip = (double) Math.round((tipAmount / splitNum) * 100 / 100);
            double total = Math.round((totalBill / splitNum) * 100 / 100);
            totalPlusTip = tip + total;

            model.addAttribute("tip", tip);
            model.addAttribute("totalPlusTip", totalPlusTip);
        } else {
            tip = tipAmount;
            double totalPlusBill = tip + totalBill;
            model.addAttribute("totalPlusTip", totalPlusBill);
            model.addAttribute("tip", tip);
        }

        return "tip";
    }




}
