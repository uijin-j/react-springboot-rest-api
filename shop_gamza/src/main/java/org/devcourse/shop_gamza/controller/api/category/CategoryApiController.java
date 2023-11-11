package org.devcourse.shop_gamza.controller.api.category;

import lombok.RequiredArgsConstructor;
import org.devcourse.shop_gamza.controller.api.ApiResponse;
import org.devcourse.shop_gamza.domain.category.Category;
import org.devcourse.shop_gamza.dto.CategoryDTO;
import org.devcourse.shop_gamza.service.CategoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.devcourse.shop_gamza.util.ValidationUtils.requireNonBlank;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.NO_CONTENT;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryApiController {
    private final CategoryService categoryService;

    @PostMapping
    public ApiResponse<CategoryDTO> createCategory(@RequestParam String name) {
        requireNonBlank(name, "카테고리명은 빈 값일 수 없습니다.");

        Category saved = categoryService.save(name);
        CategoryDTO data = CategoryDTO.of(saved);

        return ApiResponse.of(CREATED, data);
    }

    @GetMapping
    public ApiResponse<List<CategoryDTO>> findAllCategories() {
        List<Category> categories = categoryService.findAll();
        List<CategoryDTO> data = categories.stream()
                .map(CategoryDTO::of)
                .toList();

        return ApiResponse.ok(data);
    }

    @DeleteMapping("/{id}")
    public ApiResponse deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ApiResponse.of(NO_CONTENT, null);
    }
}
