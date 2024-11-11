package demo.kun.uz.service;


import demo.kun.uz.Enum.ProfileStatus;
import demo.kun.uz.config.CustomUserDetails;
import demo.kun.uz.dto.AuthDTO;
import demo.kun.uz.dto.ProfileDTO;
import demo.kun.uz.dto.RegistrationDTO;
import demo.kun.uz.entity.ProfileEntity;
import demo.kun.uz.repository.ProfileRepository;
import demo.kun.uz.util.JwtUtil;
import demo.kun.uz.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
@Service
public class AutService {

    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private EmailSendingService emailSendingService;
    @Autowired
    private AuthenticationManager authenticationManager;

    public String registration(RegistrationDTO dto) {
        // check email exists
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.md5(dto.getPassword()));
        entity.setSurname(dto.getSurname());
        entity.setStatusEnum(ProfileStatus.BANNED);
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);


        StringBuilder sb = new StringBuilder();
        sb.append("<h1 style=\"text-align: center\"> Complete Registration</h1>");
        sb.append("<br>");
        sb.append("<p>Click the link below to complete registration</p>\n");
        sb.append("<p><a style=\"padding: 5px; background-color: indianred; color: white\"  href=\"http://localhost:8081/auth/registration/confirm/")
                .append(entity.getId()).append("\" target=\"_blank\">Click Th</a></p>\n");
        emailSendingService.sendSimpleMessage(dto.getEmail(), "Complite Registration", sb.toString());
        emailSendingService.sendMimeMessage(dto.getEmail(), "Complite Registration", sb.toString());
        return "Email was sent";
    }


    public String registrationConfirm(Long id) {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()) {
            return "Not Completed";
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatusEnum().equals(ProfileStatus.BANNED)) {
            return "Not Completed";
        }
        entity.setStatusEnum(ProfileStatus.ACTIVE);
        profileRepository.save(entity);
        return "Completed";
    }

//    public ProfileDTO login(AuthDTO dto) {
//        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleTrue(Long.valueOf(dto.getEmail()));
//        if (optional.isEmpty()) {
//            throw new AppBadRequestException("Email or Password wrong");
//        }
//        ProfileEntity entity = optional.get();
//        if (!entity.getPassword().equals(MD5Util.md5(dto.getPassword()))) {
//            throw new AppBadRequestException("Email or Password wrong");
//        }
//        if (!entity.getStatusEnum().equals(ProfileStatus.ACTIVE)) {
//            throw new AppBadRequestException("User Not Active");
//        }
//        ProfileDTO profileDTO = new ProfileDTO();
//        profileDTO.setName(entity.getName());
//        profileDTO.setSurname(entity.getSurname());
//        profileDTO.setEmail(entity.getEmail());
//        profileDTO.setRole(entity.getRoleEnum());
//        profileDTO.setJwtToken(JwtUtil.encode(entity.getEmail(), entity.getStatusEnum()
//
//                .toString()));
//        return profileDTO;
//    }


    public ProfileDTO login(AuthDTO dto) {
        try {
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword()));
            if (authentication.isAuthenticated()) {
                CustomUserDetails profile = (CustomUserDetails) authentication.getPrincipal();

                ProfileDTO profileDTO = new ProfileDTO();
                profileDTO.setName(profile.getName());
                profileDTO.setSurname(profile.getSurname());
                profileDTO.setEmail(profile.getUsername());
                profileDTO.setRole(profile.getRole());
                profileDTO.setJwtToken(JwtUtil.encode(profile.getUsername(), profile.getRole().toString()));
                return profileDTO;
            }
        } catch (BadCredentialsException e) {
            throw new UsernameNotFoundException("Phone or password wrong");
        }
        throw new UsernameNotFoundException("Phone or password wrong");
    }


}
