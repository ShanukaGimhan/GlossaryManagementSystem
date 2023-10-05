package com.springboot.pos.service.impl;

import com.springboot.pos.dto.ItemDTO;
import com.springboot.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springboot.pos.dto.response.ItemGetResponseDTO;
import com.springboot.pos.entity.Item;
import com.springboot.pos.entity.enums.MeasuringUnitType;
import com.springboot.pos.exception.NotFoundException;
import com.springboot.pos.repo.ItemRepo;
import com.springboot.pos.service.ItemService;
import com.springboot.pos.utill.mapper.ItemMapper;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ItemServiceIMPL implements ItemService {

    @Autowired
    private ItemRepo itemRepo;

    @Autowired
    private ModelMapper modelMapper;  // inject dependency of modelMapper

    @Autowired
    private ItemMapper itemMapper; // inject dependency of mapstruct


    // using model mapper can use get item without coding one by one
    @Override  // save method using modelMapper
    public String saveItem(ItemDTO itemDTO) {
        Item item = modelMapper.map(itemDTO, Item.class);
        itemRepo.save(item);
        return itemDTO.getItemName() + " Save";
    }


    @Override // select by id method using ModelMapper
    public ItemDTO getItemByID(int itemID) {
        if (itemRepo.existsById(itemID)) {

            Item item = itemRepo.getReferenceById(itemID);
            ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
            return itemDTO;

        } else {
            throw new NotFoundException("No Item Data");
        }
    }



    @Override // update method using ModelMapper
    public ItemDTO updateItem(ItemDTO itemDto) {
        if (itemRepo.existsById(itemDto.getId())) {

            itemRepo.getReferenceById(itemDto.getId());
            Item item = modelMapper.map(itemDto, Item.class);
            itemRepo.save(item);
            ItemDTO itemDTO = modelMapper.map(item, ItemDTO.class);
            System.out.println("update success");
            return itemDTO;

        } else {
            throw new NotFoundException("Update item Fail");
        }
    }




    @Override // delete method same ModelMapping and Mapstruct
    public String deleteItem(int itemID) {
        if (itemRepo.existsById(itemID)) {
            itemRepo.deleteById(itemID);
            return itemID + " Delete Success";
        } else {
            throw new NotFoundException("Delete Fail");
        }
    }

    // get all method using Mapstruct
    @Override
    public List<ItemDTO> getAllByMapstruct() {
        List<Item> items = itemRepo.findAll();
        if (items.size() > 0) {
            List<ItemDTO> itemDTOS = itemMapper.getAllItem(items);
            return itemDTOS;
        } else {
            throw new NotFoundException("Failed find using Mapstruct");
        }
    }


    // get by status and type using ResponseEntity and Mapstruct
    @Override
    public List<ItemGetResponseDTO> getItemByStatusAndTypeByResponseEntity(boolean status, MeasuringUnitType type) {
        List<Item> items = itemRepo.findByActiveStateAndMeasuringUnitTypeEquals(status, type);
        if (items.size() > 0) {
            List<ItemGetResponseDTO> itemGetResponseDTOList = itemMapper.getItemByStatusAndType(items);
            return itemGetResponseDTOList;
        } else {
            throw new NotFoundException("failed data find");
        }
    }

    // get by status using pagination
    @Override
    public PaginatedResponseItemDTO getAllByStatusByPagination(boolean activeStatus, int page, int size) {
        Page<Item> items = itemRepo.findAllByActiveStateEquals(activeStatus, PageRequest.of(page, size));
        if (items.getSize() > 0) {

            PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                    itemMapper.pageToListDTO(items),
                    itemRepo.countAllByActiveStateEquals(activeStatus)
            );
            return paginatedResponseItemDTO;

        } else {
            throw new NotFoundException("Data find error in status using pagination");
        }
    }

    // get by name using pagination
    @Override
    public PaginatedResponseItemDTO getAllByNameWithPagination(String name, int page, int size) {
        Page<Item> items = itemRepo.findAllByItemNameEqualsIgnoreCase(name, PageRequest.of(page, size));
        if (items.getSize() > 0) {
            PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                    itemMapper.pageToListForName(items),
                    itemRepo.countAllByItemNameEqualsIgnoreCase(name)
            );
            return paginatedResponseItemDTO;
        } else {
            throw new NotFoundException("no data found from name using pagination");
        }

    }

    // get by sellingPrice using pagination
    @Override
    public PaginatedResponseItemDTO getAllBySellingPriceByPagination(double sellingPrice, int page, int size) {
        Page<Item> items = itemRepo.findAllBySellingPriceEquals(sellingPrice, PageRequest.of(page, size));
        if (items.getSize() > 0) {
            PaginatedResponseItemDTO paginatedResponseItemDTO = new PaginatedResponseItemDTO(
                    itemMapper.pageToListSellingPrice(items),
                    itemRepo.countAllBySellingPriceEquals(sellingPrice)
            );
            return paginatedResponseItemDTO;
        } else {
            throw new NotFoundException("no data using selling price");
        }

    }



}
