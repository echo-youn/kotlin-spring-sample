package com.echoyoun.jsoupsample

object SiteConstants {
    const val SITE_URL = "http://suwonch.org"

    const val BASE_PATH = "/main/sub.html"


    /**
     * 은혜 간증 게시판(목록)
     */
    const val NUMBERS_1428_BOARD_ID = "www262"

    /**
     * 게시판 형식의 게시물에 본문이 들어가는 HTML Id
     * #AB_viewPrintArea ul li[0]: 제목
     * #AB_viewPrintArea ul li[1]: 날짜, 글쓴이
     * #AB_viewPrintArea ul li[2]: 본문
     * #AB_viewPrintArea ul li[3]: SNS 등...
     *
     * ```
     * <div class="mdDefaultW100" id="AB_viewPrintArea">
     *     <ul class="mdUl">
     *         <li class="mdLi">
     *             <div class="mdView_sbj"></div>
     *         </li>
     *     ...
     *     <ul>
     * <div>
     * ```
     */
    const val BOARD_CONTENT_HTML_ID = "AB_viewPrintArea"

    /**
     * 상세 페이지 제목
     * ```
     * <div class="mdDefaultW100 mdView_sbj">
     * 	<ul style="padding:10px;">
     *   2025년 제자, 사역훈련 모집 안내
     *  </ul>
     * </div>
     * ```
     */
    const val BOARD_SUBJECT_CLASS_NAME = "mdView_sbj"

    /**
     * 상세 페이지 작성자, 등록일, 조회수, 추천수
     *
     * 혹은 SNS 내보내기, 신고 기능
     * ```
     * <div class="mdDefaultW100 mdView_date">
     *  <ul style="padding:10px;">
     * 	 <span id="ANYSECURE_name_bview_15272" onclick="openLayer.memberMenu(event, 'kwonth1234', '');" '="">권태혁</span>
     * 	 <span class="mdView_line">|</span>
     * 	 등록일 : 2025.01.10
     * 	 <span class="mdView_line">|</span>
     * 	 조회수 : 86
     * 	 <span class="mdView_line">|</span>
     * 	 추천 : 0
     * 	 </ul>
     * </div>
     * ```
     */
    const val BOARD_WRITER_DATE_VIEWS_CLASS_NAME = "mdView_date"

    /**
     * 상세 페이지 내용란
     * ```
     * <div class="mdDefaultW100 mdView_cont">
     * 	<ul style="padding:10px;">
     * 	 <div class="mdDefaultW100" align="center">
     * 	    <div id="lightgallery">
     * 	      <p>... 내용 ...</p>
     * 	      <p>... 내용 ...</p>
     *      </div>
     *   </div>
     * 	</ul>
     * </div>
     * ```
     */
    const val BOARD_CONTENT_VIEW_CLASS_NAME ="mdView_cont"

    const val BOARD_CONTENT_VIEW_ID = "lightgallery"


    /**
     * 게시글 상세에 댓글 뷰
     *
     *
     */
    const val COMMENT_VIEW_ID = "AB_commentView"

    /**
     * #AB_commentView .mdRpbox
     */
    const val COMMENT_REPLY_CLASS_NAME = "mdRpbox"
}
