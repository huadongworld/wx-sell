package com.ys.sell.dao;

import com.ys.sell.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author HD
 * @date 2018/10/15 9:01
 */
@Component
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

    List<OrderDetail> findByOrderId(String s);
}
