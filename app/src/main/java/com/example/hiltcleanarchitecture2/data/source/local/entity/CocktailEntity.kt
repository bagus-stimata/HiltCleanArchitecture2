package com.example.hiltcleanarchitecture2.data.source.local.entity

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

/**
 * Created by Gastón Saillén on 03 July 2020
 */

@Parcelize
data class Cocktail(
    @SerializedName("idDrink")
    val cocktailId: String = "",
    @SerializedName("strDrinkThumb")
    val image: String = "",
    @SerializedName("strDrink")
    val name: String = "",
    @SerializedName("strInstructions")
    val description: String = "",
    @SerializedName("strAlcoholic")
    val hasAlcohol: String = "Non_Alcoholic"
) : Parcelable

data class CocktailList(
    @SerializedName("drinks")
    val cocktailEntityList: List<CocktailEntity> = listOf()
)

@Entity(tableName = "cocktailTable")
data class CocktailEntity(
    @PrimaryKey
    val cocktailId: String,
    @ColumnInfo(name = "trago_imagen")
    val image: String = "",
    @ColumnInfo(name = "trago_nombre")
    val name: String = "",
    @ColumnInfo(name = "trago_descripcion")
    val description: String = "",
    @ColumnInfo(name = "trago_has_alcohol")
    val hasAlcohol: String = "Non_Alcoholic"
)

@Entity(tableName = "favoritesTable")
data class FavoritesEntity(
    @PrimaryKey
    val cocktailId: String,
    @ColumnInfo(name = "trago_imagen")
    val image: String = "",
    @ColumnInfo(name = "trago_nombre")
    val name: String = "",
    @ColumnInfo(name = "trago_descripcion")
    val description: String = "",
    @ColumnInfo(name = "trago_has_alcohol")
    val hasAlcohol: String = "Non_Alcoholic"
)

fun List<FavoritesEntity>.asDrinkList(): List<CocktailEntity> = this.map {
    CocktailEntity(it.cocktailId, it.image, it.name, it.description, it.hasAlcohol)
}

fun List<CocktailEntity>.asCocktailList(): List<CocktailEntity> = this.map {
    CocktailEntity(it.cocktailId, it.image, it.name, it.description, it.hasAlcohol)
}

fun CocktailEntity.asCocktailEntity(): CocktailEntity =
    CocktailEntity(this.cocktailId, this.image, this.name, this.description, this.hasAlcohol)

fun CocktailEntity.asFavoriteEntity(): FavoritesEntity =
    FavoritesEntity(this.cocktailId, this.image, this.name, this.description, this.hasAlcohol)