package capstone.market.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class BatchController {

    private final JobLauncher jobLauncher;
    private final Job dummyDataJob;


    @GetMapping("/batch")
    public void runBatchJob(@RequestParam("count") int count) {
        try {
            JobParameters jobParameters = new JobParametersBuilder()
                    .addLong("time", System.currentTimeMillis()) // 매번 새로운 파라미터 생성
                    .addLong("count", (long) count) // 클라이언트에서 받은 반복 횟수 추가
                    .toJobParameters();

            jobLauncher.run(dummyDataJob, jobParameters); // 배치 작업 실행
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
