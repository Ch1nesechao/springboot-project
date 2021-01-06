package com.qy25.sm.service.impl;

import com.qy25.sm.entity.Category;
import com.qy25.sm.service.CategoryService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CategoryServiceImpl extends BaseServiceImpl<Category,Long> implements CategoryService {
}
