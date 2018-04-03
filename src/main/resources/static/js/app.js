$(function(){

    // MetsiMenu
    $('#side-menu').metisMenu();

    // 菜单切换
    $('.navbar-minimalize').click(function () {
        $("body").toggleClass("mini-navbar");
        SmoothlyMenu();
    });

    $('#side-menu>li').click(function () {
        if ($('body').hasClass('mini-navbar')) {
            NavToggle();
        }
    });
    $('#side-menu>li li a').click(function () {
        if ($(window).width() < 769) {
            NavToggle();
        }
    });


})

function SmoothlyMenu() {
    if (!$('body').hasClass('mini-navbar')) {
        $('#side-menu').hide();
        setTimeout(
            function () {
                $('#side-menu').fadeIn(500);
            }, 100);
    } else if ($('body').hasClass('fixed-sidebar')) {
        $('#side-menu').hide();
        setTimeout(
            function () {
                $('#side-menu').fadeIn(500);
            }, 300);
    } else {
        $('#side-menu').removeAttr('style');
    }
}

function NavToggle() {
    $('.navbar-minimalize').trigger('click');
}