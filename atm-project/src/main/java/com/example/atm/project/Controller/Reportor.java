package com.example.atm.project.Controller;

import com.example.atm.project.Entity.Custodian;
import com.example.atm.project.Service.CustodianService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@RestController
@RequestMapping("Atm/v1.0/Report")
@SecurityRequirement(name = "basicAuth")
public class Reportor {
    @Autowired
    private CustodianService ser;
        @GetMapping("/download")
        @PreAuthorize("hasAuthority('REPORTER')")
    public ResponseEntity<ByteArrayResource> downloadFile() {
        // Replace this with your actual file path
        String filePath = "D:\\backupatm.txt";
        File file = new File(filePath);

        try {
            byte[] fileContent = Files.readAllBytes(file.toPath());
            ByteArrayResource resource = new ByteArrayResource(fileContent);
            return ResponseEntity.ok()
                    .contentType(MediaType.valueOf("backup/txt"))
                    .body(resource);
        } catch (Exception e) {
           return ResponseEntity.notFound().build();
        }
    }
        @GetMapping("/get")
    public ResponseEntity<List<Custodian>> get(){
        return new ResponseEntity<>(ser.GetReport(), HttpStatus.OK);
    }
}
