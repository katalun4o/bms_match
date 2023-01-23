package com.rado.bms.matchingservicedemo.matching.exec;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component(value = "JobSeekerMatchExecutor")
public class JobSeekerMatchExecutor implements IMatchExecutor {
    private Logger LOG = LoggerFactory.getLogger(JobSeekerMatchExecutor.class);

    @Override
    public void execute(String id) {
        LOG.info("JobSeeker matching executor started for id: " + id);
        // simulate matching with thread sleep 2sec
        // FIXME: delete when matching is added
        JobSeeker[] jsList = new JobSeeker[40000];
        JobPosting[] jpList = new JobPosting[4000];
        setUp(jsList, jpList);

        LOG.info("before eval");
        Arrays.stream(jsList).parallel().forEach(js -> {
            for(JobPosting jp : jpList) {
                doMatch(js, jp);
            }
        });
        LOG.info("after eval");
    }

    private void doMatch(JobSeeker js, JobPosting jp) {
        boolean eduMatch = js.education.equals(jp.requiredEducation);

        for (String skill : js.skills){
            for (String rSkill : jp.requiredSkills) {
                boolean skillMatch = skill.equals(rSkill);
            }
        }

        for (String knowl : js.knowledge){
            for (String rKnowl : jp.requiredKnowledge) {
                boolean skillMatch = knowl.equals(rKnowl);
            }
        }
    }

    private void setUp(JobSeeker[] jsList, JobPosting[] jpList) {
        for(int i = 0; i < jsList.length; i++) {
            jsList[i] = new JobSeeker();
            jsList[i].education = "Education" + Math.random();
            jsList[i].knowledge = createMock("knowledge", 10);
            jsList[i].skills = createMock("skills", 10);
        }

        for(int i = 0; i < jpList.length; i++) {
            jpList[i] = new JobPosting();
            jpList[i].requiredEducation = "Education" + Math.random();
            jpList[i].requiredKnowledge = createMock("knowledge", 10);
            jpList[i].requiredSkills = createMock("skills", 10);
        }
    }

    private static String[] createMock(String key, int len) {
        String[] mockList = new String[len];
        for(int i = 0; i < len; i++) {
            mockList[i] = key + len;
        }

        return mockList;
    }

    static class JobSeeker {
        String education;
        String[] skills;
        String[] knowledge;
    }

    static class JobPosting {
        String requiredEducation;
        String[] requiredSkills;
        String[] requiredKnowledge;
    }
}
