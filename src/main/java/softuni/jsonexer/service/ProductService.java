package softuni.jsonexer.service;

import softuni.jsonexer.domain.dto.ProductSeedDto;

public interface ProductService {

    void seedProducts(ProductSeedDto[] productSeedDtos);
}
