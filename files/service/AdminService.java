package files.service;

import files.model.Role;
import files.model.Admin;
import files.model.Techs;
import files.utils.CSVUtils;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class AdminService implements IAdminService {
    public static String path = "data/admin.scv";

    @Override
    public List<Admin> getAdmin() {
        List<Admin> newAdmin = new ArrayList<>();
        List<String> records = CSVUtils.read(path);
        for (String record : records) {
            newAdmin.add(Admin.parseAdmin(record));
        }
        return newAdmin;
    }

    @Override
    public Admin loginAdmin(String username, String password) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(username) && admin.getPassword().equals(password)
                    && admin.getRole() == Role.ADMIN) {
                return admin;
            }
        }
        return null;
    }

    @Override
    public void add(Admin newAdmin) {
        newAdmin.setCreatDate(Instant.now());
        List<Admin> admins = getAdmin();
        admins.add(newAdmin);
        CSVUtils.write(path, admins);
    }

    @Override
    public void update(Admin newAdmin) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(newAdmin.getUsername())) {
                String password = admin.getPassword();
                if (password != null && !password.isEmpty())
                    admin.setPassword("123456789");
                CSVUtils.write(path, admins);
                break;
            }
        }
    }

    @Override
    public boolean existById(long id) {
        return getUserById(id) != null;
    }

    @Override
    public void updateName(Admin newAdmin) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getId() == newAdmin.getId()) {
                String name = admin.getName();
                if (name != null && !name.isEmpty())
                    admin.setName(newAdmin.getName());
                admin.setUpdateDate(Instant.now());
                CSVUtils.write(path, admins);
                break;
            }

        }
    }

    @Override
    public void updatePhone(Admin newAdmin) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getId() == newAdmin.getId()) {
                String phone = newAdmin.getPhone();
                if (phone != null && !phone.isEmpty())
                    admin.setPhone(newAdmin.getPhone());
                admin.setUpdateDate(Instant.now());
                CSVUtils.write(path, admins);
                break;
            }
        }
    }

    @Override
    public void updateAddress(Admin newAdmin) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getId() == newAdmin.getId()) {
                String address = newAdmin.getAddress();
                if (address != null && !address.isEmpty())
                    admin.setAddress(newAdmin.getAddress());
                admin.setUpdateDate(Instant.now());
                CSVUtils.write(path, admins);
            }
        }
    }

    @Override
    public void remove(Admin newAdmin) {
        List<Admin> admins = getAdmin();
        admins.removeIf(admin -> admin.getId() == newAdmin.getId());
        CSVUtils.write(path, admins);
    }


    @Override
    public boolean checkDuplicateEmail(String email) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getEmail().equals(email))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicatePhone(String phone) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getPhone().equals(phone))
                return true;
        }
        return false;
    }

    @Override
    public boolean checkDuplicateUserName(String userName) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(userName))
                return true;
        }
        return false;
    }

    @Override
    public Admin getUserById(long id) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getId() == id)
                return admin;
        }
        return null;
    }

    public Admin getUserById(int id) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getId() == id)
                return admin;
        }
        return null;
    }

    public Admin getUserByAdminName(String userName) {
        List<Admin> admins = getAdmin();
        for (Admin admin : admins) {
            if (admin.getUsername().equals(userName)) {
                return admin;
            }
        }
        return null;
    }

}
