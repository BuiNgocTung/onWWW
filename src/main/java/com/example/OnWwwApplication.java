package com.example;

import com.example.entities.Candidate;
import com.example.entities.Experience;
import com.example.entities.Roles;
import com.example.repositories.CandidateRepository;
import com.example.repositories.ExperienceRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class OnWwwApplication {
    @Autowired
        private CandidateRepository candidateRepository;
    @Autowired

    private ExperienceRepository experienceRepository;

    public static void main(String[] args) {
        SpringApplication.run(OnWwwApplication.class, args);


    }
    @Bean
    CommandLineRunner insertCandidate(){
            return  args -> {

                // Creating a sample Candidate object
                Candidate candidate = new Candidate(1, "1234567890", "example@email.com", "John Doe", null);
                Candidate candidate2 = new Candidate(2, "1234567890", "example@email.com", "John Doe", null);
                Candidate candidate3 = new Candidate(3, "1234567890", null, "John Doe",null);
                List<Candidate> candidateList = new ArrayList<>();
                candidateList.add(candidate);
                candidateList.add(candidate2);
                candidateList.add(candidate3);

                for (Candidate list : candidateList) {
                    candidateRepository.save(list);

                }


                // Creating a sample list of experiences
                List<Experience> experienceList = new ArrayList<>();

                experienceList.add(new Experience(1, LocalDate.of(2018, 5, 15), "Worked as an administrator", Roles.ADMINSTATION, "ABC Company", LocalDate.of(2020, 7, 20), new Candidate(1)));
                experienceList.add(new Experience(2, LocalDate.of(2021, 9, 1), "Managed a team", Roles.MANAGER, "XYZ Corporation",  LocalDate.of(2023, 7, 20), new Candidate(1)));


                List<Experience> experienceList2 = new ArrayList<>();

                experienceList.add(new Experience(3, LocalDate.of(2018, 5, 15), "Worked as an administrator", Roles.ADMINSTATION, "ABC Company", LocalDate.of(2020, 7, 20), new Candidate(2)));
                experienceList.add(new Experience(4, LocalDate.of(2021, 9, 1), "Managed a team", Roles.MANAGER, "XYZ Corporation",  LocalDate.of(2023, 7, 20), new Candidate(2)));


                List<Experience> experienceList3 = new ArrayList<>();

                experienceList.add(new Experience(5, LocalDate.of(2018, 5, 15), "Worked as an administrator", Roles.ADMINSTATION, "ABC Company", LocalDate.of(2020, 7, 20), new Candidate(3)));
                experienceList.add(new Experience(6, LocalDate.of(2021, 9, 1), "Managed a team", Roles.MANAGER, "XYZ Corporation",  LocalDate.of(2023, 7, 20),new Candidate(3)));

                // Saving experiences using the ExperienceRepository
                for (Experience experience : experienceList) {
                    experienceRepository.save(experience);

                }
                for (Experience experience : experienceList2) {
                    experienceRepository.save(experience);

                }
                for (Experience experience : experienceList3) {
                    experienceRepository.save(experience);

                }





            };
    }

}
