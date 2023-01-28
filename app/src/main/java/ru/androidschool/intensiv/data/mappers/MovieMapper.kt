package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.data.vo.TVModel
import ru.androidschool.intensiv.data.vo.TVResponse

object MovieMapper {
    fun toValueObject(list: TVResponse): List<TVModel> {
        return list.results
    }
}