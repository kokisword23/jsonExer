package softuni.jsonexer.service;

import softuni.jsonexer.domain.dto.ProductInRangeDto;
import softuni.jsonexer.domain.dto.ProductSeedDto;

import java.math.BigDecimal;
import java.util.List;

public interface ProductService {

    void seedProducts(ProductSeedDto[] productSeedDtos);

    List<ProductInRangeDto> productsInRange(BigDecimal more, BigDecimal less);
}
