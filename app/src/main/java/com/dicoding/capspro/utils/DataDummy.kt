package com.dicoding.capspro.utils

import com.dicoding.capspro.data.Artikel

import java.util.ArrayList

object DataDummy {

    fun generateDummyCourses(): List<Artikel> {

        val articles= ArrayList<Artikel>()

        articles.add(
            Artikel("a1",
                "Artikel 1",
                "Ini Deskripsi Artikel 1",
                "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg")
        )
        articles.add(
            Artikel("a2",
                "Artikel 2",
                "Ini Deskripsi Artikel 2",
                "https://www.dicoding.com/images/small/academy/kotlin_android_developer_expert_logo_070119140227.jpg")
        )
        articles.add(
            Artikel("a3",
                "Artikel 3",
                "Ini Deskripsi Artikel 3",
                "https://www.dicoding.com/images/small/academy/menjadi_game_developer_expert_logo_070119140532.jpg")
        )
        articles.add(
            Artikel("a4",
                "Artikel 4",
                "Ini Deskripsi Artikel 4",
                "https://www.dicoding.com/images/small/academy/membangun_progressive_web_apps_logo_070119142922.jpg")
        )
        articles.add(
            Artikel("a5",
                "Artikel 5",
                "Ini Deskripsi Artikel 5",
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg")
        )

        articles.add(
            Artikel("a6",
                "Artikel 6",
                "Ini Deskripsi Artikel 6",
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg")
        )

        articles.add(
            Artikel("a7",
                "Artikel 7",
                "Ini Deskripsi Artikel 7",
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg")
        )

        articles.add(
            Artikel("a8",
                "Artikel 8",
                "Ini Deskripsi Artikel 8",
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg")
        )

        articles.add(
            Artikel("a9",
                "Artikel 9",
                "Ini Deskripsi Artikel 9",
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg")
        )

        articles.add(
            Artikel("a10",
                "Artikel 10",
                "Ini Deskripsi Artikel 10",
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg")
        )

        return articles
    }
}
