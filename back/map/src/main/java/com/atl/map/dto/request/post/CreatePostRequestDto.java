package com.atl.map.dto.request.post;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.List; 

@Getter
@Setter
@NoArgsConstructor
public class CreatePostRequestDto {

    @NotBlank
    private String title;
    @NotBlank
    private String content;
    @NotNull
    private List<String> imageList; 
}
