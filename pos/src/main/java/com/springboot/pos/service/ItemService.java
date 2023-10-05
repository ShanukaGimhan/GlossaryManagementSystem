package com.springboot.pos.service;


import com.springboot.pos.dto.ItemDTO;
import com.springboot.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springboot.pos.dto.response.ItemGetResponseDTO;
import com.springboot.pos.entity.enums.MeasuringUnitType;

import java.util.List;

public interface ItemService {

    String saveItem(ItemDTO itemDTO);

    ItemDTO getItemByID(int itemID);

    ItemDTO updateItem(ItemDTO itemDto);

    String deleteItem(int itemID);

    List<ItemDTO> getAllByMapstruct();

    List<ItemGetResponseDTO> getItemByStatusAndTypeByResponseEntity(boolean status, MeasuringUnitType type);

    PaginatedResponseItemDTO getAllByStatusByPagination(boolean activeStatus, int page, int size);


    PaginatedResponseItemDTO getAllByNameWithPagination(String name, int page, int size);

    PaginatedResponseItemDTO getAllBySellingPriceByPagination(double sellingPrice, int page, int size);
}
