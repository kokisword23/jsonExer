package softuni.jsonexer.service.impl;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import softuni.jsonexer.domain.dto.ProductInRangeDto;
import softuni.jsonexer.domain.dto.ProductSeedDto;
import softuni.jsonexer.domain.entity.Category;
import softuni.jsonexer.domain.entity.Product;
import softuni.jsonexer.domain.entity.User;
import softuni.jsonexer.repository.CategoryRepository;
import softuni.jsonexer.repository.ProductRepository;
import softuni.jsonexer.repository.UserRepository;
import softuni.jsonexer.service.ProductService;
import softuni.jsonexer.util.ValidatorUtil;

import javax.transaction.Transactional;
import javax.validation.constraints.Null;
import java.math.BigDecimal;
import java.util.*;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CategoryRepository categoryRepository;
    private final ValidatorUtil validatorUtil;
    private final ModelMapper modelMapper;

    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository, CategoryRepository categoryRepository, ValidatorUtil validatorUtil, ModelMapper modelMapper) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.categoryRepository = categoryRepository;
        this.validatorUtil = validatorUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public void seedProducts(ProductSeedDto[] productSeedDtos) {
        for (ProductSeedDto productSeedDto : productSeedDtos) {
            productSeedDto.setSeller(this.getRandomSeller());
            productSeedDto.setBuyer(this.getRandomBuyer());
            productSeedDto.setCategories(this.getRandomCategories());
            if (!this.validatorUtil.isValid(productSeedDto)){
                this.validatorUtil.violations(productSeedDto).forEach(v -> System.out.println(v.getMessage()));

                continue;
            }

            Product product = this.modelMapper.map(productSeedDto, Product.class);

            this.productRepository.saveAndFlush(product);
        }
    }


    private User getRandomSeller(){
        Random random = new Random();

        int id = random.nextInt((int)this.userRepository.count() - 1) + 1;

        return  this.userRepository.getOne(id);
    }

    private User getRandomBuyer(){
        Random random = new Random();

        int id = random.nextInt((int)this.userRepository.count() - 1) + 1;

        if (id % 4 == 0 ){
            return null;
        }

        return  this.userRepository.getOne(id);
    }

    private Category getRandomCategory(){
        Random random = new Random();

        int id = random.nextInt((int)this.categoryRepository.count() - 1) + 1;
        Category category = this.categoryRepository.findById(id).orElse(null);
        System.out.println();
        return  category;
    }

    private List<Category> getRandomCategories(){
        List<Category> categories =  new ArrayList<>();
        Random random = new Random();

        int size = random.nextInt((int)this.categoryRepository.count() - 1) + 1;

        for (int i = 0; i < size ; i++) {
            categories.add(getRandomCategory());
        }

        return categories;
    }

    @Override
    public List<ProductInRangeDto> productsInRange(BigDecimal more, BigDecimal less) {
        List<Product> products = this.productRepository
                .findAllByPriceBetweenAndBuyerIsNullOrderByPrice(more,less);

        List<ProductInRangeDto> productInRangeDtos = new ArrayList<>();

        for (Product product : products) {
            ProductInRangeDto productInRangeDto = this.modelMapper.map(product, ProductInRangeDto.class);
            productInRangeDto.setSeller(String.format("%s %s",product.getSeller().getFirstName(),
                    product.getSeller().getLastName()));

            productInRangeDtos.add(productInRangeDto);
        }

        return productInRangeDtos;
    }
}
