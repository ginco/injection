package cnav.gedv.injection.batch;

import cnav.gedv.injection.batch.service.*;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class InjectionJob extends JobExecutionListenerSupport {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Value("${input.file}")
    Resource resource;

    @Autowired
    CustomItemProcessor processor;

    @Autowired
    CSVLotProcessor csvLotProcessor;

    @Autowired
    CustomItemWriter writer;

    @Value("${output.file}")
    Resource outputResource;

    @Bean(name = "myInjectionJob")
    public Job injectionJob() {

        /*Step step = stepBuilderFactory.get("step-1")
                .<User, User> chunk(5)
                .reader(new CustomItemReader(resource))
                .processor(processor)
                .writer(writer)
                .build();*/

        Step splitStep = stepBuilderFactory.get("split-step(1)")
                .<String, String> chunk(200)
                .reader(new CSVInputFileReader(resource))
                .processor(csvLotProcessor)
                .writer(new CSVOutputFileWriter(outputResource))
                .build();


        Job job = jobBuilderFactory.get("injection-job")
                .incrementer(new RunIdIncrementer())
                .listener(this)
                .start(splitStep)
                .build();

        return job;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println(jobExecution.getStatus());
    }

}
