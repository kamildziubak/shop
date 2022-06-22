package com.github.kamildziubak.shop.backend.services;

import com.github.kamildziubak.shop.backend.dao.BasketRepository;
import com.github.kamildziubak.shop.backend.dao.ProductRepository;
import com.github.kamildziubak.shop.backend.dao.ProductTransportRepository;
import com.github.kamildziubak.shop.backend.dao.TransportRepository;
import com.github.kamildziubak.shop.backend.modules.dbModules.ProductTransport;
import com.github.kamildziubak.shop.backend.modules.dbModules.Transport;
import com.github.kamildziubak.shop.backend.modules.dbModules.ids.BasketProductId;
import com.github.kamildziubak.shop.backend.modules.dbModules.BasketProduct;
import com.github.kamildziubak.shop.backend.modules.dbModules.Product;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class BasketService {
    private final BasketRepository basketRepository;
    private final ProductRepository productRepository;
    private final ProductTransportRepository productTransportRepository;
    private final TransportRepository transportRepository;

    public BasketService(BasketRepository basketRepository, ProductRepository productRepository, ProductTransportRepository productTransportRepository, TransportRepository transportRepository) {
        this.basketRepository = basketRepository;
        this.productRepository = productRepository;
        this.productTransportRepository = productTransportRepository;
        this.transportRepository = transportRepository;
    }

    public ArrayList<BasketProduct> getBasket(int bsktId) {
        return basketRepository.findByBasketProductIdBsktId(bsktId);
    }

    public BasketProduct getBasketProduct(BasketProductId basketProductId){
        return basketRepository.findByBasketProductId(basketProductId);
    }

    public int insertBasketProduct(BasketProduct basketProduct){
        Product product = productRepository.findByProdId(basketProduct.getBasketProductId().getProdId());
        if(basketProduct.getQuantity()>product.getQuantity()){
            return 0;
        }
        else{
            product.setQuantity(product.getQuantity()-basketProduct.getQuantity());
            productRepository.save(product);
            try {
                if (getBasketProduct(basketProduct.getBasketProductId())==null) {
                    basketRepository.save(basketProduct);
                } else {
                    BasketProduct existingBasketProduct = basketRepository.findByBasketProductId(basketProduct.getBasketProductId());
                    existingBasketProduct.setQuantity(existingBasketProduct.getQuantity() + basketProduct.getQuantity());
                    basketRepository.save(existingBasketProduct);
                }
                return 1;
            }
            catch (Exception e){
                return 0;
            }
        }
    }

    public int removeBasketProduct(BasketProduct basketProduct){
        try {
            BasketProduct realBasketProduct = basketRepository.findByBasketProductId(basketProduct.getBasketProductId());
            if (basketProduct.getQuantity() > realBasketProduct.getQuantity()) {
                return 0;
            } else if (realBasketProduct.getQuantity() == basketProduct.getQuantity()) {
                basketRepository.delete(basketProduct);
            } else {
                realBasketProduct.setQuantity(realBasketProduct.getQuantity() - basketProduct.getQuantity());
                basketRepository.save(realBasketProduct);
            }
            Product product = productRepository.findByProdId(basketProduct.getBasketProductId().getProdId());
            product.setQuantity(product.getQuantity() + basketProduct.getQuantity());
            productRepository.save(product);
            return 1;
        } catch(java.lang.NullPointerException e){
            return 0;
        }
    }

    public ArrayList<Transport> getAvailableTransports(int bsktId){
        ArrayList<Transport> availableTransports = new ArrayList<>();
        List<BasketProduct> basketProducts = getBasket(bsktId);

        if(basketProducts.size()==0)
            return availableTransports;

        Product firstProduct = productRepository.findByProdId(basketProducts.get(0).getBasketProductId().getProdId());
        List<ProductTransport> firstProductTransports = productTransportRepository.findByProductTransportIdProdId(firstProduct.getProdId());

        for(int i=0; i<firstProductTransports.size(); i++){
            availableTransports.add(transportRepository.getOne(firstProductTransports.get(i).getProductTransportId().getTrnsId()));
        }

        for(int i=1; i<basketProducts.size(); i++){
            Product product = productRepository.findByProdId(basketProducts.get(i).getBasketProductId().getProdId());
            List<ProductTransport> productTransports = productTransportRepository.findByProductTransportIdProdId(product.getProdId());
            List<Transport> transports = new ArrayList<>();
            for(int ii=0; ii<productTransports.size(); ii++){
                transports.add(transportRepository.getOne(productTransports.get(ii).getProductTransportId().getTrnsId()));
            }
            int ii=0;
            do{
                if(transports.contains(availableTransports.get(ii))==false){
                    availableTransports.remove(ii);
                    ii=0;
                }
                else{
                    ii++;
                }
            }
            while(ii<availableTransports.size());
        }

        return availableTransports;
    }

    public void removeBasket(int bsktId){
        basketRepository.deleteByBasketProductIdBsktId(bsktId);
    }
}
