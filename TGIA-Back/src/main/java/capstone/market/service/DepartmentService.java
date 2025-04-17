package capstone.market.service;

import capstone.market.domain.Category;
import capstone.market.domain.CategoryType;
import capstone.market.domain.Department;
import capstone.market.domain.DepartmentType;
import capstone.market.repository.CategoryJpaRepository;
import capstone.market.repository.DepartMentJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class DepartmentService {

    private final DepartMentJpaRepository departMentJpaRepository;


    //1트랙 2트랙 레포지토리에 저장
    //public void
    @Transactional
    public void UpdateDepartment(Department department, DepartmentType departmentType) {
        department.setDepartmentType(departmentType);
        departMentJpaRepository.save(department);
    }
}
