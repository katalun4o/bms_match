package com.rado.bms.matchingservicedemo.services;

import com.rado.bms.matchingservicedemo.dtos.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
class EligibilityService {

    @Autowired
    private
    RestTemplate restTemplate;

    EligibilityResult checkEligibility(String id) {

        EmploymentAPI dofResponse = getDofResponse(id);
        if (dofResponse.isEmployed()) {
            return EligibilityFactory.dofEmployed(dofResponse);
        }

        EmploymentAPI mohreResponse = getMohreResponse(id);
        if (mohreResponse.isEmployed()) {
            return EligibilityFactory.mohreEmployed(mohreResponse);
        }

        EmploymentAPI pfResponse = getPfResponse(id);
        if (pfResponse.isEmployed()) {
            return EligibilityFactory.pfEmployed(pfResponse);
        }

        EmploymentAPI takafoResponse = getTakafoResponse(id);
        if (takafoResponse.isEmployed()) {
            return EligibilityFactory.takafoEmployed(takafoResponse);
        }

        return EligibilityFactory.notEmployed();
    }

    private EmploymentAPI getDofResponse(String id) {
        return getApiResponse(DofResponse.class, id);
    }

    private EmploymentAPI getMohreResponse(String id) {
        return getApiResponse(MohreResponse.class, id);
    }

    private EmploymentAPI getPfResponse(String id) {
        return getApiResponse(PfResponse.class, id);
    }

    private EmploymentAPI getTakafoResponse(String id) {
        return getApiResponse(PfResponse.class, id);
    }

    private EmploymentAPI checkTakafoForEmployment(String id) {
        // query elastic search
        return new TakafoResponse(false, "ministry of health", "nurse");
    }

    private <T extends EmploymentAPI> EmploymentAPI getApiResponse(Class<T> responseClass, String id) {

        // User by Id
        T dof = restTemplate.getForObject("/jobSeeker/{id}", responseClass);
        ResponseEntity<T> responseEntityUser = restTemplate.getForEntity("/jobSeeker/{id}", responseClass, Collections.singletonMap("id", id));

        if (responseEntityUser.getStatusCode() != HttpStatus.OK) {
            return null;
        }

        return responseEntityUser.getBody();
    }
}
