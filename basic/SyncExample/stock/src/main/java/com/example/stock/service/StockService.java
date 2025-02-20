package com.example.stock.service;

import com.example.stock.domain.Stock;
import com.example.stock.repository.StockRepository;
import org.hibernate.annotations.Synchronize;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StockService {

    private final StockRepository stockRepository;

    public StockService(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }
    // 기존 Lock
//
//    @Transactional
//    public synchronized void decrease(Long id, Long quantity){
//        // Stock 조회
//        Stock stock = stockRepository.findById(id).orElseThrow();
//        // 재고를 감소한 뒤
//        stock.decrease(quantity);
//        // 갱신된 값을 저장
//        stockRepository.saveAndFlush(stock);
//    }
//
    // NamedLock
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void decrease(Long id, Long quantity){
        // Stock 조회
        Stock stock = stockRepository.findById(id).orElseThrow();
        // 재고를 감소한 뒤
        stock.decrease(quantity);
        // 갱신된 값을 저장
        stockRepository.saveAndFlush(stock);
    }
}
