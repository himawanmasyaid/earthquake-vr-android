package com.siklusdev.gempavr.model

data class VideoModel (
    val id: Int = 0,
    val title: String = "",
    val description: String  = "",
    val banner: String  = "",
    val video_url: String  = "",
    val category: CategoryModel = CategoryModel()
) {

    fun fetchVideo(): List<VideoModel> {

        return listOf(
            VideoModel(
                id = 1,
                title = "Simulasi Gempa Ketika di Ruang Kelas",
                description = "Anda dapat merasakan simulasi gempa melalui VR yang berada dalam ruangan rumah seperti di ruang kelas.",
                banner = "http://siklusdev.com/assets/ruang%20kelas.jpg",
//                video_url = "http://siklusdev.com/assets/Earthquake%20Virtual%20Reality%20Experience%20360%20Movie%20Vol.1%20in%20a%20classroom.mp4",
//                video_url = "https://github.com/himawanmasyaid/movies_android_compose/blob/feature/assets/app/src/main/assets/minions_360VR.mp4",
                video_url = "https://github.com/himawanmasyaid/movies_android_compose/blob/feature/assets/app/src/main/assets/minions_360VR.mp4?raw=true",
                category = CategoryModel().categoryPublicSpace()
            ),
            VideoModel(
                id = 2,
                title = "Dapur",
                description = "",
                banner = "http://siklusdev.com/assets/dapur.jpg",
                video_url = "http://siklusdev.com/assets/Dapur%20-%20Earthquake%20Virtual%20Reality%20Experience%20360%20Movie%20Vol.3%20in%20a%20kitchen%20of%20a%20home.mp4",
                category = CategoryModel().categoryHouse()
            ),
            VideoModel(
                id = 3,
                title = "Mini Market",
                description = "",
                banner = "http://siklusdev.com/assets/minimarket.jpeg",
                video_url = "http://siklusdev.com/assets/Mini%20Market%20-Earthquake%20Virtual%20Reality%20experience%20for%20standalone%20VR%20headset.mp4",
                category = CategoryModel().categoryPublicSpace()
            ),
            VideoModel(
                id = 4,
                title = "Kamar",
                description = "",
                banner = "http://siklusdev.com/assets/kamar.png",
                video_url = "http://siklusdev.com/assets/Kamar%20-Earthquake%20Virtual%20Reality%20Experience%20360%20Movie%20Vol.2%20Furniture%20Fall%20Risk%20and%20the%20Need%20for%20Fixing.mp4",
                category = CategoryModel().categoryHouse()
            ),
            VideoModel(
                id = 5,
                title = "Dalam Mobil",
                description = "",
                banner = "http://siklusdev.com/assets/mobil.png",
                video_url = "http://siklusdev.com/assets/Transformation%20-%20The%20virtual%20tsunami%20experience%20while%20driving%20a%20car%20using%20a%20head-mounted%20display.mp4",
                category = CategoryModel().categoryTransportation()
            )
        )

    }

    fun fetchDetailVideo(id: Int): VideoModel {
        val video = fetchVideo()
        return video.last { it.id == id }
    }

    fun fetchVideoByCategory(categoryId : Int): List<VideoModel> {

        val videoList : MutableList<VideoModel> = ArrayList()

        fetchVideo().forEach {
            if (it.category.id == categoryId) {
                videoList.add(it)
            }
        }

        return videoList

    }

}

