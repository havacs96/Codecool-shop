import {dataHandler} from "./data_handler.js";


export let dom = {

    init: function () {
        this.addListeners()
    },
    addListeners: function () {
        let categoryOption = document.querySelector(".category-option")
        categoryOption.addEventListener("change", (e) => {
            dom.changeProductsForCategory(e);
        })

    },
    changeProductsForCategory: function (e) {
        dom.loadProduct(e.currentTarget.selectedOptions[0].dataset.id)
    },
    loadProduct(id) {
        dataHandler.getProducts(id, function (product) {
            dom.showProducts(product)
        })
    },
    showProducts(products) {
        let contentBody = document.querySelector("#products")
        console.log(contentBody)
        contentBody.innerHTML = ""
        let cards = ""
        for (let product of products) {
            let card = `
            <div class="card">
                <img class="" src="/static/img/product_${product.id}.jpg" alt="" />
                <div class="card-header">
                    <h4 class="card-title">${product.name}</h4>
                    <p class="card-text">${product.description}</p>
                </div>
                <div class="card-body">
                    <div class="card-text">
                        <p class="lead">${product.defaultPrice}</p>
                    </div>
                    <div class="card-text">
                        <a class="btn btn-success" href="#">Add to cart</a>
                    </div>
                </div>
            </div>`
            cards += card
        }
        contentBody.innerHTML = `<div class="col col-sm-12 col-md-6 col-lg-4">${cards}</div>`
        console.log(contentBody)
    }
}