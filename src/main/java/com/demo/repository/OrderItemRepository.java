package com.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.demo.models.Order;
import com.demo.models.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

	List<OrderItem> findByOrder(Order order);

	@Query(value = "delete from oser_items o where o.order_id = :orderId", nativeQuery = true)
	@Transactional
	@Modifying
	void deleteByOrder(@Param("orderId") Long orderId);
}
