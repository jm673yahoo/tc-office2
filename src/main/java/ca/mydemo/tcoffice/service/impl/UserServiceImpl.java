package ca.mydemo.tcoffice.service.impl;

import ca.mydemo.tcoffice.domain.profile.UserProfile;
import ca.mydemo.tcoffice.domain.profile.UserType;
import ca.mydemo.tcoffice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Override
    public String getUserByEmail(String email) {
        return "mike@test.com";
    }

    @Override
    public UserProfile getUserProfileById(String id) {
        UserProfile profile = new UserProfile();
        profile.setUserType(UserType.SYSADMIIN);
        profile.setBizId("10");
        profile.setGroupLevelId("admin");
        profile.setPartnerId("tc");
        return profile;
    }
}
