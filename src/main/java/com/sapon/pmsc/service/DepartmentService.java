package com.sapon.pmsc.service;

import com.sapon.pmsc.helper.BusinessLogMessage;
import com.sapon.pmsc.helper.BusinessMessage;
import com.sapon.pmsc.model.Clinic;
import com.sapon.pmsc.model.Department;
import com.sapon.pmsc.repository.DepartmentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final ClinicService clinicService;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository,
                             ClinicService clinicService) {
        this.departmentRepository = departmentRepository;
        this.clinicService = clinicService;
    }

    public List<Department> getDepartments() {
        return departmentRepository.findAll();
    }

    public Object createDepartment(Department department) {
        Optional<Department> departmentName = departmentRepository
                .findDepartmentByName(department.getName());

        if (departmentName.isPresent()) {
            log.error(BusinessLogMessage.Department.DEPARTMENT_ALREADY_EXISTS + departmentName);
            throw new IllegalStateException(BusinessMessage.Department.DEPARTMENT_ALREADY_EXISTS);
        }
        departmentRepository.save(department);
        log.info(BusinessLogMessage.Department.DEPARTMENT_CREATED);
        return department;
    }


    public void deleteDepartment(final String id) {
        departmentRepository.delete(findDepartmentByDepartmentId(id));
        log.info(BusinessLogMessage.Department.DEPARTMENT_DELETED + id);
    }

    public Department findDepartmentById(final String id) {
        Department department = findDepartmentByDepartmentId(id);
        log.info(BusinessLogMessage.Department.DEPARTMENT_FOUND + id);
        return department;
    }

    public List<Department> findAllDepartments() {
        List<Department> departments = departmentRepository.findAll();

        if (departments.isEmpty()) {
            log.info(BusinessLogMessage.Department.DEPARTMENT_LIST_EMPTY);
            throw new IllegalStateException(BusinessMessage.Department.DEPARTMENT_LIST_EMPTY);
        }

        log.info(BusinessLogMessage.Department.DEPARTMENT_LIST_FOUND);
        return departments;
    }

    protected Department findDepartmentByDepartmentId(final String id) {
        return departmentRepository.findById(id)
                .orElseThrow(() -> {
                    log.error(BusinessLogMessage.Department.DEPARTMENT_NOT_FOUND + id);
                    throw new IllegalStateException(BusinessMessage.Department.DEPARTMENT_NOT_FOUND);
                });
    }

}

