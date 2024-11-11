//package demo.kun.uz.controller;
//
//import demo.kun.uz.dto.JwtDTO;
//import demo.kun.uz.dto.ProfileCreationDTO;
//import demo.kun.uz.dto.ProfileDTO;
//import demo.kun.uz.dto.UpdateProfileDetailDTO;
//import demo.kun.uz.service.ProfileService;
//import demo.kun.uz.util.JwtUtil;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.access.prepost.PreAuthorize;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/profile")
//public class ProfileController {
//    private ProfileService profileService;
//
//
//    @PostMapping
//    @PreAuthorize("hasRole('ROLE_ADMIN')")
//
//    public ResponseEntity<ProfileDTO> addProfile(@RequestBody ProfileCreationDTO profileCreationDTO{
//        return  ResponseEntity
//    }
//
//    @PutMapping("/detail")
//    public ResponseEntity<Boolean> updateDetail(@RequestBody @Valid UpdateProfileDetailDTO requestDTO,
//                                                @RequestHeader("Authorization") String token) {
//        JwtDTO dto = JwtUtil.decode(token.substring(7));
//        return ResponseEntity.ok().body(profileService.updateDetail(requestDTO, dto.getUsername()));
//    }
//}
