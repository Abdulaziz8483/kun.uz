package demo.kun.uz.config;

import demo.kun.uz.Enum.ProfileRole;
import demo.kun.uz.Enum.ProfileStatus;
import demo.kun.uz.entity.ProfileEntity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private Integer id;
    private String name;
    private String surname;
    private String email;
    private String password;
    private ProfileStatus status;
    private ProfileRole role;

    public CustomUserDetails(ProfileEntity profile) {
        this.id = Math.toIntExact(profile.getId());
        this.name = profile.getName();
        this.surname = profile.getSurname();
        this.email = profile.getEmail();
        this.password = profile.getPassword();
        this.role = profile.getRoleEnum();
        this.status = profile.getStatusEnum();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.name()));
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Akkaunt muddati cheklanmagan, doimo mavjud
    }

    @Override
    public boolean isAccountNonLocked() {
        return status == ProfileStatus.ACTIVE; // Faqat ACTIVE holatidagi foydalanuvchilar akkauntlari qulflanmagan
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return status == ProfileStatus.ACTIVE;    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public ProfileRole getRole() {
        return role;
    }

    public ProfileStatus getStatus() {
        return status;
    }
}
