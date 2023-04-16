package com.cpt202.group7.service.Interface;
import com.cpt202.group7.entity.OrderHistoryDTO;
import java.util.List;

public interface OrderHistoryService {
    List<OrderHistoryDTO> findAllOrderHistoryByUserId(Integer userId);
}