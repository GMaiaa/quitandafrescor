package com.example.quitandafrescor.dto;

import java.util.List;

public record CartResponseDTO(List<ItemCartResponseDTO> items, Float totalValue) {

}
