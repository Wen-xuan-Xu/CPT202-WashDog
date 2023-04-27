package com.cpt202.group7.service.Interface;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpt202.group7.entity.OrderHistoryDTO;
import java.util.List;
import java.util.Map;

public interface OrderHistoryService {
    List<OrderHistoryDTO> findAllOrderHistoryByUserId(Integer userId);

    Page<OrderHistoryDTO> findAllOrderHistoryByUserIdWithPagination(Integer userId, Integer pageNo, Integer pageSize);

    Page<OrderHistoryDTO> findOrderHistoryByUserIdWithPaginationAndStatusFilter(Integer userId, Integer pageNo, Integer pageSize, String statusFilter);

    Map<String,Object> findOrderDetailByOrderId(String orderId);

    void submitComment(Integer userId, String orderID, Integer starLevel, String content);

    void cancelOrder(String orderId);
}