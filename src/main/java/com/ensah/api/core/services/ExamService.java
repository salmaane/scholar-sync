package com.ensah.api.core.services;

import com.ensah.api.core.dao.ExamDAO;
import com.ensah.api.core.dto.ExamDTO;
import com.ensah.api.core.dto.InformationDTO;
import com.ensah.api.core.models.*;
import com.ensah.api.core.models.Class;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class ExamService {

    private final ExamDAO dao;
    private final ProfessorService professorService;
    private final SubjectService subjectService;
    private final ClassService classService;
    private final InformationService informationService;
    private final AdminService adminService;

    public Long rowsNumber(){
        return dao.count();
    }

    public ExamDTO create(ExamDTO examDTO) {
        // Get current Semester infos
        InformationDTO information = informationService.getInformation();

        Subject subject = subjectService.findById(examDTO.getSubjectId()).orElseThrow();
        List<Class> classes = classService.findAllById(
                examDTO.getClasses().stream().map(ExamDTO.ProfsPerClass::getClassId).toList()
        );
        // Get Available Professors for survaillance
        List<Professor> availableProfessors = professorService.findAvailableForSurveillance(
                examDTO.getGroupId(),
                examDTO.getDate(),
                examDTO.getStartHour(),
                information.getProfSurveillanceNumberPerDay()
        );
        // Get Available Professors for survaillance
        List<Administrator> availableControllers = adminService.findAvailableForSurveillance(
                examDTO.getDate(),
                examDTO.getStartHour(),
                information.getProfSurveillanceNumberPerDay()
        );

        Long neededProfessors = 0L;
        for (ExamDTO.ProfsPerClass profsPerClass : examDTO.getClasses()) {
            neededProfessors += profsPerClass.getNumber();
        }

        if (
            (availableProfessors.size() >= neededProfessors) &&
            (availableControllers.size() >= examDTO.getClasses().size())
        ) {
            // Step 1 : Exam object creation
            Exam exam = ExamDTO.toExam(examDTO);
            // Assign Exam Subject
            exam.setSubject(subject);

            // Step 2 : Create Surveillances for Exam
            List<Surveillance> surveillances = new ArrayList<>();
            for(Class Class : classes) {
                //Get number of professors per class
                ExamDTO.ProfsPerClass profsPerClass = examDTO.getClasses().stream()
                        .filter(perClass -> Objects.equals(perClass.getClassId(), Class.getId()))
                        .findFirst().orElseThrow();

                List<Professor> professorList = new ArrayList<>();
                for(int i = 0; i < profsPerClass.getNumber(); i++) {
                    professorList.add(availableProfessors.removeFirst());
                }

                Surveillance surveillance = Surveillance.builder()
                        .aClass(Class)
                        .absController(availableControllers.removeFirst())
                        .coordinator(subject.getCoordinator())
                        .exam(exam)
                        .professors(professorList)
                        .build();

                for(Professor prof : professorList) {
                    prof.getSurveillances().add(surveillance);
                }
                subject.getCoordinator().getManaged_surveillances().add(surveillance);

                surveillances.add(surveillance);
            }

            exam.setSurveillances(surveillances);

            dao.save(exam);
            return ExamDTO.toDTO(exam);
        } else {
            return null;
        }
    }

    public List<ExamDTO> findAll(String academicYear) {
        List<Exam> exams = dao.findAllByAcademicYear(academicYear);
        return exams.stream().map(ExamDTO::toDTO).toList();
    }

    public String[] getAcademicYears() {
        return dao.getAcademicYears();
    }
}
