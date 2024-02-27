package com.sapon.pmsc.helper;

public class BusinessMessage {

    public static final String ILLEGAL_STATE_EXCEPTION = "Utility class, cannot be instantiated";

    private BusinessMessage() {
        throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION);
    }


    public static class Clinic {
        private Clinic() {
            throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION);
        }

        public static final String CLINIC_NOT_FOUND = "Clinic not found";
        public static final String CLINIC_ALREADY_EXISTS = "Clinic already exists";
    }

    public static class Department {
        private Department() {
            throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION);
        }

        public static final String DEPARTMENT_NOT_FOUND = "Department not found";
        public static final String DEPARTMENT_LIST_EMPTY = "Department list is empty";
        public static final String DEPARTMENT_ALREADY_EXISTS = "Department already exists";
    }

    public static class Employee {
        private Employee() {
            throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION);
        }

        public static final String EMPLOYEE_NOT_FOUND = "Employee not found";
        public static final String EMPLOYEE_LIST_EMPTY = "Employee list is empty";
        public static final String EMPLOYEE_ALREADY_EXISTS = "Employee already exists";
    }

    public static class Patient {
        private Patient() {
            throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION);
        }

        public static final String PATIENT_NOT_FOUND = "Patient not found";
        public static final String PATIENT_LIST_EMPTY = "Patient list is empty";
        public static final String PATIENT_ALREADY_EXISTS_PID = "Patient with such PID already exists";
        public static final String PATIENT_ALREADY_EXISTS_EMAIL = "Patient with such email already exists";
    }

    public static class Allergy {
        private Allergy() {
            throw new IllegalStateException(ILLEGAL_STATE_EXCEPTION);
        }

        public static final String ALLERGY_NOT_FOUND = "Allergy not found";

        public static final String ALLERGY_CREATED = "Allergy is created";

        public static final String ALLERGY_UPDATED = "Allergy is updated";

        public static final String ALLERGY_DELETED = "Allergy is deleted";
    }

}
