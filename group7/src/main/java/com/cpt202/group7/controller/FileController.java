package com.cpt202.group7.controller;

import com.cpt202.group7.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Objects;

@Controller
@RequestMapping("/{role}")
public class FileController {
    @Autowired
    private PhotoService photoService;

    @GetMapping("/upload")
    public String showUploadPage(@PathVariable("role") String role,
                                 @RequestParam("category") String category,
                                 @RequestParam("id") Integer categoryId,
                                 Model model){
        model.addAttribute("category", category);
        model.addAttribute("categoryId",categoryId);
        System.out.println(role+"---" + category + "---" + categoryId);
        return "uploadFile";
    }

    @PostMapping("/upload")
    public String handleFileUpload(
            @PathVariable("role") String role,
            @ModelAttribute("category") String category,
            @ModelAttribute("categoryId") String categoryId,
            @RequestParam("file") MultipartFile file,
            Model model) {

        String url = "/" + role + "/upload?category=" + category + "&id=" + categoryId;

        if (file.isEmpty()) {
            model.addAttribute("message", "Please select a file to upload.");
            return "redirect:" + url;
        }

        try {
            String[] fileInfo = Objects.requireNonNull(file.getContentType()).split("/");
            String fileType = fileInfo[0];

            if (!fileType.equals("image")) {
                model.addAttribute("message", "Failed to upload file: Wrong Type.");
            } else {
                String fileName = categoryId + "." + fileInfo[fileInfo.length - 1];

                String resourceDir = System.getProperty("user.dir") + "/src/main/resources/";
                String uploadDir = resourceDir + "static/img/" + category + "Icon/";

                File dir = new File(uploadDir);
                if (!dir.exists()) {
                   boolean success =  dir.mkdirs();
                }
                String filePath = uploadDir + fileName;
                System.out.println(filePath);
                file.transferTo(new File(filePath));

                model.addAttribute("filename", filePath);
                model.addAttribute("message", "File uploaded successfully.");

                String imgName = "/img/"+category+"Icon/"+fileName;

                switch (category) {
                    case "user" -> {
                        model.addAttribute("u",imgName);
                        photoService.updateUserPhoto(imgName,Integer.parseInt(categoryId));
                        return "";
                    }
                    case "groomer" -> {
                        model.addAttribute("g",imgName);
                        photoService.updateGroomerPhoto(imgName,Integer.parseInt(categoryId));
                        return "";
                    }
                    case "service" -> {
                        model.addAttribute("s",imgName);
                        photoService.updateServicePhoto(imgName,Integer.parseInt(categoryId));
                        return "";
                    }
                    case "petType" -> {
                        model.addAttribute("p",imgName);
                        photoService.updatePetTypePhoto(imgName,Integer.parseInt(categoryId));
                        return "";
                    }
                    default -> {
                        model.addAttribute("message", "Failed to upload file");
                        return "redirect:" + url;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("message", "Failed to upload file: " + e.getMessage());
        }
        return "redirect:" + url;
    }
}
