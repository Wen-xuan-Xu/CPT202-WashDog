package com.cpt202.group7.service.Interface;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpt202.group7.entity.OrderHistoryDTO;
import java.util.List;

public interface OrderHistoryService {
    List<OrderHistoryDTO> findAllOrderHistoryByUserId(Integer userId);

    Page<OrderHistoryDTO> findAllOrderHistoryByUserIdWithPagination(Integer userId, Integer pageNo, Integer pageSize);

    Page<OrderHistoryDTO> findOrderHistoryByUserIdWithPaginationAndStatusFilter(Integer userId, Integer pageNo, Integer pageSize, String statusFilter);
}