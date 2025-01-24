package com.echoyoun.jsoupsample

import org.jsoup.Jsoup
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service

@Service
class Worker {
//    @Scheduled(fixedDelay = 10000)
    fun test() {
        val result = Jsoup.connect("www.suwonch.org/main/sub.html?pageCode=262")
        println(result)
    }
}
