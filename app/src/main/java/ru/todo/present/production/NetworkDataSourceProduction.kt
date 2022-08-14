package ru.todo.present.production

import com.mikepenz.fastadapter.utils.SubItemUtil
import ru.todo.present.core.datasource.NetworkDataSource
import ru.todo.present.core.mapper.WorkMapper
import ru.todo.present.core.model.Work
import ru.todo.present.mvi.Response
import ru.todo.present.production.dto.WorkDto
import javax.inject.Inject

class NetworkDataSourceProduction @Inject constructor(
    private val mapper: WorkMapper,
) : NetworkDataSource {

    override suspend fun getWorks(): Response<List<Work>> {

        try {
            Response.Success(listDto.map { workDto ->
                mapper.map(workDto)
            })
        } catch (){
            Response.TimeOut
        }

        //дергается апи которая возвращет dto
        val listDto = listOf(
            WorkDto(
                true,
                "10.03.2003",
                "Какой то тайтл 1"
            ),
            WorkDto(
                false,
                "10.03.2003",
                "Какой то тайтл 2"
            ),
            WorkDto(
                false,
                "10.03.2003",
                "Какой то тайтл 3"
            ),
            WorkDto(
                true,
                "10.03.2003",
                "Какой то тайтл 4"
            )
        )
        return Response.Success(listDto.map { workDto ->
            mapper.map(workDto)
        })
    }
}