package com.nijad.joblify.service;

import com.nijad.joblify.entity.Company;
import com.nijad.joblify.repository.CompanyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CompanyServiceTest {

    @Mock
    private CompanyRepository companyRepository;

    @InjectMocks
    private CompanyService companyService;

    @Test
    void getAllCompanies_ShouldReturnListOfCompanies() {
        Company company1 = new Company();
        company1.setName("Tech Corp");
        Company company2 = new Company();
        company2.setName("Innovate LLC");

        when(companyRepository.findAll()).thenReturn(Arrays.asList(company1, company2));

        List<Company> companies = companyService.getAllCompanies();

        assertNotNull(companies);
        assertEquals(2, companies.size());
        verify(companyRepository, times(1)).findAll();
    }
}
