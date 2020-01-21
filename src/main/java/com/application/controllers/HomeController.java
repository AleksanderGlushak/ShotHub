package com.application.controllers;

import com.application.beans.Message;
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
@RequestMapping("/Home")
public class HomeController {
    private static final String PHOTOS_DIRECTORY = "photos/";
    static int clientId;
    @Autowired
    private MessageRepo messageRepo;
    @Autowired
    private UserRepo userRepo;
    @GetMapping
    public String home(Map<String, Object> model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        clientId = userRepo.findIdByName(userDetails.getUsername());
        Iterable<Message> messages = messageRepo.getUploaded(clientId);
        model.put("messages", messages);
        return "home";
    }

    @PostMapping
    public String add(@AuthenticationPrincipal User user,
                      @RequestParam String text,
                      @RequestParam Integer cost,
                      @RequestParam MultipartFile image,
                      Map<String, Object> model
                      ) throws IOException {
        File createdFile = new File(PHOTOS_DIRECTORY+clientId);

        if(!createdFile.exists())
            createdFile.mkdir();
        Message message = new Message(user,text,cost);
        messageRepo.save(message);
        String fileName = image.getOriginalFilename();
        createdFile = new File("photos/"+ clientId +"/"
                                + messageRepo.getLastId(message.getUserId())
                                + fileName.substring(fileName.lastIndexOf(".")));
        createdFile.createNewFile();
        messageRepo.updateFilePath(message.getId(),message.getUserId(),"file:///" + createdFile.getAbsolutePath());
        InputStream is = null;
        OutputStream os = null;
        try {
            is = new ByteArrayInputStream(image.getBytes());
            os = new FileOutputStream(createdFile);
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) > 0) {
                os.write(buffer, 0, length);
            }
        } finally {
            is.close();
            os.close();
        }
        return "redirect:/Home";
    }

}
