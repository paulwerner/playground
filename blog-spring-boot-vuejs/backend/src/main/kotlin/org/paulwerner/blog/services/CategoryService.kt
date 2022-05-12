package org.paulwerner.blog.services

import org.paulwerner.blog.dto.CategoryDto
import org.paulwerner.blog.exceptions.NotFoundException
import org.paulwerner.blog.mappers.toCategoryDto
import org.paulwerner.blog.models.Category
import org.paulwerner.blog.repositories.CategoriesRepository
import org.springframework.stereotype.Service

@Service
class CategoryService(private val categoriesRepository: CategoriesRepository) {

    fun createCategory(createCategoryDto: CategoryDto): CategoryDto {
        val category = Category(name = createCategoryDto.name)
        val savedCategory = categoriesRepository.save(category)
        return toCategoryDto(savedCategory)
    }

    fun getAllCategories(): List<CategoryDto> =
            categoriesRepository.findAll()
                    .map { toCategoryDto(it) }
                    .toList()

    fun getByName(name: String): Category =
            categoriesRepository.findByName(name)
                    ?: throw NotFoundException("Category=[$name] could not be found")


    fun getByNameIn(names: List<String>): List<Category> =
            categoriesRepository.findByNameIn(names)

    fun getCategoryByName(name: String): CategoryDto {
        val category = getByName(name)
        return toCategoryDto(category)
    }

}
