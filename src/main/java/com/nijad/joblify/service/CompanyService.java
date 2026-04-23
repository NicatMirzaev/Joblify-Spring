package com.nijad.joblify.service;

import com.nijad.joblify.entity.Company;
import com.nijad.joblify.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Value("${file.upload-path:uploads/}")
    private String uploadPath;

    public Company saveCompany(Company company, MultipartFile logo) throws IOException {
        if (logo != null && !logo.isEmpty()) {
            Path root = Paths.get(uploadPath);
            if (!Files.exists(root)) {
                Files.createDirectories(root);
            }
            String filename = UUID.randomUUID().toString() + "_" + logo.getOriginalFilename();
            Files.copy(logo.getInputStream(), root.resolve(filename));
            company.setLogoPath(filename);
        }
        return companyRepository.save(company);
    }

    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    public Company getCompanyById(Long id) {
        return companyRepository.findById(id).orElse(null);
    }
}
