/**
 * Created by Administrator on 2015/8/4.
 */
$(function () {
    /*-----背景色----*/
    $('#dowebok').fullpage({
        sectionsColor: ['#28c085', '#fafafa', '#fafafa', '#28c085']
    });

    /*-----扫码下载hover-----*/
    $("#downShow").hover(
        function(){
            $(".lookErweima").removeClass("hideText");
        },
        function () {
            $(".lookErweima").addClass("hideText");
        }
    );
    /*-----扫码关注hover-----*/
    $("#lookShow").hover(
        function(){
            $(".downErweima").removeClass("hideText");
        },
        function () {
            $(".downErweima").addClass("hideText");
        }
    );
    /*-----page1扫码-----*/
    $("#page4showSaoma").hover(
        function () {
            $(this).addClass("hideText");
            $("#page4hidSaoma").removeClass("hideText");
            $("#page4showDown").addClass("hideText");
            $("#page4hidDown").removeClass("hideText");
            $("#erweimaDown").addClass("hideText");
            $("#erweimaGuanzhu").removeClass("hideText");
        }
    );
    $("#page4hidDown").hover(
        function () {
            $(this).addClass("hideText");
            $("#page4showDown").removeClass("hideText");
            $("#page4showSaoma").removeClass("hideText");
            $("#page4hidSaoma").addClass("hideText");
            $("#erweimaGuanzhu").addClass("hideText");
            $("#erweimaDown").removeClass("hideText");
        }
    );
    /*-----page1扫码-----*/
    $("#page4showSaoma1").hover(
        function () {
            $(this).addClass("hideText");
            $("#page4hidSaoma1").removeClass("hideText");
            $("#page4showDown1").addClass("hideText");
            $("#page4hidDown1").removeClass("hideText");
            $("#erweimaDown1").addClass("hideText");
            $("#erweimaGuanzhu1").removeClass("hideText");
        }
    );
    $("#page4hidDown1").hover(
        function () {
            $(this).addClass("hideText");
            $("#page4showDown1").removeClass("hideText");
            $("#page4showSaoma1").removeClass("hideText");
            $("#page4hidSaoma1").addClass("hideText");
            $("#erweimaGuanzhu1").addClass("hideText");
            $("#erweimaDown1").removeClass("hideText");
        }
    );
    /*-----下载hover----*/
    $("#showAndriod").hover(
        function () {
            $("#andriod1").addClass("hideText");
            $("#andriod2").removeClass("hideText");
        }, function () {
            $("#andriod2").addClass("hideText");
            $("#andriod1").removeClass("hideText");
        }
    );
    $("#showApple").hover(
        function () {
            $("#apple1").addClass("hideText");
            $("#apple2").removeClass("hideText");
        }, function () {
            $("#apple2").addClass("hideText");
            $("#apple1").removeClass("hideText");
        }
    );
    setInterval(function () {
        $(".Icon1").animate({
            top:'70px'
        },2000).animate({
            top:'140px'
        },0);
    });

    setInterval(function () {
        $(".Icon").animate({
            bottom:'0px'
        },2000).animate({
            bottom:'70px'
        },0);
    });
    $(".merchantAndriodDown").click(function () {
        location.href='../e.qtshe.com'
    });
    $(".merchantAppleDown").click(function () {
        location.href='../https@appsto.re/cn/Jtz16.i '
    });
    $(".studentAndriodDown").click(function () {
        location.href='../d.qtshe.com'
    });
    $(".studentAppleDown").click(function () {
        location.href='../https@appsto.re/cn/BmoO7.i '
    });
});