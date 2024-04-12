package org.hospital.service;


import org.hospital.repository.AdminRepository;

public class AdminService 
{
    private AdminRepository adminRepository;

    public AdminService() 
    {
        adminRepository = new AdminRepository();
    }

    public boolean validateAdminCredentials(String username, String password)
    {
        return adminRepository.validateAdminCredentials(username, password);
    }
}
