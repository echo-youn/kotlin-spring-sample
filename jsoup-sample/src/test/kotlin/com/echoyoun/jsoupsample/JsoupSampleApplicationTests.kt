package com.echoyoun.jsoupsample

import com.echoyoun.jsoupsample.SiteConstants.BASE_PATH
import com.echoyoun.jsoupsample.SiteConstants.BOARD_CONTENT_VIEW_ID
import com.echoyoun.jsoupsample.SiteConstants.BOARD_SUBJECT_CLASS_NAME
import com.echoyoun.jsoupsample.SiteConstants.BOARD_WRITER_DATE_VIEWS_CLASS_NAME
import com.echoyoun.jsoupsample.SiteConstants.NUMBERS_1428_BOARD_ID
import com.echoyoun.jsoupsample.SiteConstants.SITE_URL
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.select.Elements
import org.junit.jupiter.api.Test

class JsoupSampleApplicationTests {
    /**
     * path: /main/sub.html
     * Mode=view -- 상세 페이지
     * boardId: www262
     * num: 게시글 ID
     *
     */

    @Test
    fun analyzeList() {
        val document: Document =
            Jsoup.connect("$SITE_URL$BASE_PATH?Mode=list&boardID=$NUMBERS_1428_BOARD_ID&page=0").get()
        val list = document.select(".jSubject a").eachAttr("href").map {
            "$SITE_URL$it"
        }
        println(list)
    }

    @Test
    fun contextLoads() {
        val document: Document = Jsoup
            .connect("http://www.suwonch.org/main/sub.html?Mode=view&boardID=www262&num=15289&page=0&keyfield=&key=&bCate=")
            .get()
        val title = document.select(".$BOARD_SUBJECT_CLASS_NAME").text()

        val elements: Elements = document.select("#$BOARD_CONTENT_VIEW_ID")
        println(title)
        println(elements.textNodes().joinToString("\n") { it.text() })
    }
}
