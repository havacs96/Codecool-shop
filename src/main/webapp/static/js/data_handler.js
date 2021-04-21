export let dataHandler = {
    _api_get: function (url, callback) {

        fetch(url, {
            method: 'GET',
            credentials: 'same-origin',
        })
            .then(response => response.json())  // parse the response as JSON
            .then(json_response => callback(json_response));  // Call the `callback` with the returned object
    },
    _api_post: function (url, data, callback) {

        // it is not called from outside
        // sends the data to the API, and calls callback function
        fetch(url, {
            method: 'POST',
            credentials: 'same-origin',
            headers: {
                'Content-Type': 'application/json'
                // 'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: JSON.stringify(data)
        })
            .then(response => response.json())  // parse the response as JSON
            .then(json_response => callback(json_response));
    },
    getProducts: function (sortBy, id, callback) {
        this._api_get("/product?" + new URLSearchParams({
            id: id, sortBy: sortBy}), (response) => {
            callback(response)
        })
    },
    addProductToCart: function (productId, callback) {
        this._api_get("/add_to_cart?" + new URLSearchParams({
            id: productId}), (response) => {
            callback(response)
        })
    },
    getCartItems: function (callback) {
        this._api_get("/cart_items", (response) => {
            callback(response)
        })
    },
    changeQuantity(currentId, value, callback) {
        let data = {"current_id": currentId, "value": value}
        this._api_post('/cart_items', data, (resp) => {
            console.log(resp.log)
            callback();
        });
    }
}