package org.hospital.service;


import org.hospital.repository.AdminRepository;

public class AdminService {
    private AdminRepository adminRepository;

    public AdminService() {
        // Instantiate the repository
        adminRepository = new AdminRepository();
    }

    public boolean validateAdminCredentials(String username, String password) {
        // Delegate the validation to the repository
        return adminRepository.validateAdminCredentials(username, password);
    }
}
