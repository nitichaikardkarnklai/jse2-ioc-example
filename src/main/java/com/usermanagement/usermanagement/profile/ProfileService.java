package com.usermanagement.usermanagement.profile;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }

    public List<Profile> getProfiles() {
        List<Profile> profiles = profileRepository.findAll();
        return profiles;
    }

//    public Profile createProfile(ProfileRequestDto profileRequestDto) {
//        return null;
//    }
}
