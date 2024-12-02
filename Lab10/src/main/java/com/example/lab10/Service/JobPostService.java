package com.example.lab10.Service;

import com.example.lab10.Model.JobPost;
import com.example.lab10.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {
    private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPost(){
        return jobPostRepository.findAll();
    }


    public void addJobPost(JobPost jobPost){
        jobPostRepository.save(jobPost);
    }

    public Boolean updateJobPost(Integer id, JobPost jobPost){
        JobPost oldJobPost = jobPostRepository.getById(id);

        if(oldJobPost==null){
            return false;
        }
        oldJobPost.setPostingDate(jobPost.getPostingDate());
        oldJobPost.setDescription(jobPost.getDescription());
        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setSalary(jobPost.getSalary());
        oldJobPost.setLocation(jobPost.getLocation());
        jobPostRepository.save(oldJobPost);
        return true;

    }

    public Boolean deleteJobPost(Integer id){
        JobPost jobPost =jobPostRepository.getById(id);
        if(jobPost==null){
            return false;
        }
        jobPostRepository.delete(jobPost);
        return true;
    }

}
