package com.siklusdev.gempavr.model

data class CategoryModel(
    val id: Int = 0,
    val name: String = "",
    val icon: String = ""
) {

    fun fetchCategory() : List<CategoryModel>{
        return listOf(
            categoryPublicSpace(),
            categoryHouse(),
            categoryOutdoor(),
            categoryTransportation()
        )
    }

    fun fetchDetailCategory(id: Int) : CategoryModel {

        val category = fetchCategory()
        return category.last {it.id == id}

    }

    fun categoryPublicSpace(): CategoryModel {
        return CategoryModel(
            id = 1,
            name = "Ruang Publik",
            icon = ""
        )
    }

    fun categoryHouse(): CategoryModel {
        return CategoryModel(
            id = 2,
            name = "Ruang di Rumah",
            icon = ""
        )
    }

    fun categoryOutdoor(): CategoryModel {
        return CategoryModel(
            id = 3,
            name = "Area Terbuka",
            icon = ""
        )
    }

    fun categoryTransportation() : CategoryModel {
        return CategoryModel(
            id = 4,
            name = "Kendaraan",
            icon = ""
        )
    }

}


