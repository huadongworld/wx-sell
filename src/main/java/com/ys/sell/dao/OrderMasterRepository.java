package com.ys.sell.dao;/**
 * @author HD
 * @date 2018/10/15 9:02
 */

import com.ys.sell.model.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

/**
 * @author HD
 * @date 2018/10/15 9:02
 */
@Component
public interface OrderMasterRepository extends JpaRepository<OrderMaster, String> {
}
