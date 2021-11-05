package com.example.kotlin_template.mapper.auth

import com.example.kotlin_template.dao.User
import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import org.apache.ibatis.annotations.Param
import org.apache.ibatis.annotations.Select
import org.apache.ibatis.jdbc.SQL

@Mapper
interface UserMapper {
    @Select(
        """
        SELECT 
            * 
        FROM 
            users 
        WHERE login_id = #{loginId} 
        """
    )
    fun findByLoginId(@Param("loginId") loginId: String): User?

    @Insert(
        """
            INSERT INTO users 
                ( user_id, login_id, password, created_by, updated_by )
            VALUES 
                ( #{userId}, #{loginId}, #{password}, '0', '0' )
        """
    )
    fun insert(user: User): User

    @org.apache.ibatis.annotations.SelectProvider(type = SelectProvider::class, method = "paginate")
    fun paginate(condition: PaginateFetchCondition): List<User>

    data class PaginateFetchCondition(
        val current: Int,
        val perPage: Int
    )

    data class PaginateOrderCondition(
        val current: Int,
        val perPage: Int,
    )

    // FIXME:xmlでも良いけどこれでも良さそうな気が
    class SelectProvider {
        companion object {
            @JvmStatic
            fun paginate(condition: PaginateFetchCondition): String = condition.run {
                SQL()
                    .SELECT("*")
                    .FROM("users")
                    .OFFSET(((current - 1) * perPage).toLong())
                    .LIMIT(perPage)
                    .toString()
            }
        }
    }
}