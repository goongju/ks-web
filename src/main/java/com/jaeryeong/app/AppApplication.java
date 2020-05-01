package com.jaeryeong.app;

import com.jaeryeong.app.entity.*;
import com.jaeryeong.app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class AppApplication implements CommandLineRunner {

    private final PersonRepository personRepository;
    private final InterestsRepository interestsRepository;
    private final SocialMediaRepository socialMediaRepository;
    private final SkillRepository skillRepository;
    private final WorkRepository workRepository;

    public AppApplication(PersonRepository personRepository, InterestsRepository interestsRepository, SocialMediaRepository socialMediaRepository, SkillRepository skillRepository, WorkRepository workRepository) {
        this.personRepository = personRepository;
        this.interestsRepository = interestsRepository;
        this.socialMediaRepository = socialMediaRepository;
        this.skillRepository = skillRepository;
        this.workRepository = workRepository;
    }


    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        personRepository.save(new Person("윤재령", "백엔드", "hello@test.com", "010-1234-5678"));
        socialMediaRepository.save(new SocialMedia("anonymous","anonymous"));
        interestsRepository.save(new Interests("넷플릭스 보기"));
        interestsRepository.save(new Interests("게임하기"));
        skillRepository.save(new Skill("Spring",50));
        skillRepository.save(new Skill("DJANGO",30));
        workRepository.save(new Work("개발자", "A 회사", "3년", "ㅇㅇ프로그램을 개발하는 데에 참여하였음"));
        workRepository.save(new Work("백엔드개발자", "B 회사", "1년", "ㅇㅇ회사의 DB관리 "));
    }
}