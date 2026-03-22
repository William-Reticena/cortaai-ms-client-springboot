package br.com.cortaai.client.dtos.shared;

import lombok.Builder;

import java.util.List;

@Builder
public record EmployeeWithSpecialties(
        Long id,
        String nmBarber,
        List<String> specialties
) {
}
