package com.jitterted.yacht.adapter.web;

import com.jitterted.yacht.domain.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.stream.Collectors;

@Controller
public class YachtController {

  private final Game game;

  @Autowired
  public YachtController(Game game) {
    this.game = game;
  }

  @PostMapping("/rolldice")
  public String rollDice() {
    game.rollDice();
    return "redirect:/rollresult";
  }

  @GetMapping("/rollresult")
  public String rollResult(Model model) {
    model.addAttribute("score", "0");
    String roll = game.lastRoll()
                      .stream()
                      .map(String::valueOf)
                      .collect(Collectors.joining(" "));
    model.addAttribute("roll", roll);
    return "roll-result.html";
  }
}
