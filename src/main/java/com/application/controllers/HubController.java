package com.application.controllers;

import com.application.beans.Message;
import com.application.beans.Role;
import com.application.beans.User;
import com.application.repos.MessageRepo;
import com.application.repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.HttpRequestHandler;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.*;
import sun.plugin.liveconnect.SecurityContextHelper;

import java.io.*;
import java.util.Map;

@Controller
@RequestMapping("/ShotHub")
public class HubController {
    private static final String PHOTOS_DIRECTORY = "photos/";
    static int clientId;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String home(@AuthenticationPrincipal User user,Map<String, Object> model){
        if(user==null)
            model.put("auth",false);
        else
            model.put("auth",true);
        Iterable<Message> messages = messageRepo.getAll();
        model.put("messages", messages);
        return "hub";
    }
}

