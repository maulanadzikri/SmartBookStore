function toggleSidebar() {
    document.getElementById("sidebar").classList.toggle('open');
    document.body.classList.toggle('sidebar-open');
}

document.addEventListener('DOMContentLoaded', function() {
    var currentUrl = window.location.pathname;

    var navLinks = document.querySelectorAll('.nav-link');

    navLinks.forEach(function(link) {
        var linkHref = link.getAttribute('href');

        if (currentUrl === linkHref) {
            link.classList.add('active');
        }
    });
});