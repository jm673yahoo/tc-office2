package ca.mydemo.tcoffice.service;

import ca.mydemo.tcoffice.domain.profile.UserProfile;

public interface UserService {
    String getUserByEmail(String email);

    UserProfile getUserProfileById(String id);
}
