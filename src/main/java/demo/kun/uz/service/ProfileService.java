package demo.kun.uz.service;

import demo.kun.uz.dto.ProfileDTO;
import demo.kun.uz.dto.UpdateProfileDetailDTO;
import demo.kun.uz.entity.ProfileEntity;
import demo.kun.uz.repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import  demo.kun.uz.util.SpringSecurityUtil;
import java.util.List;
import java.util.Optional;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    public List<ProfileEntity> getAllProfiles() {
        return profileRepository.findAll();
    }

    public Optional<ProfileEntity> getProfileById(Long id) {
        return profileRepository.findById(id);
    }



    public ProfileEntity updateProfile(Long id, ProfileDTO profileDetails) {
        ProfileEntity profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));

        profile.setName(profileDetails.getName());
        profile.setSurname(profileDetails.getSurname());
        profile.setEmail(profileDetails.getEmail());
        profile.setPhone(String.valueOf(profileDetails.getPhotoId()));
        profile.setPassword(profileDetails.getPassword());


        return profileRepository.save(profile);
    }

//    public void deleteProfile(Long id) {
//        profileRepository.deleteById(id);
//    }
//    public boolean updateDetail(UpdateProfileDetailDTO requestDTO, String username) {
//        ProfileEntity profile = getProfileById(Long.valueOf(SpringSecurityUtil.getCurrentUserId()));
//        profile.setName(requestDTO.getName());
//        profile.setSurname(requestDTO.getSurname());
//        profileRepository.save(profile);
//
//        return true;
//    }
//
//
//    public ProfileEntity getByUsername(String username) {
//        return profileRepository.findByEmailAndVisibleTrue(username).orElseThrow(() -> new AppBadRequestException("User not found"));
//    }
}
