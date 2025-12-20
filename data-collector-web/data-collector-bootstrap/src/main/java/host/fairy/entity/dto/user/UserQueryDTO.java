/*****************************************************
 * @software: IntelliJ IDEA
 * @author: Lionel Johnson
 * @contact: https://fairy.host
 * @organization: https://github.com/FairylandFuture
 * @datetime: 2025-12-20 13:43:27 UTC+08:00
 ****************************************************/
package host.fairy.entity.dto.user;

import com.fasterxml.jackson.annotation.JsonFormat;
import host.fairy.entity.mo.user.UserQueryMO;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @author Lionel Johnson
 * @version 1.0
 */
@Data
public class UserQueryDTO {
    
    private String username;
    
    private String name;
    
    private String gender;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate birthdayStart;
    
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private LocalDate brithdayEnd;
    
    private String phone;
    
    private String email;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime createdAtStart;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime createdAtEnd;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime updatedAtStart;
    
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "UTC+8")
    private LocalDateTime updatedAtEnd;
    
    private Boolean deleted = false;
    
    private Integer page = 1;
    
    private Integer size = 10;
    
    
    public UserQueryMO toMO() {
        UserQueryMO mo = new UserQueryMO();
        BeanUtils.copyProperties(this, mo);
        return mo;
    }
}
