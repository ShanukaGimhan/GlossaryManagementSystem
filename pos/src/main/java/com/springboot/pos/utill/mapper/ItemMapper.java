package com.springboot.pos.utill.mapper;

import com.springboot.pos.dto.ItemDTO;
import com.springboot.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springboot.pos.dto.response.ItemGetResponseDTO;
import com.springboot.pos.entity.Item;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

import java.util.List;

// add mapper annotation for this class define as a mapper. and must add componentModel as a spring
@Mapper(componentModel = "spring")
public interface ItemMapper {


    ItemDTO getItemByID(Item item);

    List<ItemDTO> getAllItem(List<Item> items);

    List<ItemGetResponseDTO> getItemByStatusAndType(List<Item> items);

    List<ItemDTO> pageToListDTO(Page<Item> items);

    List<ItemDTO> pageToListForName(Page<Item> items);

    List<ItemDTO> pageToListSellingPrice(Page<Item> items);
}
