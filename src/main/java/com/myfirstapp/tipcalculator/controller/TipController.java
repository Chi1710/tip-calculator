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


    @GetMapping("")
    public String displayBillAndTipPercentage(Model model){
        model.addAttribute("totalBill", totalBill);
        model.addAttribute("tipPercentage", tipPercentage);
        model.addAttribute("splitNum", splitNum);
        return "index";
    }

    @PostMapping("")
    public String processBillAndTipPercentage(@RequestParam Double totalBill,
                                              @RequestParam Integer tipPercentage,
                                              @RequestParam Integer splitNum,
                                              Model model) {
        
       Double tipAmount = (double) (Math.round((totalBill * tipPercentage / 100) * 100 / 100));

        model.addAttribute("totalBill", totalBill);
        model.addAttribute("tipPercentage", tipPercentage);
        model.addAttribute("splitNum", splitNum);

        if (splitNum > 0 ) {
            Double tip = (double) Math.round((tipAmount / splitNum) * 100 / 100);
            double total = Math.round((totalBill / splitNum) * 100 / 100);
            Double totalPlusTip = tip + total;

            model.addAttribute("tip", tip);
            model.addAttribute("totalPlusTip", totalPlusTip);
        } else {
            Double tip = tipAmount;
            double totalPlusBill = tip + totalBill;
            model.addAttribute("totalPlusTip", totalPlusBill);
            model.addAttribute("tip", tip);
        }

        return "tip";
    }




}
