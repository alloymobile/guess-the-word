package com.alloymobile.guess.service.impl.member;

import com.alloymobile.guess.service.dto.IGuessDTO;
import lombok.Data;

@Data
public class MemberDTO implements IGuessDTO {
    private Long id;
    private String name;
    private Long teamId;
}
