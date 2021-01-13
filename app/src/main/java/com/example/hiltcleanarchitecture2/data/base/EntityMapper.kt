package com.example.hiltcleanarchitecture2.data.base

import com.example.hiltcleanarchitecture2.domain.base.Model

interface EntityMapper<M : Model, ME : ModelEntity> {
    fun mapToDomain(entity: ME): M

    fun mapToEntity(model: M): ME
}