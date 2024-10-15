package com.start.clubproject.entities.dtos;

import com.start.clubproject.entities.enums.UserRole;

public record RegisterDto(String name, UserRole role, String email, Boolean isMain, String password) {
}
