package ru.androidschool.intensiv.data.mappers

import ru.androidschool.intensiv.ui.feed.TVModel
import ru.androidschool.intensiv.ui.feed.TVResponse

object MovieMapper {
    fun toValueObject(list: TVResponse): List<TVModel> {
        return list.results
    }
}