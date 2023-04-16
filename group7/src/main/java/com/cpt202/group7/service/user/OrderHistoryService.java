package com.cpt202.group7.service.user;

import com.cpt202.group7.entity.DTO.OrderHistoryDTO;
import com.cpt202.group7.entity.Order;
import com.cpt202.group7.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public interface OrderHistoryService {
    List<OrderHistoryDTO> getOrderHistoryList(Integer userId);
}
