function openProfilePopup(event) {
    event.preventDefault();
    var width = 400;
    var height = 600;
    var left = (window.screen.width / 2) - (width / 2);
    var top = (window.screen.height / 2) - (height / 2);

    window.open('/profile', 'ProfilePopup',
        'width=' + width +
        ',height=' + height +
        ',left=' + left +
        ',top=' + top +
        ',resizable=yes,scrollbars=yes,status=yes'
    );
}