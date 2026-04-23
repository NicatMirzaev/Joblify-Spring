package com.nijad.joblify.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JobListing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Job title is required")
    private String title;

    @NotBlank(message = "Description is required")
    @Column(length = 1000)
    private String description;

    @Min(value = 0, message = "Salary must be at least 0")
    private Double salary;

    @NotBlank(message = "Experience required is needed")
    private String experience;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private Company company;
}
