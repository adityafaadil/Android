package com.dicoding.capspro.utils

import com.dicoding.capspro.data.Artikel

import java.util.ArrayList

object DataDummy {

    fun generateDummyCourses(): List<Artikel> {

        val articles= ArrayList<Artikel>()

        articles.add(
            Artikel("a1",
                "Testimoni Kekerasan Seksual : 174 penyintas, 79 kampus, 29 kota",
                "Wed 23 Mar, 2019  12:10 PM",
                "https://mmc.tirto.id/image/otf/700x0/2019/03/14/header-nama-baik-kampus-375x250-01_ratio-16x9.jpg")
        )
        articles.add(
            Artikel("a2",
                "93 Persen Penyintas Tak Laporkan Pemerkosaan yang Dialami: Survei",
                "Thu 22 Jul, 2016  12:10 PM",
                "https://magdalene.co/storage/media/47SURVEY%20mulai%20bicara%20text.jpg")
        )
        articles.add(
            Artikel("a3",
                "Ada 3 Korban yang Melaporkan Dugaan Kekerasan Seksual di SMA di Kota Batu",
                "Sun 31 May, 2021  12:10 PM",
                "https://asset.kompas.com/crops/hIy62a1Bjqd21bwEODWkgNnD1PY=/0x0:0x0/750x500/data/photo/2021/05/21/60a7aac12666b.jpg")
        )
        articles.add(
            Artikel("a4",
                "5 Provinsi Ini Penyumbang Naiknya Okupansi Tempat Tidur Pasien Covid-19",
                "Sat 28 May, 2021  12:10 PM",
                "https://statik.tempo.co/data/2021/05/27/id_1023956/1023956_720.jpg")
        )
        return articles
    }
}
