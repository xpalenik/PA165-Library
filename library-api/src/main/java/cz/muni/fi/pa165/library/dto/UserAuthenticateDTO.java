package cz.muni.fi.pa165.library.dto;
/**
 * from https://github.com/fi-muni/PA165/blob/master/eshop-api/src/main/java/cz/fi/muni/pa165/dto/UserAuthenticateDTO.java
 * @author Katarína Hermanová
 * UČO 433511
 * Github katHermanova
 */
public class UserAuthenticateDTO {
    private Long userId;
    private String password;

    public UserAuthenticateDTO(long userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public Long getUserId()
    {
        return userId;
    }

    public void setUserId(Long userId)
    {
        this.userId = userId;
    }

    public String getPassword()
    {
        return password;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}
