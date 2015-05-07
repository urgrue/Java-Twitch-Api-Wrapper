function clearGetParams() {
    // Clear the GET parameters so that the access_token is not stored in the browser history
    if(typeof window.history.pushState == 'function') {
        window.history.pushState({}, "Hide", location.origin + location.pathname);
    }
}
window.onload = clearGetParams();