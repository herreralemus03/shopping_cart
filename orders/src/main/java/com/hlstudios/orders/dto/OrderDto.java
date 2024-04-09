package com.hlstudios.orders.dto;

import com.hlstudios.orders.utils.Formatter;
import lombok.*;

import java.util.List;
import java.util.UUID;

@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    @Getter @Setter
    private UUID id;
    @Getter @Setter
    private CountryDto country;
    @Getter @Setter
    private StoreDto store;
    @Getter @Setter
    private List<OrderShippingDto> shipping;
    @Setter
    private String creationDate;

    public String getCreationDate(){
        return Formatter.formatDate("yyyy-MM-dd HH:mm:ss", this.creationDate);
    }
}
