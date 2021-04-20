export let dataHandler = {
    _api_get: function (url, callback) {

        fetch(url, {
            method: 'GET',
            credentials: 'same-origin',
        })
            .then(response => response.json())  // parse the response as JSON
            .then(json_response => callback(json_response));  // Call the `callback` with the returned object
    },
    getProducts: function (sortBy, id, callback) {
        this._api_get("/product?" + new URLSearchParams({
            id: id, sortBy: sortBy}), (response) => {
            callback(response)
        })
    }
}