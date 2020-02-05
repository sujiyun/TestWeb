package com.web.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Value("${apiUrl}")
    String apiUrl;

    @GetMapping("/list")
    public Object boardList(Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if(session.getAttribute("userName") == null) {
            return new RedirectView( "/");
        }

        RestTemplate restTemplate = new RestTemplate();
        List<JSONPObject> result = restTemplate.getForObject(apiUrl + "/board/list", List.class);

        model.addAttribute("boards", result);
        model.addAttribute("userName", session.getAttribute("userName"));

        return "/board/list";
    }

    @GetMapping({"", "/"})
    public Object getBoard(@RequestParam(value = "id", defaultValue = "0") int id, Model model, HttpServletRequest request) {

        HttpSession session = request.getSession();
        if(session.getAttribute("userName") == null) {
            return new RedirectView( "/");
        }

        RestTemplate restTemplate = new RestTemplate();
        model.addAttribute("board", restTemplate.getForObject(apiUrl + "/board?id=" + id, Map.class));
        model.addAttribute("userId", session.getAttribute("userId"));
        model.addAttribute("userName", session.getAttribute("userName"));

        return "/board/form";
    }

}
