package com.dto;

import com.model.KeepRoom;
import lombok.Data;

@Data
public class KeepRoomDto {
    private Long id;
    private Integer quantity;
    private ProductDto product;

    public static KeepRoomDto toKeepRoomDto(KeepRoom keepRoom) {
        KeepRoomDto keepRoomDto = new KeepRoomDto();
        keepRoomDto.setId(keepRoom.getId());
        keepRoomDto.setQuantity(keepRoom.getQuantity());
        keepRoomDto.setProduct(ProductDto.modifierToProductDto(keepRoom.getProduct()));
        return keepRoomDto;
    }
}
