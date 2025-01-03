package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class OptimisticLockStockService {

    private final StockRepository stockRepository;

    public OptimisticLockStockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease(Long id, Long quantity){
        // Stock 조회
        Stock stock = stockRepository.findByIdWithinOptimisticLock(id);
        // 재고를 감소한 뒤
        stock.decrease(quantity);
        // 갱신된 값을 저장
        stockRepository.save(stock);
    }
}
