package softuni.jsonexer.service;

import softuni.jsonexer.domain.dto.CategorySeedDto;

public interface CategoryService {

    void seedCategories(CategorySeedDto[] categorySeedDtos);
}
