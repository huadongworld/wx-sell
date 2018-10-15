package com.ys.sell.dao;/**
 * @author HD
 * @date 2018/10/15 9:01
 */

import com.ys.sell.model.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author HD
 * @date 2018/10/15 9:01
 */
@Component
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {
}
