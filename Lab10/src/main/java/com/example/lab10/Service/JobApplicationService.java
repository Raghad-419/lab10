package com.example.lab10.Service;

import com.example.lab10.Model.JobApplication;
import com.example.lab10.Model.JobPost;
import com.example.lab10.Model.User;
import com.example.lab10.Repository.JobApplicationRepository;
import com.example.lab10.Repository.JobPostRepository;
import com.example.lab10.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationService {
    private final JobApplicationRepository jobApplicationRepository;
    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;

    public List<JobApplication> getJobApplication(){
        return jobApplicationRepository.findAll();
    }


    public String ApplyJobApplication(JobApplication jobApplication){
        User user =findUserByID(jobApplication.getUserId());
        if(user==null){
            return "User not found";
        }
        JobPost jobPost=findJobPostById(jobApplication.getJobPostId());
        if(jobPost == null){
            return "Job Post not found";
        }
        jobApplicationRepository.save(jobApplication);
        return "True";
    }


    public String updateJobApplication(Integer id, JobApplication jobApplication){
        JobApplication oldjobApplication =jobApplicationRepository.getById(id);
        if(oldjobApplication==null){
            return "F jobApplication not found";
        }
        User user =findUserByID(jobApplication.getUserId());
        if(user==null){
            return "User not found";
        }
        JobPost jobPost =findJobPostById(jobApplication.getJobPostId());
        if(jobPost==null){
            return "JobPost not found";
        }
        oldjobApplication.setJobPostId(jobApplication.getJobPostId());
        oldjobApplication.setUserId(jobApplication.getUserId());
        jobApplicationRepository.save(oldjobApplication);
        return "True";
    }


    public Boolean deleteJobApplication(Integer id){
        JobApplication jobApplication=jobApplicationRepository.getById(id);
        if(jobApplication==null){
            return false;
        }
        jobApplicationRepository.delete(jobApplication);
        return true;
    }


    public User findUserByID(Integer id){
        for(User user1: userRepository.findAll()){
            if(user1.getId().equals(id)){
                return user1;
            }
        }
        return null;
    }

    public JobPost findJobPostById(Integer id){
        for(JobPost jobPost: jobPostRepository.findAll()){
            if(jobPost.getId().equals(id)){
                return jobPost;
            }
        }
        return null;
    }

}
