package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Random;

@Controller
public class RollDice {
    @RequestMapping(path = "/roll-dice", method = RequestMethod.GET)
    public String guessNumber() {
        return "roll-dice";
    }
    @RequestMapping(path = "/roll-dice/{numberGuess}", method = RequestMethod.GET)
    public String guessNumber(@PathVariable int numberGuess, Model vModel) {
        Random rand = new Random();

        int  n = rand.nextInt(6) + 1;
        vModel.addAttribute("diceRoll", n);
        vModel.addAttribute("numberGuess", numberGuess);
        return "roll-dice";
    }
}
