package com.github.kamildziubak.shop.backend.services;

import com.github.kamildziubak.shop.backend.dao.ProductRepository;
import com.github.kamildziubak.shop.backend.dao.ProductTransportRepository;
import com.github.kamildziubak.shop.backend.dao.TransportRepository;
import com.github.kamildziubak.shop.backend.modules.dbModules.Product;
import com.github.kamildziubak.shop.backend.modules.dbModules.ProductTransport;
import com.github.kamildziubak.shop.backend.modules.dbModules.ids.ProductTransportId;
import com.github.kamildziubak.shop.backend.modules.nodDbModules.ProductWithTransports;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ProductService {
    private final ProductRepository productRepository;
    private final ProductTransportRepository productTransportRepository;

    @Autowired
    public ProductService(ProductRepository productRepository, ProductTransportRepository productTransportRepository, TransportRepository transportRepository) {
        this.productRepository = productRepository;
        this.productTransportRepository = productTransportRepository;
    }

    public List<ProductWithTransports> getAllProducts()
    {
        List<Product> products = productRepository.findAll();
        List<ProductWithTransports> productWithTransports = new ArrayList<>();

        for(int x=0; x<products.size(); x++){
            List<ProductTransport> productTransports = productTransportRepository.findByProductTransportIdProdId(products.get(x).getProdId());
            ArrayList<Integer> transports = new ArrayList<>();
            for(int y=0; y<productTransports.size(); y++){
                transports.add(productTransports.get(y).getProductTransportId().getTrnsId());
            }
            productWithTransports.add(new ProductWithTransports(products.get(x), transports));
        }

        return productWithTransports;
    }

    public ProductWithTransports getProduct(int id) {
        Product product = productRepository.findByProdId(id);
        List<ProductTransport> productTransports = productTransportRepository.findByProductTransportIdProdId(id);
        ArrayList<Integer> transports = new ArrayList<>();
        for(int i=0; i<productTransports.size(); i++){
            transports.add(productTransports.get(i).getProductTransportId().getTrnsId());
        }
        ProductWithTransports productWithTransports = new ProductWithTransports(product, transports);
        return productWithTransports;
    }

    public int getLastId()
    {
        List<Product> productList = (ArrayList)productRepository.findAll();
        Product lastProduct = productList.get(productList.size()-1);
        return lastProduct.getProdId();
    }

    public void insertNewProduct(ProductWithTransports productWithTransports, boolean giveId)
    {
        if(giveId)
            productWithTransports.setProdId(getLastId()+1);

        BigDecimal price = productWithTransports.getPrice().setScale(2, RoundingMode.UP);
        productWithTransports.setPrice(price);
        Product product = new Product(productWithTransports.getProdId(),
                productWithTransports.getName(),
                productWithTransports.getDescription(),
                productWithTransports.getPrice(),
                productWithTransports.getDiscount(),
                productWithTransports.getQuantity(),
                productWithTransports.getCategory());
        productRepository.save(product);

        ArrayList<Integer> transports = productWithTransports.getTransports();

        for(int i=0; i<transports.size(); i++){
            ProductTransportId productTransportId = new ProductTransportId();
            productTransportId.setProdId(productWithTransports.getProdId());
            productTransportId.setTrnsId(transports.get(i));

            ProductTransport productTransport = new ProductTransport();
            productTransport.setProductTransportId(productTransportId);

            productTransportRepository.save(productTransport);
        }
    }

    public int lowerProductQuantity(int prodId, int quantity){
        if(productRepository.findByProdId(prodId)!=null) {
            int productQuantity = productRepository.findByProdId(prodId).getQuantity();
            if (quantity > productQuantity)
                return 0;
            else
                return productRepository.lowerProductQuantity(prodId, quantity);
        }
        return 0;
    }
}