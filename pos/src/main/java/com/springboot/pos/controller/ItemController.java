package com.springboot.pos.controller;


import com.springboot.pos.dto.ItemDTO;
import com.springboot.pos.dto.paginated.PaginatedResponseItemDTO;
import com.springboot.pos.dto.response.ItemGetResponseDTO;
import com.springboot.pos.entity.enums.MeasuringUnitType;
import com.springboot.pos.exception.NotFoundException;
import com.springboot.pos.service.ItemService;
import com.springboot.pos.utill.StandardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/item")
@CrossOrigin
public class ItemController {

    @Autowired
    private ItemService itemService;



    // save method using response entity
    @PostMapping("/save-by-responseEntity")
    public ResponseEntity<StandardResponse> saveItemByResponseEntity(ItemDTO itemDTO) {
        String message = itemService.saveItem(itemDTO);
        ResponseEntity<StandardResponse> standardResponseResponseEntity = new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "SAVED", message), HttpStatus.CREATED
        );
        return standardResponseResponseEntity;
    }



    // get by id method using response entity
    @GetMapping(path = "/get-item-responseEntity",
            params = "id")
    public ResponseEntity<StandardResponse> getItemByIdByResponseEntity(@RequestParam(value = "id") int itemID) {
        ItemDTO itemDTO = itemService.getItemByID(itemID);
        ResponseEntity<StandardResponse> responseEntity = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Find data", itemDTO), HttpStatus.OK
        );
        return responseEntity;
    }



    // update method using response entity
    @PutMapping("/update-using-responseEntity")
    public ResponseEntity<StandardResponse> updateByResponseEntity(@RequestBody ItemDTO itemDTO) {
        ItemDTO itemDTs = itemService.updateItem(itemDTO);
        ResponseEntity<StandardResponse> responseEntity = new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Updated", itemDTs), HttpStatus.CREATED
        );
        return responseEntity;
    }




    // get all method using response entity
    @GetMapping("/get-all-by-responseEntity")
    public ResponseEntity<StandardResponse> getAllByResponseEntity() {
        List<ItemDTO> itemDTOS = itemService.getAllByMapstruct();
        ResponseEntity<StandardResponse> responseEntity = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Selected", itemDTOS), HttpStatus.OK
        );
        return responseEntity;
    }


    // delete method using responseEntity
    @DeleteMapping(
            value = "/delete-by-responseEntity",
            params = "id"
    )
    public ResponseEntity<StandardResponse> deleteItemByResponseEntity(@RequestParam(value = "id") int itemID) {
        String message = itemService.deleteItem(itemID);
        ResponseEntity<StandardResponse> responseEntity = new ResponseEntity<StandardResponse>(
                new StandardResponse(201, "Deleted", message), HttpStatus.ACCEPTED
        );
        return responseEntity;
    }


    // get by status and type using ResponseEntity
    @GetMapping(
            path = "/get-by-status-and-type-responseEntity",
            params = {"status", "type"}
    )
    public ResponseEntity<StandardResponse> getByStatusAndTypeByResponseEntity(
            @RequestParam(value = "status") boolean status,
            @RequestParam(value = "type") MeasuringUnitType type) {

        List<ItemGetResponseDTO> itemGetResponseDTOList = itemService.getItemByStatusAndTypeByResponseEntity(status, type);
        ResponseEntity<StandardResponse> responseEntity = new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Get By Type and Status", itemGetResponseDTOList), HttpStatus.OK
        );
        return responseEntity;
    }

    // get all by status method for pagination
    @GetMapping(
            path = "/get-all-by-status-pagination",
            params = {"activeStatus", "page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllByStatusByPagination(
            @RequestParam(value = "activeStatus") boolean activeStatus,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllByStatusByPagination(activeStatus, page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "Get by status using Pagination", paginatedResponseItemDTO), HttpStatus.OK
        );
    }

    // get all by itemName method for pagination
    @GetMapping(
            path = "/get-all-by-name-pagination",
            params = {"name", "page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllByNameByPagination(
            @RequestParam(value = "name") String name,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllByNameWithPagination(name, page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "data found by name using pagination", paginatedResponseItemDTO), HttpStatus.OK
        );
    }

    // get all by sellingPrice method for pagination
    @GetMapping(
            path = "/get-all-by-sellingPrice-pagination",
            params = {"sellingPrice", "page", "size"}
    )
    public ResponseEntity<StandardResponse> getAllBySellingPriceByPagination(
            @RequestParam(value = "sellingPrice") double sellingPrice,
            @RequestParam(value = "page") int page,
            @RequestParam(value = "size") int size
    ) {
        PaginatedResponseItemDTO paginatedResponseItemDTO = itemService.getAllBySellingPriceByPagination(sellingPrice, page, size);
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(200, "data found by selling price using pagination", paginatedResponseItemDTO), HttpStatus.OK
        );
    }

}
