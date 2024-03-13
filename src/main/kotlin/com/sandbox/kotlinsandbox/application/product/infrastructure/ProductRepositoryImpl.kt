package com.sandbox.kotlinsandbox.application.product.infrastructure

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import com.sandbox.kotlinsandbox.application.product.domain.Product
import com.sandbox.kotlinsandbox.application.product.domain.QProduct
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import org.springframework.stereotype.Repository

@Repository
class ProductRepositoryImpl(
    private val query: JPAQueryFactory
) : ProductRepositoryCustom {

    override fun findAllBy(pageable: Pageable, title: String?, owner: String?): Page<Product> {
        val product = QProduct.product
        val list = query.selectFrom(product)
            .where(likeTitle(title))
            .where(likeOwner(owner))
            .limit(pageable.pageSize.toLong())
            .offset(pageable.offset)
            .orderBy(product.id.desc())
            .fetch()

        val count = query.select(product.id.count())
            .from(product)
            .where(likeTitle(title))
            .where(likeOwner(owner))

        return PageableExecutionUtils.getPage(list, pageable) { count.fetchOne()!! }
    }

    private fun likeTitle(title: String?): BooleanExpression? {
        return title?.let { QProduct.product.title.like("%{$title}%") }
    }

    private fun likeOwner(userName: String?): BooleanExpression? {
        return userName?.let { QProduct.product.owner.name.like("%{$userName}%") }
    }
}