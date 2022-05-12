package org.paulwerner.blog.service

import org.paulwerner.blog.dto.CategoryDto
import org.paulwerner.blog.dto.PostDto
import org.paulwerner.blog.exceptions.CategoryAlreadyExistsException
import org.paulwerner.blog.exceptions.CategoryNotFoundException
import org.paulwerner.blog.model.Category
import org.paulwerner.blog.model.Post
import org.paulwerner.blog.repository.CategoryRepository
import org.paulwerner.blog.repository.PostRepository
import org.springframework.stereotype.Service
import java.util.stream.Collectors.toList

@Service
class BlogService(private val postRepository: PostRepository,
                  private val categoryRepository: CategoryRepository) {


    fun findAllCategories(): List<CategoryDto> {
        return categoryRepository.findAll().stream()
            .map { category ->
                CategoryDto(category.title)
            }.collect(toList())
    }

    fun createCategory(categoryDto: CategoryDto) {
        val foundCategory = categoryRepository.findByTitle(categoryDto.title)

        if (foundCategory == null) {
            val category = Category(title = categoryDto.title)
            categoryRepository.save(category)
        } else {
            throw CategoryAlreadyExistsException("a category with title=[${categoryDto.title}] already exists")

        }
    }

    fun findAllPostsByCategory(categoryTitle: String): List<PostDto> {
        val foundCategory = categoryRepository.findByTitle(categoryTitle)
            ?: throw CategoryNotFoundException("category for the given title=[$categoryTitle] could not be found")
        return postRepository.findByCategory(foundCategory).stream()
            .map { post ->
                PostDto(
                    post.title,
                    post.content,
                    post.category.title)
            }.collect(toList())
    }

    fun createPost(postDto: PostDto) {
        val foundCategory = categoryRepository.findByTitle(postDto.category)
            ?: throw CategoryNotFoundException("category for the given title=[${postDto.category}] could not be found")

        val post = Post(
            title = postDto.title,
            content = postDto.content,
            category = foundCategory)

        postRepository.save(post)
    }
}