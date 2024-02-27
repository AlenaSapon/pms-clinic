package com.sapon.pmsc.helper;

public class BusinessLogMessage {
    private BusinessLogMessage() {
        throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
    }
    public static class Clinic {
        private Clinic() {
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String CLINIC_CREATED = "Clinic created successfully";

        public static final String CLINIC_ALREADY_EXISTS = "Clinic already exists : {}";
    }

    public static class Department {
        private Department() {
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String DEPARTMENT_ADDED_TO_CLINIC = "Department is added to clinic successfully : {}";

        public static final String DEPARTMENT_CREATED = "Department created successfully";
        public static final String DEPARTMENT_UPDATED = "Department updated successfully : {}";
        public static final String DEPARTMENT_DELETED = "Department deleted successfully : {}";
        public static final String DEPARTMENT_FOUND = "Department found successfully : {}";
        public static final String DEPARTMENT_LIST_FOUND = "Department list found successfully";
        public static final String DEPARTMENT_NOT_FOUND = "Department not found : {}";
        public static final String DEPARTMENT_LIST_EMPTY = "Department list is empty";
        public static final String DEPARTMENT_ALREADY_EXISTS = "Department already exists : {}";
    }

    public static class Employee {
        private Employee() {
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }

        public static final String EMPLOYEE_CREATED = "Employee created successfully";
        public static final String EMPLOYEE_UPDATED = "Employee updated successfully : {}";
        public static final String EMPLOYEE_DELETED = "Employee deleted successfully : {}";
        public static final String EMPLOYEE_FOUND = "Employee found successfully : {}";
        public static final String EMPLOYEE_LIST_FOUND = "Employee list found successfully";
        public static final String EMPLOYEE_NOT_FOUND = "Employee not found : {}";
        public static final String EMPLOYEE_LIST_EMPTY = "Employee list is empty";
        public static final String EMPLOYEE_ALREADY_EXISTS = "Employee already exists : {}";
    }

    public static class Patient {
        private Patient() {
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }
        public static final String PATIENT_ALREADY_EXISTS_EMAIL = "Patient already exists with this email : {}";

        public static final String PATIENT_ALREADY_EXISTS_PID = "Patient already exists with this PID : {}";
        public static final String PATIENT_CREATED = "Patient created successfully";
        public static final String PATIENT_UPDATED = "Patient updated successfully : {}";
        public static final String PATIENT_DELETED = "Patient deleted successfully : {}";
        public static final String PATIENT_NOT_FOUND = "Patient not found : {}";
                 }

    public static class Allergy {
        private Allergy() {
            throw new IllegalStateException(BusinessMessage.ILLEGAL_STATE_EXCEPTION);
        }
        public static final String ALLERGY_CREATED = "Allergy created successfully";
        public static final String ALLERGY_UPDATED = "Allergy updated successfully : {}";
        public static final String ALLERGY_DELETED = "Allergy deleted successfully : {}";
        public static final String ALLERGY_NOT_FOUND = "Allergy not found : {}";
    }


}
