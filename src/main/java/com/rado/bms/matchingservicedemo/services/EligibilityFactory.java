package com.rado.bms.matchingservicedemo.services;

import com.rado.bms.matchingservicedemo.dtos.EmploymentAPI;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
public class EligibilityFactory {

    public static EligibilityResult notEmployed() {
        return EligibilityResult.notEmployed();
    }

    public static EligibilityResult pfEmployed(EmploymentAPI employmentAPI) {
        return new EligibilityResult(true, "Employed", "pf", employmentAPI.getEmployerName(), employmentAPI.getOccupation());
    }

    public static EligibilityResult mohreEmployed(EmploymentAPI employmentAPI) {
        return new EligibilityResult(true, "Employed", "mohre", employmentAPI.getEmployerName(), employmentAPI.getOccupation());
    }

    public static EligibilityResult dofEmployed(EmploymentAPI employmentAPI) {
        return new EligibilityResult(true, "Employed", "dof", employmentAPI.getEmployerName(), employmentAPI.getOccupation());
    }

    public static EligibilityResult takafoEmployed(EmploymentAPI employmentAPI) {
        return new EligibilityResult(true, "Employed", "takafo", employmentAPI.getEmployerName(), employmentAPI.getOccupation());
    }
}
