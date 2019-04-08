package cnav.gedv.injection.batch.controller;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class InjectionController {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    @Qualifier("myInjectionJob")
    Job injectionJob;

    @GetMapping("/injection")
    public ResponseEntity<String> runInjection() throws Exception {

        JobParameters jobParameters = new JobParametersBuilder()
                .addString("source", "auto")
                .toJobParameters();
        try {
            jobLauncher.run(injectionJob, jobParameters);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, e.getMessage(), e);
        }

    }
}
