package com.dicoding.capspro.utils

import com.dicoding.capspro.data.Artikel

import java.util.ArrayList

object DataDummy {

    fun generateDummyCourses(): List<Artikel> {

        val articles= ArrayList<Artikel>()

        articles.add(
            Artikel("a1",
                "Artikel 1",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "https://www.dicoding.com/images/small/academy/menjadi_android_developer_expert_logo_070119140352.jpg")
        )
        articles.add(
            Artikel("a2",
                "Artikel 2",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "https://www.dicoding.com/images/small/academy/kotlin_android_developer_expert_logo_070119140227.jpg")
        )
        articles.add(
            Artikel("a3",
                "Artikel 3",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "https://www.dicoding.com/images/small/academy/menjadi_game_developer_expert_logo_070119140532.jpg")
        )
        articles.add(
            Artikel("a4",
                "Artikel 4",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "https://www.dicoding.com/images/small/academy/membangun_progressive_web_apps_logo_070119142922.jpg")
        )
        articles.add(
            Artikel("a5",
                "Artikel 5",
                "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.",
                "https://www.dicoding.com/images/small/academy/belajar_membuat_aplikasi_android_untuk_pemula_logo_070119140911.jpg")
        )

        return articles
    }
}
