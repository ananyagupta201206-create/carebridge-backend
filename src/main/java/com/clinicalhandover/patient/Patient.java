package com.clinicalhandover.patient;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Patient {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank private String patientCode;
    @NotBlank private String fullName;
    private Integer age;
    private String ward;
    private String diagnosis;
    private String status = "Stable";

    public Patient() {}
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getPatientCode() { return patientCode; }
    public void setPatientCode(String patientCode) { this.patientCode = patientCode; }
    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }
    public Integer getAge() { return age; }
    public void setAge(Integer age) { this.age = age; }
    public String getWard() { return ward; }
    public void setWard(String ward) { this.ward = ward; }
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
