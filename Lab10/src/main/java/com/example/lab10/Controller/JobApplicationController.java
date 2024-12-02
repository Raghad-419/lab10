package com.example.lab10.Controller;

import com.example.lab10.ApiResponse.ApiResponse;
import com.example.lab10.Model.JobApplication;
import com.example.lab10.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/jobApplication")
public class JobApplicationController {
    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity getJobApplication(){
        return ResponseEntity.status(200).body(jobApplicationService.getJobApplication());
    }

    @PostMapping("/add")
    public ResponseEntity ApplyJobApplication(@RequestBody @Valid JobApplication jobApplication, Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
        char result =jobApplicationService.ApplyJobApplication(jobApplication).charAt(0);
        return switch (result){
            case 'T' -> ResponseEntity.status(200).body(new ApiResponse("Job Application added"));
            case 'U' ->ResponseEntity.status(400).body(new ApiResponse("User not found"));
            default -> ResponseEntity.status(400).body(new ApiResponse("Job Post not found"));
        };
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateJobApplication(@PathVariable Integer id ,@RequestBody @Valid JobApplication jobApplication,Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(errors.getFieldError().getDefaultMessage());
        }
    char result = jobApplicationService.updateJobApplication(id,jobApplication).charAt(0);

        return switch (result){
            case 'T' -> ResponseEntity.status(200).body(new ApiResponse("Job Application updated"));
            case 'U' ->ResponseEntity.status(400).body(new ApiResponse("User not found"));
            case 'F' ->ResponseEntity.status(400).body(new ApiResponse("Job Application not found"));
            default -> ResponseEntity.status(400).body(new ApiResponse("Job Post not found"));
        };

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteJobApplication(@PathVariable Integer id){
        if(jobApplicationService.deleteJobApplication(id)){
            return ResponseEntity.status(200).body(new ApiResponse("Job Application deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Job Application not found"));
    }

}
