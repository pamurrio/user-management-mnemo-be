package com.user.management.dto;

import com.user.management.models.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private Long id;
    @NotEmpty
    @Size(max = 20)
    private String name;
    @NotEmpty
    @Size(max = 40)
    private String lastName;
    @NotEmpty
    @Size(max = 40)
    private String code;
    private GroupDTO group;
}
