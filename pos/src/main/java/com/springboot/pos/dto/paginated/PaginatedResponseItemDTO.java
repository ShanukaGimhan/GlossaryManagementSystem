package com.springboot.pos.dto.paginated;


import com.springboot.pos.dto.ItemDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaginatedResponseItemDTO {

    private List<ItemDTO> list;
    private long count;

}
